package com.kkpa.multithreading.threadpools;

import java.time.LocalTime;

public class ScheduledTask implements Runnable {

	private String name;

	public ScheduledTask(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(LocalTime.now() + " : " + Thread.currentThread().getName() + " is executing:  " + name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
