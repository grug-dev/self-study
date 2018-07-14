package com.kkpa.datasctructures.arrays;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ArrayManipulation {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
	Map<Integer, Long> fragments = new TreeMap<>();
	Arrays.stream(queries).forEach(q -> {
	    long k = q[2];
	    fragments.merge(q[0], k, Long::sum);
	    fragments.merge(q[1] + 1, -k, Long::sum);
	});
	long overlappingValue = 0, result = 0;
	for (long value : fragments.values()) {
	    result = Long.max(overlappingValue += value, result);
	}
	return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
	String[] nm = scanner.nextLine().split(" ");

	int n = Integer.parseInt(nm[0]);

	int m = Integer.parseInt(nm[1]);

	int[][] queries = new int[m][3];

	for (int i = 0; i < m; i++) {
	    String[] queriesRowItems = scanner.nextLine().split(" ");
	    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	    for (int j = 0; j < 3; j++) {
		int queriesItem = Integer.parseInt(queriesRowItems[j]);
		queries[i][j] = queriesItem;
	    }
	}

	long result = arrayManipulation(n, queries);

	System.out.println(result);

	scanner.close();

    }

}
