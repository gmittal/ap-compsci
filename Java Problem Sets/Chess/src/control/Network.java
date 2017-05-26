package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import board.Board;

public class Network {
	/* Populate these variables on multi-player startup */
	public static final String HOST = "http://gautam.cc";
	public static int GAME_PIN = 1134;

	private HashMap<String, Integer> ids = new HashMap<>();

	public static ArrayList<String> state;
	public Board board = Main.board;

	
	/* Default constructor creates a new game pin for others to join */
	public Network() {
		
		try {
			GAME_PIN = startNewGame();
			System.out.println("MULTIPLAYER GAME PIN: "+GAME_PIN);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		state = new ArrayList<>();

		try {
			URL url = new URL(HOST+":"+GAME_PIN+"/games");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("PUT");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
			osw.write(String.format("{\"" + GAME_PIN + "\":%s}", "[]"));
			osw.flush();
			osw.close();
			System.err.println(connection.getResponseCode());
		} catch (IOException e) {
			System.out.println(e);
		}

	}
	
	/* Constructor joins another game given a game pin */
	public Network(int pin) {
		GAME_PIN = pin;
	}
	
	public int startNewGame() throws IOException {
		URL url = new URL(HOST+":9000/new");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		int r = Integer.parseInt(rd.readLine(), 10);
		rd.close();
		return r;
	}
	
	public void sendLocalChange(String move) throws IOException {

		state.add(move);
		System.out.println(state.toString());

		URL url = new URL(HOST+":"+GAME_PIN+"/games");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
		osw.write(String.format("{\"" + GAME_PIN + "\":%s}", state.toString()));
		osw.flush();
		osw.close();
		System.err.println(connection.getResponseCode());

	}

	public ArrayList<String> listenForNetworkChange() throws IOException, JSONException {
		StringBuilder result = new StringBuilder();
		URL url = new URL(HOST+":"+GAME_PIN+"/games");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();

		JSONArray obj = new JSONObject(result.toString()).getJSONArray(Integer.toString(GAME_PIN));
		ArrayList<String> cloud = new ArrayList<>();
		for (int i = 0; i < obj.length(); i++) {
			cloud.add(obj.getString(i));
		}

		return cloud;

	}

}
