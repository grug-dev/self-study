package com.kkpa.multithreading.forkjoinpool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {

	public static int[] sort(int[] numbers) {
		return Arrays.stream(numbers).boxed().sorted().mapToInt(i -> i).toArray();

	}

	public static int[] merge(int[] firstResult, int[] secondResult) {
		List<Integer> merged = new ArrayList<>();

		merged.addAll(Arrays.stream(firstResult).boxed().collect(Collectors.toList()));
		merged.addAll(Arrays.stream(secondResult).boxed().collect(Collectors.toList()));

		return merged.stream().sorted().mapToInt(i -> i).toArray();
	}

}
