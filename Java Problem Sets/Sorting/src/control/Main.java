package control;

import java.util.Arrays;

import sort.HeapSort;

public class Main {

	public static void main(String[] args) {

		Integer[] a = { 2, 6, 3, 1, 4, 5, 9, -1, 87, 0 };

		// Integer[] a = { 2, 1, 3, 4, 5 };

		System.out.println(Arrays.toString(a));

		HeapSort.sort(a);

		System.out.println(Arrays.toString(a));

	}

}
