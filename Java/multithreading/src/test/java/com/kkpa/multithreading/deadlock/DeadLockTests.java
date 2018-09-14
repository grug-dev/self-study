package com.kkpa.multithreading.deadlock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DeadLockTests {

	Object secondResource;
	Object firstResource;

	@Before
	public void setup() {
		secondResource = new Object();
		firstResource = new Object();
	}

	@Test
	public void given2ResourcesExecutedBy2ThreadsShouldNotBlockEachOther() {

		Runnable task = new TaskDeadLock(firstResource, secondResource);
		Runnable task2 = new TaskDeadLock(firstResource, secondResource);

		Thread one = new Thread(task);
		Thread two = new Thread(task2);

		one.start();
		two.start();

	}

	@Test
	public void given2ResourcesExecutedBy2ThreadsShouldBlockEachOther() {

		Runnable task = new TaskDeadLock(firstResource, secondResource);
		Runnable task2 = new TaskDeadLock(secondResource, firstResource);

		Thread one = new Thread(task);
		Thread two = new Thread(task2);

		one.start();
		two.start();

	}

}
