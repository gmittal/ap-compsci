package board;

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

}
