package com.kkpa.multithreading.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kkpa.multithreading.reentrantlock.Printer;
import com.kkpa.multithreading.reentrantlock.TaskLock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrinterLocksTests {

	private final static int TASK = 5;

	@Test
	public void test() {

		ExecutorService executorService = Executors.newCachedThreadPool();
		Printer printer = new Printer();
		List<Callable<Integer>> tasks = new ArrayList<>(TASK);

		IntStream.rangeClosed(1, 5).forEach(i -> {
			TaskLock t = new TaskLock(i, printer);
			tasks.add(t);
		});

		try {
			executorService.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executorService.shutdown();

		System.out.println("Completed Jobs: " + printer.getCont());

	}
}
