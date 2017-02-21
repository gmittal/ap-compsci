package sort;

public class BubbleSort extends Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {
		int n = a.length;
		for (int i = n - 1; i > 0; i--)
			for (int x = 0; x < i; x++) {
				if (a[x].compareTo(a[x + 1]) > 0)
					swap(x, x + 1, a);
			}

	}

}
