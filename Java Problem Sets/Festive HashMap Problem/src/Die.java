import java.util.Random;

public class Die {

	private int sides;
	private Random rand;

	public Die(int s) {
		sides = s;
		rand = new Random();
	}

	public Die(int s, Random r) {
		sides = s;
		rand = r;
	}

	public int roll() {
		return rand.nextInt(sides) + 1;
	}

}
