package board;

import pieces.Piece;

public class Cell {

	public int x, y;
	public Piece piece;
	public Board board;

	public Cell(int x, int y, Board b) {

		board = b;
		this.x = x;
		this.y = y;
		piece = null;

	}

	public boolean isSameSide(boolean s) {
		if (piece == null)
			return false;

		return piece.side == s;
	}

}
