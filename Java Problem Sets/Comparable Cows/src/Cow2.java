
public class Cow2 implements Comparable<Cow2> {

	public int weight;
	public String name;

	public Cow2(int w, String n) {
		weight = w;
		name = n;
	}

	public Cow2() {
		weight = 1800;
		name = "Anonymous Cow2";
	}

	@Override
	public int compareTo(Cow2 o) {
		// TODO Auto-generated method stub
		return weight - o.weight;
	}

}
