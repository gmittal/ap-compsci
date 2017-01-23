package pieces;

import java.util.HashSet;

import board.Cell;

public class Knight extends Piece {

	public Knight(Cell l, boolean s) {
		super(l, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashSet<Cell> getPossibleMoves() {
		HashSet<Cell> possibleMoves = new HashSet<>();

		Cell c = getBoard().getCell(location.x + 2, location.y + 1);
		if (c != null && !c.isSameSide(side))
			possibleMoves.add(c);
		c = getBoard().getCell(location.x + 2, location.y - 1);
		if (c != null && !c.isSameSide(side))
			possibleMoves.add(c);
		c = getBoard().getCell(location.x - 2, location.y + 1);
		if (c != null && !c.isSameSide(side))
			possibleMoves.add(c);
		c = getBoard().getCell(location.x - 2, location.y - 1);
		if (c != null && !c.isSameSide(side))
			possibleMoves.add(c);
		c = getBoard().getCell(location.x + 1, location.y + 2);
		if (c != null && !c.isSameSide(side))
			possibleMoves.add(c);
		c = getBoard().getCell(location.x + 1, location.y - 2);
		if (c != null && !c.isSameSide(side))
			possibleMoves.add(c);
		c = getBoard().getCell(location.x - 1, location.y + 2);
		if (c != null && !c.isSameSide(side))
			possibleMoves.add(c);
		c = getBoard().getCell(location.x - 1, location.y - 2);
		if (c != null && !c.isSameSide(side))
			possibleMoves.add(c);

		return possibleMoves;
	}

}
