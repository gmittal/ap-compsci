package sort;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort extends Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {

		ArrayList<T> originalList = new ArrayList<>();
		originalList.addAll(Arrays.asList(a));

		mergeSort(originalList).toArray(a);
	}

	private static <T extends Comparable<T>> ArrayList<T> mergeSort(ArrayList<T> a) {
		if (a.size() < 2)
			return a;

		ArrayList<T> sub1 = new ArrayList<>();
		sub1.addAll(a.subList(0, a.size() / 2));

		ArrayList<T> sub2 = new ArrayList<>();
		sub2.addAll(a.subList(a.size() / 2, a.size()));

		return merge(mergeSort(sub1), mergeSort(sub2));
	}

	private static <T extends Comparable<T>> ArrayList<T> merge(ArrayList<T> a1, ArrayList<T> a2) {
		ArrayList<T> a = new ArrayList<>();

		while (!(a1.isEmpty() && a2.isEmpty())) {

			if (a1.isEmpty()) {
				a.addAll(a2);
				break;
			} else if (a2.isEmpty()) {
				a.addAll(a1);
				break;
			} else if (a1.get(0).compareTo(a2.get(0)) > 0) {
				a.add(a2.get(0));
				a2.remove(0);
			} else {
				a.add(a1.get(0));
				a1.remove(0);
			}

		}

		return a;
	}

}
