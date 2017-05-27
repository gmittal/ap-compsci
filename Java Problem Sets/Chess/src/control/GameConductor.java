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
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class GameConductor implements MouseListener {

	public Board board;
	public Piece selectedPiece, whiteKing, blackKing;
	public Network network;
	public boolean side, mySide;
	public HashSet<Piece> whitePieces, blackPieces;
	private String[] notation;

	public GameConductor() {
		board = Main.board;
		int pin = determinePin();
		if (pin != -1)
			network = new Network(pin);
		else 
			network = new LocalNetwork();
		notation = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
		whitePieces = new HashSet<>();
		blackPieces = new HashSet<>();
		side = false;

	}

	private int determinePin() {
		Object[] answer = { "New Game", "Join Game", "Local Game" };
		int input = JOptionPane.showOptionDialog(null, "Start a new game or join an existing one", "Start game",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, answer, answer[0]);

		if (input == JOptionPane.YES_OPTION) {
			try {
				int pin = Network.startNewGame();
				JOptionPane.showMessageDialog(null, "Your game pin is " + pin + ". You will play as White.", "Start Game",
						JOptionPane.INFORMATION_MESSAGE, null);
				mySide = false;
				return pin;
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		} else if (input == JOptionPane.NO_OPTION) {
			mySide = true;
			return Integer.parseInt(
					JOptionPane.showInputDialog(null, "Enter pin. You will play as Black.", "Start Game", JOptionPane.PLAIN_MESSAGE), 10);
		} else if (input == JOptionPane.CANCEL_OPTION) {
			return -1;
		}else {
			System.exit(0);
		}
		
		return -1;
		
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
//								System.out.println("Network interface detected change.");
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
		Main.window.f.setTitle("Chess - " + (side ? "Black's " : "White's ") + "Move [" + network.GAME_PIN + "]");
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
			
			if (selectedPiece instanceof Pawn) {
				if (c.x-selectedPiece.location.x != 0 && c.piece == null)
					board.getCell(c.x, selectedPiece.location.y).piece.taken();
			}

			selectedPiece.move(c);
			
			if (selectedPiece instanceof Pawn) {
				if (c.y == 0 || c.y == 7) {
					Object[] options = {"Queen", "Rook", "Knight", "Bishop"};
					int input = JOptionPane.showOptionDialog(null, "What would you like to promote your pawn to?", "Pawn Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					switch (input) {
					case 0:
						selectedPiece.taken();
						c.piece = new Queen(c, selectedPiece.side);
						break;
					case 1:
						selectedPiece.taken();
						c.piece = new Rook(c, selectedPiece.side);
						break;
					case 2:
						selectedPiece.taken();
						c.piece = new Knight(c, selectedPiece.side);
						break;
					case 3:
						selectedPiece.taken();
						c.piece = new Bishop(c, selectedPiece.side);
						break;						
					}
					(selectedPiece.side ? blackPieces : whitePieces).add(c.piece);
				}
			}

			nextTurn();
			startListening();
		}

		if (board.getCell(x, y).piece != null && board.getCell(x, y).piece.side == side && (side == mySide || network instanceof LocalNetwork))
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
		if (movedPiece instanceof Pawn) {
			if (notationToCell(parts[3]).x-movedPiece.location.x != 0 && notationToCell(parts[3]).piece == null)
				board.getCell(notationToCell(parts[3]).x, movedPiece.location.y).piece.taken();
		}
		
		movedPiece.move(notationToCell(parts[3]));
		
		if (movedPiece instanceof Pawn) {
			if (notationToCell(parts[3]).y == 0 || notationToCell(parts[3]).y == 7) {
				Object[] options = {"Queen", "Rook", "Knight", "Bishop"};
				int input = JOptionPane.showOptionDialog(null, "What would you like to promote your pawn to?", "Pawn Promotion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				switch (input) {
				case 0:
					movedPiece.taken();
					notationToCell(parts[3]).piece = new Queen(notationToCell(parts[3]), movedPiece.side);
					break;
				case 1:
					movedPiece.taken();
					notationToCell(parts[3]).piece = new Rook(notationToCell(parts[3]), movedPiece.side);
					break;
				case 2:
					movedPiece.taken();
					notationToCell(parts[3]).piece = new Knight(notationToCell(parts[3]), movedPiece.side);
					break;
				case 3:
					movedPiece.taken();
					notationToCell(parts[3]).piece = new Bishop(notationToCell(parts[3]), movedPiece.side);
					break;						
				}
				(movedPiece.side ? blackPieces : whitePieces).add(notationToCell(parts[3]).piece);
			}
		}

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
