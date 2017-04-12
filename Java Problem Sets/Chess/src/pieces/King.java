package pieces;

import java.util.HashSet;

import board.Cell;
import control.Main;

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

		addCastling(possibleMoves);

		removeIllegalMoves(possibleMoves);

		return possibleMoves;
	}

	public HashSet<Cell> getPossibleMovesWithoutCheck() {
		HashSet<Cell> possibleMoves = new HashSet<>();

		for (int x = -1; x <= 1; x++)
			for (int y = -1; y <= 1; y++) {
				Cell c = getBoard().getCell(location.x + x, location.y + y);
				if (c != null && !c.isSameSide(side))
					possibleMoves.add(c);
			}

		return possibleMoves;
	}

	private void addCastling(HashSet<Cell> possibleMoves) {

		for (String s : Main.gc.network.state) {
			String[] parts = s.split(" ");
			if (Main.gc.notationToCell(parts[3]) == location)
				return;
		}

		rightCastle: if (getBoard().getCell(location.x + 1, location.y) != null
				&& getBoard().getCell(location.x + 1, location.y).piece == null
				&& getBoard().getCell(location.x + 2, location.y) != null
				&& getBoard().getCell(location.x + 2, location.y).piece == null) {
			for (String s : Main.gc.network.state) {
				String[] parts = s.split(" ");
				if (Main.gc.notationToCell(parts[2]) == getBoard().getCell(location.x + 3, location.y))
					break rightCastle;
				if (Main.gc.notationToCell(parts[3]) == getBoard().getCell(location.x + 3, location.y))
					break rightCastle;
			}
			if (!Main.gc.isMoveLegal(this, getBoard().getCell(location.x + 1, location.y)))
				break rightCastle;

			possibleMoves.add(getBoard().getCell(location.x + 2, location.y));

		}

		leftCastle: if (getBoard().getCell(location.x - 1, location.y) != null
				&& getBoard().getCell(location.x - 1, location.y).piece == null
				&& getBoard().getCell(location.x - 2, location.y) != null
				&& getBoard().getCell(location.x - 2, location.y).piece == null
				&& getBoard().getCell(location.x - 3, location.y) != null
				&& getBoard().getCell(location.x - 3, location.y).piece == null) {
			for (String s : Main.gc.network.state) {
				String[] parts = s.split(" ");
				if (Main.gc.notationToCell(parts[2]) == getBoard().getCell(location.x - 4, location.y))
					break leftCastle;
				if (Main.gc.notationToCell(parts[3]) == getBoard().getCell(location.x - 4, location.y))
					break leftCastle;
			}
			if (!Main.gc.isMoveLegal(this, getBoard().getCell(location.x - 1, location.y)))
				break leftCastle;

			possibleMoves.add(getBoard().getCell(location.x - 2, location.y));

		}

	}

}
