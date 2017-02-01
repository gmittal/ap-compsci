package control;

import board.Board;

public class Main {

	public static Board board;
	public static Window window;
	public static GameConductor gc;

	public static void main(String[] args) {
		board = new Board();
		board.placePieces();
		gc = new GameConductor();
		window = new Window();
	}

}
