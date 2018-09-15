package com.kkpa.multithreading.concurrentqueues;

import java.util.concurrent.BlockingQueue;

public class TaskQueueConsumer implements Runnable {

	private BlockingQueue<Message> queue;

	public TaskQueueConsumer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		Message msg = null;
		try {
			while (!Thread.interrupted() || !queue.isEmpty()) {
				msg = queue.take();
				System.out.println("Receiveid: " + msg.getContent() + " - Priority: " + msg.getPriorityName());
				Thread.sleep(3);
			}

		} catch (InterruptedException e) {
			System.out.println("Interrupted");
			e.printStackTrace();
		}

	}

}
