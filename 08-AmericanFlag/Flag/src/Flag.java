import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JApplet;

public class Flag extends JApplet {

	private final double HOIST = 1.0;
	private final double FLY = 1.9;
	private final double HOIST_UNION = 7.0 / 13.0;
	private final double FLY_UNION = 0.76;
	private final double E = 0.054;
	private final double F = 0.054;
	private final double G = 0.063;
	private final double H = 0.063;
	private final double STAR_DIAMETER = 0.0616;
	private final double STRIPE_WIDTH = 1.0 / 13.0;
	private int height, width;

	public void init() {
		setSize(760, 400);
		repaint();
	}

	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		height = getHeight();
		width = getWidth();

		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		if (width / height > FLY / HOIST) {
			// height is major dimension
			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0) {
					g.setColor(Color.red);
					g.fillRect(0, (int) (i * STRIPE_WIDTH * height), (int) (height * FLY),
							(int) (STRIPE_WIDTH * height));
				} else {
					g.setColor(Color.white);
					g.fillRect(0, (int) (i * STRIPE_WIDTH * height), (int) (height * FLY),
							(int) (STRIPE_WIDTH * height));
				}
			}

			g.setColor(Color.blue);
			g.fillRect(0, 0, (int) (height * FLY_UNION), (int) (height * HOIST_UNION));

		} else {
			// width is major dimension
			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0) {
					g.setColor(Color.red);
					g.fillRect(0, (int) (i * width / FLY * STRIPE_WIDTH), (int) width,
							(int) (width / FLY * STRIPE_WIDTH));
				} else {
					g.setColor(Color.white);
					g.fillRect(0, (int) (i * width / FLY * STRIPE_WIDTH), (int) width,
							(int) (width / FLY * STRIPE_WIDTH));
				}
			}

			g.setColor(Color.blue);
			g.fillRect(0, 0, (int) (width * FLY_UNION / FLY), (int) (width / FLY * HOIST_UNION));

		}

	}
}
