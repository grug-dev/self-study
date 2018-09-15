package com.kkpa.multithreading.concurrentqueues;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

	private String content;
	private int priority;

	public synchronized String getPriorityName() {
		return Priority.getPriorityName(priority);
	}

}
