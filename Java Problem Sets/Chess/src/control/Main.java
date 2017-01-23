package control;

import board.Board;

public class Main {

	public static void main(String[] args) {
		Board b = new Board();
		b.placePieces();

		Window w = new Window(b);

	}

}
