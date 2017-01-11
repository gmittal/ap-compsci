import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel {

	private HashSet<Die> dice;
	private HashMap<Integer, Integer> history;
	private int dieSides;

	public Display(int d) {

		dieSides = 6;

		dice = new HashSet<>();
		for (int i = 0; i < d; i++)
			dice.add(new Die(dieSides));

		history = new HashMap<>();
		for (int rolls = 0; rolls < 1000000; rolls++) {
			rollAll();
		}
		System.out.println(history.size());

		windowSetup();
		repaint();

	}

	private void rollAll() {
		int total = 0;
		for (Die d : dice)
			total += d.roll();

		if (!history.containsKey(total))
			history.put(total, 1);
		else {
			history.replace(total, history.get(total) + 1);
		}

	}

	private void windowSetup() {
		JFrame f = new JFrame("Hans Rosling's History of Dice Rolling");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		setPreferredSize(new Dimension(history.size() * 32, 512));
		f.pack();
		f.setVisible(true);

	}

	public void paintComponent(Graphics g) {
		int minKey = Collections.min(history.keySet());
		int maxValue = Collections.max(history.values());
		double sf = (getHeight() - 80) / (double) maxValue;
		g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		FontMetrics metrics = g.getFontMetrics();

		for (int k : history.keySet()) {
			g.fillRect((k - minKey) * 32 + 4, (int) (getHeight() - 40 - sf * history.get(k)), 24,
					(int) (sf * history.get(k)));
			String n = Integer.toString(history.get(k));
			g.drawString(n, (k - minKey) * 32 + 16 - metrics.stringWidth(n) / 2,
					(int) (getHeight() - 44 - sf * history.get(k)));
			String roll = Integer.toString(k);
			g.drawString(roll, (k - minKey) * 32 + 16 - metrics.stringWidth(roll) / 2, getHeight() - 28);
		}

	}

}
