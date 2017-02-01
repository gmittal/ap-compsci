package control;

import board.Board;
import pieces.Piece;

public class Network {
	public Board board;

	public Network(Board b) {
		board = b;
	}

	public String[][] getEncodedState() {
		String[][] state = new String[8][8];
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

}
