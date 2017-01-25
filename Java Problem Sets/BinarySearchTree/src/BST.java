
public class BST<T extends Comparable<T>> {
	private T datum;
	private BST<T> left;
	private BST<T> right;

	public BST(T datum) {
		this.datum = datum;
	}

	public void add(T newDatum) {
		if (newDatum.compareTo(datum) < 0) {
			if (left != null)
				left.add(newDatum);
			else
				left = new BST<T>(newDatum);
		} else {
			if (right != null)
				right.add(newDatum);
			else
				right = new BST<T>(newDatum);
		}
	}

	public T getDatum() {
		return datum;
	}

	public BST<T> getLeft() {
		return left;
	}

	public BST<T> getRight() {
		return right;
	}

	public void printTree() {

	}

	public String toString() {
		return "";
	}

	public int depth() {
		if (left != null || right != null) {
			if (left == null)
				return right.depth() + 1;
			if (right == null)
				return left.depth() + 1;

			return Math.max(left.depth(), right.depth()) + 1;
		} else
			return 1;
	}

}
