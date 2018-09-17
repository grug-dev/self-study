package com.kkpa.multithreading.forkjoinpool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForkJoinPoolTests {
	private static final int ELEMENTS = 100;
	private static final int WORK_LOAD = 10;
	private Random random = new Random();
	private int[] numbers;
	List<Integer> numbersCollection;

	@Before
	public void setup() {
		Random random = new Random();
		long streamSize = ELEMENTS;
		int randomNumberBound = ELEMENTS;
		int randomNumberOrigin = 1;
		int[] tmp = random.ints(streamSize, randomNumberOrigin, randomNumberBound).boxed().mapToInt(i -> i).toArray();
		numbers = new int[tmp.length + 2];
		numbers = Arrays.copyOf(tmp, tmp.length + 2);
		numbers[ELEMENTS] = (Integer.MIN_VALUE);
		numbers[ELEMENTS + 1] = (Integer.MAX_VALUE);

		numbersCollection = Arrays.stream(numbers).boxed().collect(Collectors.toList());
	}

	@Test
	public void testAcamica() {
		ForkJoinPool pool = new ForkJoinPool();
		SortTask task = new SortTask(WORK_LOAD, 0, numbers.length - 1, numbers);

		pool.execute(task);

		int[] numbersSorted;
		try {
			numbersSorted = task.get();
			assertNotNull(numbersSorted);
			assertEquals(numbersSorted[0], Integer.MIN_VALUE);
			assertEquals(numbersSorted[numbersSorted.length - 1], Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { while (!task.isDone()) { Thread.sleep(5); // printPoolStatus(pool); }
		 * 
		 * int[] ordered = task.get(); nanos = (System.nanoTime() - nanos) / 1000000;
		 * 
		 * // System.out.printf("First: %d - Last: %d ", ordered[0],
		 * ordered[ordered.length // - 1]); // System.out.println("Task Used: " +
		 * task.TASK_COUNT.get() + " Total Time: " + // nanos + " ms."); } catch
		 * (Exception ex) { ex.printStackTrace(); }
		 */

	}

	private void printPoolStatus(ForkJoinPool pool) {
		System.out.printf("Paralellism: %d - Pool size: %d - Active threads: %d - Task Count: %d - Steal Count: %d \n",
				pool.getParallelism(), pool.getPoolSize(), pool.getActiveThreadCount(), pool.getQueuedTaskCount(),
				pool.getStealCount());

	}

	@Test
	public void givenCollectionThenShouldBeOrdered() {
		AscendentSortCollectionTask numberSortTask = new AscendentSortCollectionTask(WORK_LOAD, numbersCollection);
		NumberSorter<List<Integer>> sorter = new NumberSorter<List<Integer>>(numberSortTask);
		List<Integer> numbersSorted = sorter.sort(numbersCollection);

		assertNotNull(numbersSorted);
		assertEquals(numbersSorted.get(0).longValue(), Integer.MIN_VALUE);
		assertEquals(numbersSorted.get(numbersSorted.size() - 1).longValue(), Integer.MAX_VALUE);

	}

	@Test
	public void givenNumbersArrayThenShouldBeOrdered() {

		RecursiveTask<int[]> numberSortTask = new AscendentSortArrayTask(WORK_LOAD, numbers);
		NumberSorter<int[]> sorter = new NumberSorter<int[]>(numberSortTask);
		int[] numbersSorted = sorter.sort(numbers);

		assertNotNull(numbersSorted);
		assertEquals(numbersSorted[0], Integer.MIN_VALUE);
		assertEquals(numbersSorted[numbersSorted.length - 1], Integer.MAX_VALUE);

	}

}
