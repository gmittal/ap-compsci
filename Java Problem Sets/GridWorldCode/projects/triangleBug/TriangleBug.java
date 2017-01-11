
import info.gridworld.actor.Bug;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class TriangleBug extends Bug {
	private int steps;
	private int baseSteps;

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public TriangleBug(int length) {
		steps = 0;
		baseSteps = length;
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if (canMove()) {
			if (isOnBase()) {
				if (steps < baseSteps) {
					move();
					steps++;
				} else {
					turn();
					turn();
					turn();
					steps = 0;
				}
			} else if (steps == baseSteps / 2) {
				turn();
				turn();
				steps++;
			} else if (steps == baseSteps + 1) {
				turn();
				turn();
				turn();
				steps = 0;
			} else {
				move();
				steps++;
			}
		} else {
			turn();
			turn();
			turn();
			steps = 0;
		}
	}

	private boolean isOnBase() {
		return getDirection() % 90 == 0;
	}
}
