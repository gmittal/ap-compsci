package sort;

public class HeapSort extends Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {

		maxHeap(a);

		for (int end = a.length - 1; end > 0; end--) {
			swap(0, end, a);
			trickleDown(a, 0, end - 1);
		}

	}

	private static <T extends Comparable<T>> void maxHeap(T[] a) {

		for (int i = a.length - 1; i >= 0; i--) {
			trickleDown(a, i, a.length - 1);
		}

	}

	private static <T extends Comparable<T>> void trickleDown(T[] a, int dataPos, int end) {

		if ((2 * dataPos + 1 <= end) && (a[dataPos].compareTo(a[2 * dataPos + 1]) < 0)) {

			if ((2 * dataPos + 2 <= end) && (a[2 * dataPos + 1].compareTo(a[2 * dataPos + 2]) < 0)) {

				swap(dataPos, 2 * dataPos + 2, a);
				trickleDown(a, 2 * dataPos + 2, end);

			} else {

				swap(dataPos, 2 * dataPos + 1, a);
				trickleDown(a, 2 * dataPos + 1, end);

			}

		} else if ((2 * dataPos + 2 <= end) && (a[dataPos].compareTo(a[2 * dataPos + 2]) < 0)) {

			swap(dataPos, 2 * dataPos + 2, a);
			trickleDown(a, 2 * dataPos + 2, end);

		}

	}

}
