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
	public static final String HOST = "http://gautam.cc:9000/games";
	public static int GAME_PIN = 12348;

	private HashMap<String, Integer> ids = new HashMap<>();

	public static ArrayList<String> state;
	public Board board = Main.board;

	public Network() {
		state = new ArrayList<>();
		
		try {
			URL url = new URL(HOST);
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
		} catch (IOException e)  {
			System.out.println(e);
		}
		
	}

	public void sendLocalChange(String move) throws IOException {

		state.add(move);
		System.out.println(state.toString());

		URL url = new URL(HOST);
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
		URL url = new URL(HOST);
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
