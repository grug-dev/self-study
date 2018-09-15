package com.kkpa.multithreading.concurrentqueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriorityQueueTests {

	private static final int QUEUE_SIZE = 30;
	private static final int CONSUMERS = 5;

	@Test
	public void test() {
		ExecutorService executorService = Executors.newFixedThreadPool(CONSUMERS);

		BlockingQueue<Message> queue = new PriorityBlockingQueue<>(QUEUE_SIZE, new Priority());

		IntStream.rangeClosed(1, CONSUMERS).forEach(c -> {
			executorService.submit(new TaskQueueConsumer(queue));
		});

		IntStream.rangeClosed(1, QUEUE_SIZE).forEach(i -> {
			Message m = new Message("Message: " + i, (i % 3) + 1);
			try {
				queue.put(m);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		try {
			TimeUnit.SECONDS.sleep(2);
			executorService.shutdown();
			executorService.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Queue Size: " + queue.size());

	}

}
