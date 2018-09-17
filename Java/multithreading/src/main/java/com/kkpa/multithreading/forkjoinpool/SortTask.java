package com.kkpa.multithreading.forkjoinpool;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class SortTask extends RecursiveTask<int[]> {

	private int[] numbers;
	private int maxElementsToProcess, start, end;
	public static final AtomicInteger TASK_COUNT = new AtomicInteger(0);

	public SortTask(int maxElementsToProcess, int start, int end, int[] numbers) {
		this.maxElementsToProcess = maxElementsToProcess;
		this.start = start;
		this.end = end;
		this.numbers = numbers;
		TASK_COUNT.incrementAndGet();
	}

	@Override
	protected int[] compute() {
		return recursiveInvocation(start, end);
	}

	private int[] recursiveInvocation(int start, int end) {
		if (end - start + 1 > maxElementsToProcess) {
			int half = (start + end + 1) / 2;
			SortTask task = (SortTask) new SortTask(maxElementsToProcess, half + 1, end, numbers).fork();
			int[] result = recursiveInvocation(start, half);
			int[] result2 = task.join();
			return Sort.merge(result, result2);
		} else {
			int[] result = Arrays.copyOfRange(numbers, start, end + 1);
			result = Sort.sort(result);
			return result;
		}
	}

}
