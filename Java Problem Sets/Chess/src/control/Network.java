package control;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import board.Board;
import pieces.Piece;

public class Network {
	/* Populate these variables on multiplayer startup */
	public static String HOST = "http://c409f780.ngrok.io/games";
	public static int GAME_PIN = 1234;

	private HashMap<String, Integer> ids = new HashMap<>();

	public static String[][] state;
	public Board board = Main.board;

	public Network() {
		state = new String[8][8];
		updateState();
	}

	public String[][] updateState() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece selectedPiece = board.getCell(i, j).piece;
				String cellID = selectedPiece != null
						? (selectedPiece.side ? "Black " : "White ") + selectedPiece.getClass().getSimpleName()
						: "Empty";
				state[i][j] = cellID;
			}
		}

		return state;
	}

	private String StateToString(Object[][] array) {
		String line = ",";
		StringBuilder sb = new StringBuilder();

		for (Object[] row : array) {
			sb.append(Arrays.toString(row)).append(line);
		}

		return "[" + sb.toString().substring(0, sb.toString().length() - 1) + "]";
	}

	public void sendLocalChange() throws IOException {
		updateState();

		URL url = new URL(HOST);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
		System.out.println(String.format("{\"" + GAME_PIN + "\":%s}", StateToString(state)));
		/*
		 * the above does not currently work because each of the individual
		 * objects need to be wrapped in quotes.
		 */
		osw.write(String.format("{\"" + GAME_PIN + "\":%s}", StateToString(state)));
		osw.flush();
		osw.close();
		System.err.println(connection.getResponseCode());

	}

	public void listenForNetworkChange() {
		/*
		 * Keep pulling the JSON data and listen for a change --> do something
		 */
	}

}
