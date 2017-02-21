package control;

import java.util.Arrays;

import sort.InsertionSort;

public class Main {

	public static void main(String[] args) {

		Integer[] a = { 2, 6, 3, 1, 4, 5, 5, 9, -1, 87 };

		System.out.println(Arrays.toString(a));

		InsertionSort.sort(a);

		System.out.println(Arrays.toString(a));

	}

}
