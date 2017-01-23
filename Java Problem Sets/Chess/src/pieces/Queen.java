package pieces;

import java.util.HashSet;

import board.Cell;

public class Queen extends Piece {

	public Queen(Cell l, boolean s) {
		super(l, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashSet<Cell> getPossibleMoves() {
		HashSet<Cell> possibleMoves = new HashSet<>();

		for (int x = 0; x <= 7; x++) {
			Cell c = getBoard().getCell(location.x + x, location.y);
			if (c != null && !c.isSameSide(side))
				possibleMoves.add(c);
			else
				break;
		}

		for (int x = 0; x <= 7; x++) {
			Cell c = getBoard().getCell(location.x - x, location.y);
			if (c != null && !c.isSameSide(side))
				possibleMoves.add(c);
			else
				break;
		}

		for (int y = 0; y <= 7; y++) {
			Cell c = getBoard().getCell(location.x, location.y + y);
			if (c != null && !c.isSameSide(side))
				possibleMoves.add(c);
			else
				break;
		}

		for (int y = 0; y <= 7; y++) {
			Cell c = getBoard().getCell(location.x, location.y - y);
			if (c != null && !c.isSameSide(side))
				possibleMoves.add(c);
			else
				break;
		}

		for (int i = 0; i <= 7; i++) {
			Cell c = getBoard().getCell(location.x + i, location.y + i);
			if (c != null && !c.isSameSide(side))
				possibleMoves.add(c);
			else
				break;
		}

		for (int i = 0; i <= 7; i++) {
			Cell c = getBoard().getCell(location.x + i, location.y - i);
			if (c != null && !c.isSameSide(side))
				possibleMoves.add(c);
			else
				break;
		}

		for (int i = 0; i <= 7; i++) {
			Cell c = getBoard().getCell(location.x - i, location.y + i);
			if (c != null && !c.isSameSide(side))
				possibleMoves.add(c);
			else
				break;
		}

		for (int i = 0; i <= 7; i++) {
			Cell c = getBoard().getCell(location.x - i, location.y - i);
			if (c != null && !c.isSameSide(side))
				possibleMoves.add(c);
			else
				break;
		}

		return possibleMoves;
	}

}
