package pieces;

import java.util.HashSet;

import board.Cell;

public class Rook extends Piece {

	public Rook(Cell l, boolean s) {
		super(l, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashSet<Cell> getPossibleMoves() {
		HashSet<Cell> possibleMoves = new HashSet<>();

		for (int x = 1; x <= 7; x++) {
			Cell c = getBoard().getCell(location.x + x, location.y);
			if (c != null && !c.isSameSide(side)) {
				possibleMoves.add(c);
				if (c.piece != null && c.piece.side != side)
					break;
			} else
				break;
		}

		for (int x = 1; x <= 7; x++) {
			Cell c = getBoard().getCell(location.x - x, location.y);
			if (c != null && !c.isSameSide(side)) {
				possibleMoves.add(c);
				if (c.piece != null && c.piece.side != side)
					break;
			} else
				break;
		}

		for (int y = 1; y <= 7; y++) {
			Cell c = getBoard().getCell(location.x, location.y + y);
			if (c != null && !c.isSameSide(side)) {
				possibleMoves.add(c);
				if (c.piece != null && c.piece.side != side)
					break;
			} else
				break;
		}

		for (int y = 1; y <= 7; y++) {
			Cell c = getBoard().getCell(location.x, location.y - y);
			if (c != null && !c.isSameSide(side)) {
				possibleMoves.add(c);
				if (c.piece != null && c.piece.side != side)
					break;
			} else
				break;
		}

		removeIllegalMoves(possibleMoves);

		return possibleMoves;
	}

	@Override
	public HashSet<Cell> getPossibleMovesWithoutCheck() {
		HashSet<Cell> possibleMoves = new HashSet<>();

		for (int x = 1; x <= 7; x++) {
			Cell c = getBoard().getCell(location.x + x, location.y);
			if (c != null && !c.isSameSide(side)) {
				possibleMoves.add(c);
				if (c.piece != null && c.piece.side != side)
					break;
			} else
				break;
		}

		for (int x = 1; x <= 7; x++) {
			Cell c = getBoard().getCell(location.x - x, location.y);
			if (c != null && !c.isSameSide(side)) {
				possibleMoves.add(c);
				if (c.piece != null && c.piece.side != side)
					break;
			} else
				break;
		}

		for (int y = 1; y <= 7; y++) {
			Cell c = getBoard().getCell(location.x, location.y + y);
			if (c != null && !c.isSameSide(side)) {
				possibleMoves.add(c);
				if (c.piece != null && c.piece.side != side)
					break;
			} else
				break;
		}

		for (int y = 1; y <= 7; y++) {
			Cell c = getBoard().getCell(location.x, location.y - y);
			if (c != null && !c.isSameSide(side)) {
				possibleMoves.add(c);
				if (c.piece != null && c.piece.side != side)
					break;
			} else
				break;
		}

		return possibleMoves;
	}

}
