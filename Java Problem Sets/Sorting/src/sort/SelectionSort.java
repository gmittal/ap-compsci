package sort;

public class SelectionSort extends Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int smallest = i;
			for (int x = i; x < n; x++) {
				if (a[x].compareTo(a[smallest]) < 0)
					smallest = x;
			}
			swap(smallest, i, a);
		}

	}

}
