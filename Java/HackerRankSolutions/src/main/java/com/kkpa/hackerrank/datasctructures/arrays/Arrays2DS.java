package com.kkpa.datasctructures.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Arrays2DS {

    private static Scanner scan = new Scanner(System.in);

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
	int length = arr.length;
	AtomicInteger y0 = new AtomicInteger(-1), x0 = new AtomicInteger(-1), sumHourglass = new AtomicInteger(0);
	List<Integer> sumHourGlasses = new ArrayList<>();

	IntStream.range(0, length).forEach(y -> {
	    if (y + 3 > length) {
		return;
	    }
	    IntStream.range(0, length).forEach(x -> {
		if (x + 3 > length) {
		    return;
		}

		IntStream.range(y, y + 3).forEach(i -> {
		    x0.incrementAndGet();
		    y0.set(-1);
		    IntStream.range(x, x + 3).forEach(j -> {
			y0.incrementAndGet();
			if (x0.get() == 1 && y0.get() != 1) {
			    return;
			}
			sumHourglass.addAndGet(arr[i][j]);
		    });
		});
		sumHourGlasses.add(sumHourglass.get());
		sumHourglass.set(0);
		x0.set(-1);
		y0.set(-1);
	    });
	});

	return sumHourGlasses.stream().max(Integer::compareTo).get();
    }

    public static void main(String[] args) {
	int n = 6;
	int[][] arr = new int[n][n];

	for (int row = 0; row < n; row++) {
	    String[] line = scan.nextLine().split(" ");
	    for (int col = 0; col < n; col++) {
		String value = line[col];
		if (!value.isEmpty())
		    arr[row][col] = Integer.parseInt(line[col]);
	    }
	}

	System.out.println(hourglassSum(arr));

    }

}
