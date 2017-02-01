package control;

import board.Board;
import pieces.Piece;

public class Network {
	public static String[][] state;
	public Board board = Main.board;

	public Network() {
		state = new String[8][8];
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

	public void sendLocalChange() {
		updateState();

		/* Send the state to the Internet here */

	}

	public void listenForNetworkChange() {
		/*
		 * Keep pulling the JSON data and listen for a change --> do something
		 */
	}

}
