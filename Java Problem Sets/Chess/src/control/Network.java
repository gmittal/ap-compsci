package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import board.Board;
import pieces.Piece;

public class Network implements ActionListener {
	private static String[][] state;
	public Board board = Main.board;

	public Network() {
		state = new String[8][8];
	}

	public String[][] getState() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
