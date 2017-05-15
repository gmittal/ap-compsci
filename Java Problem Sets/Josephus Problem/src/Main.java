import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println(josephus(8));
	}

	public static int josephus(int N) {
		ArrayList<Integer> jews = new ArrayList<>();
		for (int i = 1; i <= N; i++)
			jews.add(i);

		int c = 1;
		while (jews.size() > 1) {
			int v = jews.remove(c);
			if (c + 2 > jews.size() - 1)
				c = 0;
			else
				c += 2;
			System.out.println(v);
			System.out.println(jews);
		}

		return jews.get(0);
	}

}
