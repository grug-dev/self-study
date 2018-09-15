package com.kkpa.multithreading.reentrantlock;

import java.util.concurrent.Callable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskLock implements Callable<Integer> {

	private Integer id;
	private Printer printer;

	@Override
	public Integer call() throws Exception {
		printer.print("Task: " + id);
		printer.print("Task: " + id);
		printer.print("Task: " + id);
		return id;

	}

}
