
public class Cow implements Comparable {

	public int weight;
	public String name;

	public Cow(int w, String n) {
		weight = w;
		name = n;
	}

	public Cow() {
		weight = 1800;
		name = "Anonymous Cow";
	}

	@Override
	public int compareTo(Object o) {
		Cow c = (Cow) o;
		return weight - c.weight;
	}

}
