package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort extends Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {

		List<T> originalList = new ArrayList<>();

		originalList.addAll(Arrays.asList(a));

		List<T> sortedList = mergeSort(originalList);

		sortedList.toArray(a);
	}

	private static <T extends Comparable<T>> List<T> mergeSort(List<T> a) {
		if (a.size() < 2)
			return a;
		return merge(mergeSort(a.subList(0, a.size() / 2)), mergeSort(a.subList(a.size() / 2, a.size())));
	}

	private static <T extends Comparable<T>> List<T> merge(List<T> a1, List<T> a2) {
		List<T> a = new ArrayList<>();

		while (!(a1.isEmpty() && a2.isEmpty())) {

			if (a1.isEmpty()) {
				a.addAll(a2);
				a2.clear();
			} else if (a2.isEmpty()) {
				a.addAll(a1);
				a1.clear();
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
