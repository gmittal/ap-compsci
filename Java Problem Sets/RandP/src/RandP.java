import java.util.ArrayList;
import java.util.Random;

public class RandP {

	private Random rand;
	private ArrayList<Integer> validInts;

	public RandP(int n) {

		rand = new Random();

		validInts = new ArrayList<>();
		for (int i = 1; i <= n; i++)
			validInts.add(i);

	}

	public int nextInt() {
		if (validInts.isEmpty())
			return 0;
		else {
			int index = rand.nextInt(validInts.size());
			int r = validInts.get(index);
			validInts.remove(index);
			return r;
		}
	}

}
