public class Main {

	public static void main(String[] args) {

		RandP rand = new RandP(100);

		for (int i = 0; i < 101; i++)
			System.out.println(rand.nextInt());
	}

}
