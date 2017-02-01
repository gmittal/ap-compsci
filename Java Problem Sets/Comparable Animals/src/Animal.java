
public abstract class Animal implements Comparable<Animal> {

	public int weight;
	public String name;

	public Animal() {
		weight = 1800;
		name = "Anonymous " + this.getClass().getSimpleName();
	}

	public Animal(int w, String n) {
		weight = w;
		name = n;
	}

	public void eat(int amt) {
		weight += amt;
	}

	@Override
	public int compareTo(Animal o) {
		return weight - o.weight;
	}

}
