package com.kkpa.multithreading.threadlocal;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith (SpringRunner.class)
@SpringBootTest
public class ThreadLocalTests {
	
	
	@Test
	public void givenAFactoryShouldReturnSameObjectEachThread() {
		int n = 5;
		Thread[] threads = new Thread[n];
		FactoryThreadLocal factory = new FactoryThreadLocal();
		IntStream.range(1, n).forEach( i -> {
			threads[i-1] = new Thread(new TaskThreadLocal(factory));
			threads[i-1].setName("My Thread: " + i);
			threads[i-1].start();
		});
		
	}

}
