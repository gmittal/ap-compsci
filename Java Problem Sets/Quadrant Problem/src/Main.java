
public class Main {

	public static void main(String[] args) {
		System.out.println(quadrant(1, 1));
		System.out.println(quadrant(-1, 1));
		System.out.println(quadrant(-1, -1));
		System.out.println(quadrant(1, -1));

	}

	public static int quadrant(int x, int y) {
		return (int) (((Math.atan2(y, x) + 2 * Math.PI) % (2 * Math.PI)) / (Math.PI / 2)) + 1;

	}

}
