package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import board.Board;

public class Network {
	/* Populate these variables on multi-player startup */
	public static final String HOST = "http://gautam.cc:9000/games";
	public static String GAME_PIN = "1234";

	private HashMap<String, Integer> ids = new HashMap<>();

	public static ArrayList<String> state;
	public Board board = Main.board;

	public Network() {
		state = new ArrayList<>();
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

	public void listenForNetworkChange() throws IOException, JSONException {
		/*
		 * Keep pulling the JSON data and listen for a change --> do something
		 */

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

		JSONObject obj = new JSONObject(result.toString());
		JSONObject arr = obj.optJSONObject(GAME_PIN);
		System.out.println(arr);

	}

}
