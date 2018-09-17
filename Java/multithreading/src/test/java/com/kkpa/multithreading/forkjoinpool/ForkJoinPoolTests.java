package com.kkpa.multithreading.forkjoinpool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForkJoinPoolTests {
	private static final int ELEMENTS = 1000000;
	private static final int MAX_ELEMENTS_TO_PROCESS_PER_TASK = 100000;
	private Random random = new Random();
	private int[] numbers = random.ints(ELEMENTS, 1, ELEMENTS).toArray();;

	@Test
	public void test() {

		ForkJoinPool pool = new ForkJoinPool();
		SortTask task = new SortTask(MAX_ELEMENTS_TO_PROCESS_PER_TASK, 0, numbers.length - 1, numbers);

		Long nanos = System.nanoTime();
		pool.execute(task);

		try {
			while (!task.isDone()) {
				Thread.sleep(5);
				printPoolStatus(pool);
			}

			int[] ordered = task.get();
			nanos = (System.nanoTime() - nanos) / 1000000;

			System.out.printf("First: %d - Last: %d  ", ordered[0], ordered[ordered.length - 1]);
			System.out.println("Task Used: " + task.TASK_COUNT.get() + " Total Time: " + nanos + " ms.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Test
	public void sortArrayNormalWay() {
		Long nanos = System.nanoTime();
		int[] ordered = Arrays.stream(numbers).boxed().parallel().sorted().mapToInt(i -> i).toArray();
		nanos = (System.nanoTime() - nanos) / 1000000;

		System.out.printf("First Normal Way: %d - Last Normal Way: %d  ", ordered[0], ordered[ordered.length - 1]);
		System.out.println(" Total Time Nomral Way: " + nanos + " ms.");

	}

	private void printPoolStatus(ForkJoinPool pool) {
		System.out.printf("Paralellism: %d - Pool size: %d - Active threads: %d - Task Count: %d - Steal Count: %d \n",
				pool.getParallelism(), pool.getPoolSize(), pool.getActiveThreadCount(), pool.getQueuedTaskCount(),
				pool.getStealCount());

	}

}
