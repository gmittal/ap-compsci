package sort;

public class InsertionSort extends Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {
		int n = a.length;
		for (int i = 1; i < n; i++) {
			for (int x = i; x > 0; x--) {
				if (a[x].compareTo(a[x - 1]) < 0)
					swap(x, x - 1, a);
				else
					break;
			}
		}

	}

}
