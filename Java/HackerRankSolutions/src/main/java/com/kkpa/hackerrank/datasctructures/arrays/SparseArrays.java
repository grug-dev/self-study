package com.kkpa.datasctructures.arrays;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SparseArrays {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the matchingStrings function below.
    static int[] matchingStrings(String[] strings, String[] queries) {
	int[] result = new int[queries.length];

	IntStream.range(0, queries.length).forEach(q -> {
	    Arrays.asList(strings).stream().forEach(input -> {
		if (input.contentEquals(queries[q])) {
		    result[q]++;
		}
	    });
	});

	return result;
    }

    public static void main(String[] args) throws IOException {
	int stringsCount = scanner.nextInt();
	scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	String[] strings = new String[stringsCount];

	for (int i = 0; i < stringsCount; i++) {
	    String stringsItem = scanner.nextLine();
	    strings[i] = stringsItem;
	}

	int queriesCount = scanner.nextInt();
	scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	String[] queries = new String[queriesCount];

	for (int i = 0; i < queriesCount; i++) {
	    String queriesItem = scanner.nextLine();
	    queries[i] = queriesItem;
	}

	int[] res = matchingStrings(strings, queries);

	for (int i = 0; i < res.length; i++) {
	    System.out.println(res[i]);
	}

	scanner.close();
    }

}
