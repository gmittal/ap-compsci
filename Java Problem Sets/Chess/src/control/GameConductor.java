package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.json.JSONException;

import board.Board;
import board.Cell;
import pieces.Piece;

public class GameConductor implements MouseListener {

	public Board board;
	public Piece selectedPiece, whiteKing, blackKing;
	public Network network = new Network();
	public boolean side, mySide;
	public HashSet<Piece> whitePieces, blackPieces;
	private String[] notation;

	public GameConductor() {
		board = Main.board;
		notation = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
		whitePieces = new HashSet<>();
		blackPieces = new HashSet<>();
		mySide = false;
		side = false;

		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					try {
						ArrayList<String> pull = network.listenForNetworkChange();
						if (pull.size() != network.state.size()) {
							System.out.println("Network interface detected change.");
							network.state = pull;
							updateHandler();
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		t.start();

	}

	private void updateHandler() {
		executeNotationMove(network.state.get(network.state.size() - 1));
		nextTurn();
	}

	private void startListening() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				listen: while (true)
					try {
						try {
							ArrayList<String> pull = network.listenForNetworkChange();
							if (pull.size() == network.state.size() + 1) {
								System.out.println("Network interface detected change.");
								network.state = pull;
								updateHandler();
								break listen;
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});

		t.start();
	}

	private void nextTurn() {
		selectedPiece = null;
		side = !side;
		isGameOver();
		Main.window.f.setTitle("Chess - " + (side ? "Black's " : "White's ") + "Move");
		Main.window.repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / 64;
		int y = e.getY() / 64;

		if (selectedPiece != null && selectedPiece.getPossibleMoves().contains(board.getCell(x, y))) {
			try {
				network.sendLocalChange("\"" + (side ? "Black " : "White ") + selectedPiece.getClass().getSimpleName()
						+ " " + getChessNotation(selectedPiece.location) + " " + getChessNotation(board.getCell(x, y))
						+ "\"");
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} // tell the Network that something happened
			selectedPiece.move(board.getCell(x, y));
			nextTurn();
			startListening();
		}

		if (board.getCell(x, y).piece != null && board.getCell(x, y).piece.side == side && side == mySide)
			selectedPiece = board.getCell(x, y).piece;

		Main.window.repaint();

	}

	private void executeNotationMove(String move) {

		String[] parts = move.split(" ");
		Piece movedPiece = notationToCell(parts[2]).piece;
		movedPiece.move(notationToCell(parts[3]));
		nextTurn();

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

		if ((side ? getAllMovesWithoutCheck(false).contains(blackKing.location)
				: getAllMovesWithoutCheck(true).contains(whiteKing.location)) && getAllMoves(side).isEmpty()) {
			System.out.println("Checkmate");
			return true;
		}
		if (getAllMoves(side).isEmpty()) {
			System.out.println("Stalemate");
			return true;
		}

		return false;
	}

	private String getChessNotation(Cell c) {
		return notation[c.x] + Integer.toString(8 - c.y);
	}

	private Cell notationToCell(String s) {

		String[] parts = s.split("");
		return board.getCell(Arrays.asList(notation).indexOf(parts[0]), 8 - Integer.parseInt(parts[1]));
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
