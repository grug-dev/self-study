package com.kkpa.datasctructures.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class LeftRotation {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
	String[] nd = scanner.nextLine().split(" ");

	int n = Integer.parseInt(nd[0]);

	int d = Integer.parseInt(nd[1]);

	int[] a = new int[n];

	String[] aItems = scanner.nextLine().split(" ");
	scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	for (int i = 0; i < n; i++) {
	    int aItem = Integer.parseInt(aItems[i]);
	    a[i] = aItem;
	}
	scanner.close();

	int length = a.length;
	int[] leftRotation = Arrays.copyOf(a, length);

	for (int i = 0; i < length; i++) {
	    int newPosition = (i - d);
	    while (Integer.signum(newPosition) == -1) {
		newPosition = n + newPosition;
	    }
	    leftRotation[newPosition] = a[i];
	}

	for (int result : leftRotation) {
	    System.out.print(result + " ");
	}

    }

}
