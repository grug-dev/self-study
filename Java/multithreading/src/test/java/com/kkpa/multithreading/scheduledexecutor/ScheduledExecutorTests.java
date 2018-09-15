package com.kkpa.multithreading.scheduledexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kkpa.multithreading.threadpools.ScheduledTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduledExecutorTests {

	@Test
	public void test() {
		TimeUnit unit = TimeUnit.SECONDS;
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

		ScheduledTask task2seconds = new ScheduledTask("2 seconds delayed");
		executor.schedule(task2seconds, 2L, TimeUnit.SECONDS);

		ScheduledTask task4DelayEverySecond = new ScheduledTask("4 seconds delayed every second");
		executor.scheduleWithFixedDelay(task4DelayEverySecond, 4L, 1L, unit);

		ScheduledTask task6DelayEverySecond = new ScheduledTask("6 seconds delayed every second");
		executor.scheduleWithFixedDelay(task6DelayEverySecond, 6L, 1L, unit);

		for (int i = 0; i < 5; i++) {
			executor.execute(new ScheduledTask("Task Custom: " + i));
		}

		try {
			Thread.sleep(10000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		executor.shutdown();

		System.out.println("Executor was terminated");

	}
}
