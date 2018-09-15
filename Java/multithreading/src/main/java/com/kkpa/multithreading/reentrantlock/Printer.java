package com.kkpa.multithreading.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.Data;

@Data
public class Printer {

	private Lock lock = new ReentrantLock(true);
	private int cont = 0;

	public void print(String message) {
		try {
			lock.lock();
			cont++;
			message += Thread.currentThread().getName();
			System.out.println(String.format("Message: %s - cont: %d", message, cont));
		} finally {
			lock.unlock();
		}

	}

}
