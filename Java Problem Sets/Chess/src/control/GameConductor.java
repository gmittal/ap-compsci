package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import board.Board;
import pieces.Piece;

public class GameConductor implements MouseListener {

	public Board board;
	public Piece selectedPiece;
	public Network network = new Network();

	public GameConductor() {
		board = Main.board;
		network.updateState();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / 64;
		int y = e.getY() / 64;

		if (selectedPiece != null && selectedPiece.getPossibleMoves().contains(board.getCell(x, y))) {
			selectedPiece.move(board.getCell(x, y));
			selectedPiece = null;
			Main.window.repaint();
			return;
		}

		selectedPiece = board.getCell(x, y).piece;

		Main.window.repaint();
		network.sendLocalChange(); // tell the Network that something happened

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
