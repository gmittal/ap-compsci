import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Cow2[] cows = { new Cow2(2000, "Hulk"), new Cow2(), new Cow2(1600, "Bessie"), new Cow2(1700, "Moohead"),
				new Cow2(), new Cow2(1900, "Big Time Jones") };

		printArray(cows);
		Arrays.sort(cows);
		printArray(cows);
	}

	public static void printArray(Cow2[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i].name + ", ");
		}

		System.out.println("");
	}

}
