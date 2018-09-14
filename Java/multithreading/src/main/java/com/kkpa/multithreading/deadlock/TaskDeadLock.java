package com.kkpa.multithreading.deadlock;

public class TaskDeadLock implements Runnable {

	private Object firstResource, secondResource;

	public TaskDeadLock(Object firstResource, Object secondResource) {
		this.firstResource = firstResource;
		this.secondResource = secondResource;
	}

	@Override
	public void run() {

		synchronized (firstResource) {
			try {
				System.out.println(
						"First Resource " + firstResource + " Blocked! By: " + Thread.currentThread().getName());
				Thread.sleep(100);
				synchronized (secondResource) {
					System.out.println(
							"Second Resource " + secondResource + "  Blocked! By: " + Thread.currentThread().getName());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
