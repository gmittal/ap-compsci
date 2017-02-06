package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import board.Board;
import board.Cell;
import pieces.Piece;

public class GameConductor implements MouseListener {

	public Board board;
	public Piece selectedPiece, whiteKing, blackKing;
	public Network network = new Network();
	public boolean side;
	public HashSet<Piece> whitePieces, blackPieces;

	public GameConductor() {
		board = Main.board;
		whitePieces = new HashSet<>();
		blackPieces = new HashSet<>();
		side = false;
		network.updateState();
	}

	private void nextTurn() {
		selectedPiece = null;
		side = !side;
		isGameOver();
		Main.window.f.setTitle("Chess - " + (side ? "Black's " : "White's ") + "Move");
		Main.window.repaint();
		network.sendLocalChange(); // tell the Network that something happened

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / 64;
		int y = e.getY() / 64;

		if (selectedPiece != null && selectedPiece.getPossibleMoves().contains(board.getCell(x, y))) {
			selectedPiece.move(board.getCell(x, y));
			nextTurn();
		}

		if (board.getCell(x, y).piece != null && board.getCell(x, y).piece.side == side)
			selectedPiece = board.getCell(x, y).piece;

		Main.window.repaint();

	}

	public HashSet<Cell> getAllMovesWithoutCheck(boolean s) {
		HashSet<Cell> moves = new HashSet<>();

		for (Piece p : (s ? blackPieces : whitePieces))
			moves.addAll(p.getPossibleMovesWithoutCheck());

		return moves;

	}

	public HashSet<Cell> getAllMoves(boolean s) {
		HashSet<Cell> moves = new HashSet<>();

		for (Piece p : (s ? blackPieces : whitePieces))
			moves.addAll(p.getPossibleMoves());

		return moves;

	}

	public boolean isMoveLegal(Piece p, Cell c) {
		Cell oldCell = p.location;
		Piece oldPiece = c.piece;

		p.move(c);

		boolean legal = isBoardLegal();

		p.move(oldCell);
		c.piece = oldPiece;
		if (oldPiece != null)
			(oldPiece.side ? Main.gc.blackPieces : Main.gc.whitePieces).add(oldPiece);

		return legal;
	}

	public boolean isBoardLegal() {

		return side ? !getAllMovesWithoutCheck(false).contains(blackKing.location)
				: !getAllMovesWithoutCheck(true).contains(whiteKing.location);
	}

	public boolean isGameOver() {
		boolean over = false;

		if ((side ? getAllMovesWithoutCheck(false).contains(blackKing.location)
				: getAllMovesWithoutCheck(true).contains(whiteKing.location)) && !getAllMoves(side).isEmpty())
			System.out.println("Game Over");

		return over;
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
