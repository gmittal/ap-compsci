package control;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;
import pieces.Piece;

public class Window extends JPanel implements ActionListener, MouseListener {

	private Board board;
	private Piece selectedPiece;

	public Window(Board b) {

		board = b;

		JFrame f = new JFrame("Chess");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		setPreferredSize(new Dimension(512, 512));
		f.pack();
		f.setVisible(true);
		
		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		board.draw(g);
		
		if (selectedPiece != null) {
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / 64;
		int y = e.getY() / 64;

		System.out.println(x + ", " + y);
		
		selectedPiece = board.getCell(x, y).piece;

		String currentCell = selectedPiece != null
				? (selectedPiece.side ? "Black " : "White ") + selectedPiece.getClass().getSimpleName() : "Empty";
		System.out.println(currentCell);
		
		if (selectedPiece != null)
		{
			System.out.println(selectedPiece.getPossibleMoves().size());
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
