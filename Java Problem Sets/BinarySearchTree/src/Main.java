public class Main {

	public static void main(String[] args) {

		int average = 0;

		for (int j = 0; j < 10000; j++) {
			RandP rand = new RandP(10000);
			BST<Integer> tree = new BST<>(0);
			for (int i = 1; i < 10000; i++) {
				tree.add(rand.nextInt());
			}

			average += tree.depth();
		}

		System.out.println(average / 10000);

	}

}
