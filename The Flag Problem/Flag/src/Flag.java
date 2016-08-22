
/* Aaron Schultz
 * Gautam Mittal
 */

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

	// Pretty colors
	private final Color AMERICAN_RED = new Color(191, 10, 48);
	private final Color AMERICAN_BLUE = new Color(0, 40, 104);
	private final Color AMERICAN_WHITE = Color.white;

	// Set the applet scene
	public void init() {
		setSize(760, 400);
		repaint();
	}

	// Helper function to generate stars
	public void drawStar(int x, int y, double r, Graphics2D g) {
		int starPoints = 5;
		int[] xcoord = new int[starPoints * 2];
		int[] ycoord = new int[starPoints * 2];

		// Generate x and y coordinates to draw the star
		for (int i = 0; i < xcoord.length; i++) {
			double angle = Math.PI / 2.0 + i * Math.PI / starPoints;
			if (i % 2 == 0) {
				xcoord[i] = (int) (x + Math.cos(angle) * r);
				ycoord[i] = (int) (y - Math.sin(angle) * r);
			} else {
				xcoord[i] = (int) (x + Math.cos(angle) * Math.sqrt(0.1 * (25 - 11 * Math.sqrt(5.0)))
						/ Math.sqrt(0.1 * (5 - Math.sqrt(5.0))) * r);
				ycoord[i] = (int) (y - Math.sin(angle) * Math.sqrt(0.1 * (25 - 11 * Math.sqrt(5.0)))
						/ Math.sqrt(0.1 * (5 - Math.sqrt(5.0))) * r);
			}
		}

		g.setColor(AMERICAN_WHITE);
		g.fillPolygon(xcoord, ycoord, starPoints * 2);
	}

	// Draw the flag
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		height = getHeight();
		width = getWidth();

		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		if (width / height > FLY / HOIST) { // height is major dimension

			// Draw the stripes
			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0) {
					g.setColor(AMERICAN_RED);
					g.fillRect(0, (int) (i * STRIPE_WIDTH * height), (int) (height * FLY),
							(int) (STRIPE_WIDTH * height));
				} else {
					g.setColor(AMERICAN_WHITE);
					g.fillRect(0, (int) (i * STRIPE_WIDTH * height), (int) (height * FLY),
							(int) (STRIPE_WIDTH * height));
				}
			}

			// Draw the blue union
			g.setColor(AMERICAN_BLUE);
			g.fillRect(0, 0, (int) (height * FLY_UNION), (int) (height * HOIST_UNION));

			// Draw some stars
			for (int j = 0; j < 9; j++) {
				if (j % 2 == 0) {
					for (int k = 0; k < 6; k++) {
						drawStar((int) ((G + 2 * H * k) * height), (int) ((E + F * j) * height),
								(STAR_DIAMETER / 2.0) * height, g);
					}
				} else {
					for (int k = 0; k < 5; k++) {
						drawStar((int) ((G + 2 * H * k + H) * height), (int) ((E + F * j) * height),
								(STAR_DIAMETER / 2.0) * height, g);
					}
				}
			}

		} else { // width is major dimension

			// Draw the stripes
			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0) {
					g.setColor(AMERICAN_RED);
					g.fillRect(0, (int) (i * width / FLY * STRIPE_WIDTH), (int) width,
							(int) (width / FLY * STRIPE_WIDTH));
				} else {
					g.setColor(AMERICAN_WHITE);
					g.fillRect(0, (int) (i * width / FLY * STRIPE_WIDTH), (int) width,
							(int) (width / FLY * STRIPE_WIDTH));
				}
			}

			// Draw the blue union
			g.setColor(AMERICAN_BLUE);
			g.fillRect(0, 0, (int) (width * FLY_UNION / FLY), (int) (width / FLY * HOIST_UNION));

			// Draw some stars
			for (int j = 0; j < 9; j++) {
				if (j % 2 == 0) {
					for (int k = 0; k < 6; k++) {
						drawStar((int) ((G + 2 * H * k) * width / FLY), (int) ((E + F * j) * width / FLY),
								(STAR_DIAMETER / 2.0) * width / FLY, g);
					}
				} else {
					for (int k = 0; k < 5; k++) {
						drawStar((int) ((G + 2 * H * k + H) * width / FLY), (int) ((E + F * j) * width / FLY),
								(STAR_DIAMETER / 2.0) * width / FLY, g);
					}
				}
			}

		}

	}
}
