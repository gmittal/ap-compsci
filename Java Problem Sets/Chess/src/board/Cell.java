package board;

import java.awt.Color;
import java.awt.Graphics;

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

	public void draw(Graphics g) {
		if ((x + y) % 2 == 0)
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.BLACK);

		g.fillRect(x * 64, y * 64, 64, 64);

		if (piece != null)
			piece.draw(g);
	}

}
