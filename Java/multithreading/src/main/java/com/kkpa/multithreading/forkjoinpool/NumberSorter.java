package com.kkpa.multithreading.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class NumberSorter<T> {

	private ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
	private RecursiveTask<T> recursiveTask;

	public NumberSorter(RecursiveTask<T> recursiveTask) {
		this.recursiveTask = recursiveTask;
	}

	public T sort(T numbers) {

		forkJoinPool.execute(recursiveTask);

		return recursiveTask.join();

	}

}
