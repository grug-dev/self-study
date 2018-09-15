package com.kkpa.multithreading.concurrentqueues;

import java.util.Comparator;
import java.util.concurrent.Semaphore;

public class Priority implements Comparator<Message> {

	public final static int HIGH = 1;
	public final static int MEDIUM = 2;
	public final static int LOW = 3;

	private static Semaphore semaphore = new Semaphore(1);

	public Priority() {

	}

	@Override
	public int compare(Message o1, Message o2) {
		return Integer.compare(o1.getPriority(), o2.getPriority());
	}

	public static String getPriorityName(int priority) {
		String result = null;
		try {
			semaphore.acquire();
			switch (priority) {
			case HIGH:
				result = "HIGH";
				break;
			case MEDIUM:
				result = "MEDIUM";
				break;
			default:
				result = "LOW";
				break;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaphore.release();
		}

		return result;

	}

}
