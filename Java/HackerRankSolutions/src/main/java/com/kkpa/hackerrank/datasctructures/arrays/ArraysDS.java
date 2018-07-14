package com.kkpa.datasctructures.arrays;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ArraysDS {

    // Complete the reverseArray function below.
    static int[] reverseArray(int[] a) {
	int lengthArray = a.length;
	int[] reverseArray = new int[lengthArray];

	IntStream.range(0, lengthArray).forEach(i -> {
	    reverseArray[i] = a[(lengthArray - i) - 1];
	});

	return reverseArray;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
	int arrCount = scanner.nextInt();
	scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	int[] arr = new int[arrCount];

	String[] arrItems = scanner.nextLine().split(" ");
	scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	for (int i = 0; i < arrCount; i++) {
	    int arrItem = Integer.parseInt(arrItems[i]);
	    arr[i] = arrItem;
	}

	int[] res = reverseArray(arr);

	for (int i = 0; i < res.length; i++) {
	    System.out.println(res[i]);
	}

	scanner.close();
    }
}
