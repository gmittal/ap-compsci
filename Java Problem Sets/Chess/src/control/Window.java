package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;
import board.Cell;
import pieces.Piece;

public class Window extends JPanel {

	private Board board;
	public JFrame f;

	public Window() {

		board = Main.board;

		f = new JFrame("Chess - White's Move [" + Main.gc.network.GAME_PIN + "]");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		setPreferredSize(new Dimension(512, 512));
		f.setResizable(false);
		f.pack();
		f.setVisible(true);

		addMouseListener(Main.gc);
	}

	public void paintComponent(Graphics g) {
		board.draw(g);

		if (Main.gc.selectedPiece != null) {
			Piece selectedPiece = Main.gc.selectedPiece;
			g.setColor(new Color(0, 255, 0, 90));
			g.fillRect(selectedPiece.location.x * 64, selectedPiece.location.y * 64, 64, 64);
			for (Cell c : selectedPiece.getPossibleMoves()) {
				g.fillRect(c.x * 64, c.y * 64, 64, 64);
			}
		}
	}

}
