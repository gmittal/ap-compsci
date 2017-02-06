package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import board.Board;
import board.Cell;
import pieces.King;
import pieces.Piece;

public class GameConductor implements MouseListener {

	public Board board;
	public Piece selectedPiece;
	public Network network = new Network();
	public boolean side;

	public GameConductor() {
		board = Main.board;
		side = false;
		network.updateState();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / 64;
		int y = e.getY() / 64;

		if (selectedPiece != null && selectedPiece.getPossibleMoves().contains(board.getCell(x, y))) {
			selectedPiece.move(board.getCell(x, y));
			selectedPiece = null;
			side = !side;
			Main.window.f.setTitle("Chess - " + (side ? "Black's " : "White's ") + "Move");
			Main.window.repaint();
			return;
		}

		if (board.getCell(x, y).piece != null && board.getCell(x, y).piece.side == side)
			selectedPiece = board.getCell(x, y).piece;

		Main.window.repaint();
		network.sendLocalChange(); // tell the Network that something happened

	}

	public HashSet<Cell> getAllMoves(boolean s) {
		HashSet<Cell> moves = new HashSet<>();

		for (Cell[] col : board.cells)
			for (Cell c : col) {
				if (c.piece != null && c.piece.side == s)
					if (!(c.piece instanceof King))
						moves.addAll(c.piece.getPossibleMoves());
					else {
						moves.addAll(((King) c.piece).getPossibleMovesWithoutCheck());
					}
			}

		return moves;
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
