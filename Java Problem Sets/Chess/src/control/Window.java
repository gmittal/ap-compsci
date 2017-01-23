package control;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;

public class Window extends JPanel {

	private Board board;

	public Window(Board b) {

		board = b;

		JFrame f = new JFrame("Chess");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		setPreferredSize(new Dimension(512, 512));
		f.pack();
		f.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		board.draw(g);
	}
}
