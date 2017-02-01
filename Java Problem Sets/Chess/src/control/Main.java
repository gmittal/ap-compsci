package control;

import board.Board;

public class Main {
	
	public static Board board;
	public static Window window;

	public static void main(String[] args) {
		board = new Board();
		board.placePieces();
		window = new Window();
	}

}
