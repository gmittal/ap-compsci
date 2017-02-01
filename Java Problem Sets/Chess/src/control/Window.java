package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;
import board.Cell;
import pieces.Piece;

public class Window extends JPanel implements  MouseListener {

	private Board board;
	private Piece selectedPiece;

	public Window() {

		board = Main.board;

		JFrame f = new JFrame("Chess");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		setPreferredSize(new Dimension(512, 512));
		f.setResizable(false);
		f.pack();
		f.setVisible(true);
		
		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		board.draw(g);
		
		if (selectedPiece != null) {
			g.setColor(new Color(0, 255, 0, 90));
			g.fillRect(selectedPiece.location.x*64, selectedPiece.location.y*64, 64, 64);
			for (Cell c : selectedPiece.getPossibleMoves()) {
				g.fillRect(c.x*64, c.y*64, 64, 64);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / 64;
		int y = e.getY() / 64;
		
		if (selectedPiece != null && selectedPiece.getPossibleMoves().contains(board.getCell(x,y))){
			selectedPiece.move(board.getCell(x, y));
			selectedPiece = null;
			repaint();
			return;
		}

		selectedPiece = board.getCell(x, y).piece;
		
		repaint();
		

		//System.out.println(x + ", " + y);
//		String currentCell = selectedPiece != null
//				? (selectedPiece.side ? "Black " : "White ") + selectedPiece.getClass().getSimpleName() : "Empty";
//		System.out.println(currentCell);
//		
//		if (selectedPiece != null)
//		{
//			System.out.println(selectedPiece.getPossibleMoves().size());
//		}
		
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
