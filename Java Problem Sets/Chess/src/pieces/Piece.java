package pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.imageio.ImageIO;

import board.Board;
import board.Cell;
import control.Main;

public abstract class Piece {

	public boolean side; // true is black false is white
	public Cell location;
	private Image img;

	public Piece(Cell l, boolean s) {
		location = l;
		side = s;
		try {
			img = ImageIO.read(getClass().getResource("/Piece Images/"
					+ (side ? "Black" : "White") + this.getClass().getSimpleName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Board getBoard() {
		return Main.board;
	}

	public abstract HashSet<Cell> getPossibleMoves();

	public abstract HashSet<Cell> getPossibleMovesWithoutCheck();

	public void removeIllegalMoves(HashSet<Cell> possibleMoves) {
		Iterator<Cell> iter = possibleMoves.iterator();

		while (iter.hasNext()) {
			Cell c = iter.next();
			if (!Main.gc.isMoveLegal(this, c))
				iter.remove();

		}
	}

	public void move(Cell c) {
		location.piece = null;
		if (c.piece != null)
			c.piece.taken();
		c.piece = this;
		location = c;
	}

	public void taken() {
		location.piece = null;
		(side ? Main.gc.blackPieces : Main.gc.whitePieces).remove(this);
	}

	public void draw(Graphics g) {

		g.drawImage(img, location.x * 64 + 2, location.y * 64 + 2, null);

	}

}
