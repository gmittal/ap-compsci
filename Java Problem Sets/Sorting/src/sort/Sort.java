package sort;

public abstract class Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {

	}

	protected static <T extends Comparable<T>> void swap(int i, int j, T[] a) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
