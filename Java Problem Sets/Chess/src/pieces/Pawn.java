package pieces;

import java.util.HashSet;

import board.Cell;
import control.Main;

public class Pawn extends Piece {

	public Pawn(Cell l, boolean s) {
		super(l, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashSet<Cell> getPossibleMoves() {
		HashSet<Cell> possibleMoves = new HashSet<>();

		if (side) {
			Cell c = getBoard().getCell(location.x, location.y + 1);
			if (c != null && c.piece == null) {
				possibleMoves.add(c);

				if (location.y == 1) {
					c = getBoard().getCell(location.x, location.y + 2);
					if (c != null && c.piece == null)
						possibleMoves.add(c);
				}
			}

			c = getBoard().getCell(location.x + 1, location.y + 1);
			if (c != null && c.piece != null && c.piece.side != side)
				possibleMoves.add(c);

			c = getBoard().getCell(location.x - 1, location.y + 1);
			if (c != null && c.piece != null && c.piece.side != side)
				possibleMoves.add(c);

		} else {
			Cell c = getBoard().getCell(location.x, location.y - 1);
			if (c != null && c.piece == null) {
				possibleMoves.add(c);

				if (location.y == 6) {
					c = getBoard().getCell(location.x, location.y - 2);
					if (c != null && c.piece == null)
						possibleMoves.add(c);
				}
			}

			c = getBoard().getCell(location.x + 1, location.y - 1);
			if (c != null && c.piece != null && c.piece.side != side)
				possibleMoves.add(c);

			c = getBoard().getCell(location.x - 1, location.y - 1);
			if (c != null && c.piece != null && c.piece.side != side)
				possibleMoves.add(c);

		}

		addEnPassant(possibleMoves);

		removeIllegalMoves(possibleMoves);

		return possibleMoves;
	}

	@Override
	public HashSet<Cell> getPossibleMovesWithoutCheck() {
		HashSet<Cell> possibleMoves = new HashSet<>();

		if (side) {
			Cell c = getBoard().getCell(location.x, location.y + 1);
			if (c != null && c.piece == null)
				possibleMoves.add(c);

			if (location.y == 1) {
				c = getBoard().getCell(location.x, location.y + 2);
				if (c != null && c.piece == null)
					possibleMoves.add(c);
			}

			c = getBoard().getCell(location.x + 1, location.y + 1);
			if (c != null && c.piece != null && c.piece.side != side)
				possibleMoves.add(c);

			c = getBoard().getCell(location.x - 1, location.y + 1);
			if (c != null && c.piece != null && c.piece.side != side)
				possibleMoves.add(c);

		} else {
			Cell c = getBoard().getCell(location.x, location.y - 1);
			if (c != null && c.piece == null)
				possibleMoves.add(c);

			if (location.y == 6) {
				c = getBoard().getCell(location.x, location.y - 2);
				if (c != null && c.piece == null)
					possibleMoves.add(c);
			}

			c = getBoard().getCell(location.x + 1, location.y - 1);
			if (c != null && c.piece != null && c.piece.side != side)
				possibleMoves.add(c);

			c = getBoard().getCell(location.x - 1, location.y - 1);
			if (c != null && c.piece != null && c.piece.side != side)
				possibleMoves.add(c);

		}

		return possibleMoves;
	}

	private void addEnPassant(HashSet<Cell> possibleMoves) {
		if (Main.gc.network.state.isEmpty())
			return;

		String[] parts = Main.gc.network.state.get(Main.gc.network.state.size() - 1).split(" ");

		if (!parts[1].equals("Pawn"))
			return;

		if ((Main.gc.notationToCell(parts[3]) == getBoard().getCell(location.x + 1, location.y))
				&& (Main.gc.notationToCell(parts[3]) == getBoard().getCell(location.x + 1, location.y - 2))) {
			possibleMoves.add(getBoard().getCell(location.x + 1, location.y - 1));
		}
		if ((Main.gc.notationToCell(parts[3]) == getBoard().getCell(location.x - 1, location.y))
				&& (Main.gc.notationToCell(parts[3]) == getBoard().getCell(location.x - 1, location.y - 2))) {
			possibleMoves.add(getBoard().getCell(location.x - 1, location.y - 1));
		}
	}

}
