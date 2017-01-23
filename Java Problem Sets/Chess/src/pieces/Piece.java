package pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;

import board.Board;
import board.Cell;

public abstract class Piece {

	public boolean side; // true is black false is white
	public Cell location;
	private Image img;

	public Piece(Cell l, boolean s) {
		location = l;
		side = s;
		try {
			img = ImageIO.read(new File(System.getProperty("user.dir") + "/src/Piece Images/"
					+ (side ? "Black" : "White") + this.getClass().getSimpleName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
