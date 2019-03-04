package com.kkpa.multithreading.semaphore;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kkpa.multithreading.semaphores.Consumer;
import com.kkpa.multithreading.semaphores.Producer;

@SpringBootTest
@RunWith(SpringRunner.class)
public class listProducerConsumerTests {

	Semaphore mutex = new Semaphore(1);

	@Test
	public void givenProducerThenConsumerShouldReceiveMessage() {
		Queue<String> queue = new LinkedList<>();

		Semaphore queueSemaphore = new Semaphore(0);

		Thread firstProducer = new Thread(new Producer(30, queue, mutex, queueSemaphore));

		Consumer consumer = new Consumer(queue, mutex, queueSemaphore);
		Thread firstConsumer = new Thread(consumer);
		firstConsumer.setName("First Consumer");
		Thread secondConsumer = new Thread(consumer);
		secondConsumer.setName("Second Consumer");

		firstProducer.start();

		// Consumers
		firstConsumer.start();
		secondConsumer.start();
		ExecutorService consumerService = Executors.newFixedThreadPool(2);
		consumerService.submit(consumer);

		try {
			firstProducer.join();

			while (queueIsNotEmpty(mutex, queue)) {
				Thread.sleep(100);
			}
			consumer.stopConsumming();
			consumerService.shutdown();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Finish Test");
		assertTrue(queue.isEmpty());

	}

	private boolean queueIsNotEmpty(Semaphore mutex, Queue queue) {
		boolean isNotEmpty = true;
		try {
			mutex.acquire();
			isNotEmpty = !queue.isEmpty();
		} catch (InterruptedException e) {
		}

		mutex.release();

		return isNotEmpty;
	}

}
