package pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashSet;

import board.Board;
import board.Cell;

public abstract class Piece {

	public boolean side; // true is black false is white
	public Cell location;
	private Image img;

	public Piece(Cell l, boolean s) {
		location = l;
		side = s;
	}

	public Board getBoard() {
		return location.board;
	}

	public abstract HashSet<Cell> getPossibleMoves();

	public void move(Cell c) {
		location.piece = null;
		c.piece = this;
		location = c;
	}

	public void draw(Graphics g) {
		g.drawImage(img, location.x * 64, location.y * 64, null);
	}

}
