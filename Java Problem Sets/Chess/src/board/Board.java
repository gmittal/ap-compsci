package board;

import java.awt.Graphics;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class Board {

	public Cell[][] cells;

	public Board() {

		cells = new Cell[8][8];

		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				cells[x][y] = new Cell(x, y, this);

	}

	public Cell getCell(int x, int y) {
		try {
			return cells[x][y];
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public void placePieces() {

		for (int x = 0; x <= 7; x++) { // place pawns
			cells[x][1].piece = new Pawn(cells[x][1], true);
			cells[x][6].piece = new Pawn(cells[x][6], false);
		}

		cells[0][0].piece = new Rook(cells[0][0], true);
		cells[1][0].piece = new Knight(cells[1][0], true);
		cells[2][0].piece = new Bishop(cells[2][0], true);
		cells[3][0].piece = new Queen(cells[3][0], true);
		cells[4][0].piece = new King(cells[4][0], true);
		cells[5][0].piece = new Bishop(cells[5][0], true);
		cells[6][0].piece = new Knight(cells[6][0], true);
		cells[7][0].piece = new Rook(cells[7][0], true);

		cells[0][7].piece = new Rook(cells[0][7], false);
		cells[1][7].piece = new Knight(cells[1][7], false);
		cells[2][7].piece = new Bishop(cells[2][7], false);
		cells[3][7].piece = new Queen(cells[3][7], false);
		cells[4][7].piece = new King(cells[4][7], false);
		cells[5][7].piece = new Bishop(cells[5][7], false);
		cells[6][7].piece = new Knight(cells[6][7], false);
		cells[7][7].piece = new Rook(cells[7][7], false);
	}

	public void draw(Graphics g) {
		for (Cell[] col : cells)
			for (Cell c : col)
				c.draw(g);
	}

}
