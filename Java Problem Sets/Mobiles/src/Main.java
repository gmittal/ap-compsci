
public class Main {

	public static mobile m;

	public static void main(String[] args) {
		m = new mobile(new branch(6, new mobile(new branch(1, new weight(8)), new branch(4, new weight(2)))),
				new branch(5, new weight(12)));

		System.out.println(m.totalWeight());
		System.out.println(m.isBalanced());

	}

}