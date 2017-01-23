package pieces;

import java.util.HashSet;

import board.Cell;

public class King extends Piece {

	public King(Cell l, boolean s) {
		super(l, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashSet<Cell> getPossibleMoves() {
		HashSet<Cell> possibleMoves = new HashSet<>();

		for (int x = -1; x <= 1; x++)
			for (int y = -1; y <= 1; y++) {
				Cell c = getBoard().getCell(location.x + x, location.y + y);
				if (c != null && !c.isSameSide(side))
					possibleMoves.add(c);
			}

		return possibleMoves;
	}

}
