package sort;

public class QuickSort extends Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<T>> void quicksort(T[] a, int low, int high) {
		if (low < high) {
			int p = partition(a, low, high);
			quicksort(a, low, p - 1);
			quicksort(a, p + 1, high);
		}
	}

	private static <T extends Comparable<T>> int partition(T[] a, int low, int high) {
		int splitPos = low;
		for (int i = low; i < high; i++) {
			if (a[i].compareTo(a[high]) < 0) {
				swap(i, splitPos, a);
				splitPos++;
			}
		}
		swap(splitPos, high, a);
		return splitPos;
	}

	public static <T extends Comparable<T>> T median(T[] a, int low, int high) {
		if (low < high) {
			int p = partition(a, low, high);
			if (p == (a.length - 1) / 2)
				return a[p];
			else if (p > (a.length - 1) / 2)
				return median(a, low, p - 1);
			else
				return median(a, p + 1, high);
		}

		return a[low];

	}

}
