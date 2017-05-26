package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JOptionPane;

import org.json.JSONException;

import board.Board;
import board.Cell;
import pieces.King;
import pieces.Piece;

public class GameConductor implements MouseListener {

	public Board board;
	public Piece selectedPiece, whiteKing, blackKing;
	public Network network;
	public boolean side, mySide;
	public HashSet<Piece> whitePieces, blackPieces;
	private String[] notation;

	public GameConductor() {
		board = Main.board;
		network = new Network(determinePin());
		notation = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
		whitePieces = new HashSet<>();
		blackPieces = new HashSet<>();
		side = false;

	}

	private int determinePin() {
		Object[] answer = { "New Game", "Join Game" };
		int input = JOptionPane.showOptionDialog(null, "Start a new game or join an existing one", "Start game",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, answer, answer[0]);
		if (input == JOptionPane.YES_OPTION) {
			try {
				int pin = Network.startNewGame();
				JOptionPane.showMessageDialog(null, "Your game pin is " + pin, "Start game",
						JOptionPane.INFORMATION_MESSAGE, null);
				mySide = false;
				return pin;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (input == JOptionPane.NO_OPTION) {
			mySide = true;
			return Integer.parseInt(
					JOptionPane.showInputDialog(null, "Enter pin", "Start game", JOptionPane.PLAIN_MESSAGE), 10);
		}

		return 0;
	}

	private void updateHandler() {
		executeNotationMove(network.state.get(network.state.size() - 1));
		nextTurn();
	}

	public void startListening() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				listen: while (true)
					try {
						try {
							ArrayList<String> pull = network.listenForNetworkChange();
							if (pull.size() == network.state.size() + 1) {
								System.out.println("Network interface detected change.");
								network.state.add('"' + pull.get(pull.size() - 1) + '"');
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
		Main.window.f.setTitle("Chess - " + (side ? "Black's " : "White's ") + "Move    Pin:" + network.GAME_PIN);
		Main.window.repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX() / 64;
		int y = e.getY() / 64;
		Cell c = board.getCell(x, y);

		if (selectedPiece != null && selectedPiece.getPossibleMoves().contains(c)) {
			try {
				network.sendLocalChange("\"" + (side ? "Black " : "White ") + selectedPiece.getClass().getSimpleName()
						+ " " + getChessNotation(selectedPiece.location) + " " + getChessNotation(c) + "\"");
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} // tell the Network that something happened

			if (selectedPiece instanceof King) {
				if (c.x - selectedPiece.location.x == 2) {
					board.getCell(selectedPiece.location.x + 3, selectedPiece.location.y).piece
							.move(board.getCell(selectedPiece.location.x + 1, selectedPiece.location.y));
				} else if (c.x - selectedPiece.location.x == -2) {
					board.getCell(selectedPiece.location.x - 4, selectedPiece.location.y).piece
							.move(board.getCell(selectedPiece.location.x - 1, selectedPiece.location.y));
				}
			}

			// TODO add en passant stuff

			selectedPiece.move(c);

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
		if (movedPiece instanceof King) {
			if (notationToCell(parts[3]).x - movedPiece.location.x == 2) {
				board.getCell(movedPiece.location.x + 3, movedPiece.location.y).piece
						.move(board.getCell(movedPiece.location.x + 1, movedPiece.location.y));
			} else if (notationToCell(parts[3]).x - movedPiece.location.x == -2) {
				board.getCell(movedPiece.location.x - 4, movedPiece.location.y).piece
						.move(board.getCell(movedPiece.location.x - 1, movedPiece.location.y));
			}
		}
		movedPiece.move(notationToCell(parts[3]));

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

	public Cell notationToCell(String s) {

		String[] parts = s.split("");
		return board.getCell(Arrays.asList(notation).indexOf(parts[0]), 8 - Integer.parseInt(parts[1]));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
