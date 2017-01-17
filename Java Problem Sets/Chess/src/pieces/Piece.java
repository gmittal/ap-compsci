package pieces;

import java.util.HashSet;

import board.Board;
import board.Cell;

public abstract class Piece {

	public boolean side; // true is black false is white
	public Cell location;

	public Board getBoard() {
		return location.board;
	}

	public abstract HashSet<Cell> getPossibleMoves();

	public void move(Cell c) {
		location.piece = null;
		c.piece = this;
		location = c;
	}

}
