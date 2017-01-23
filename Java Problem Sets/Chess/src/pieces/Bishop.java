package pieces;

import java.util.HashSet;

import board.Cell;

public class Bishop extends Piece {

	public Bishop(Cell l, boolean s) {
		super(l, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashSet<Cell> getPossibleMoves() {
		HashSet<Cell> possibleMoves = new HashSet<>();

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
