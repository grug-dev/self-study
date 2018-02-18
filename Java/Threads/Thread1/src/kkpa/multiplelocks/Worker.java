package kkpa.multiplelocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Worker {
	
	private List<Integer> lst1 = new ArrayList<Integer>();
	private List<Integer> lst2 = new ArrayList<Integer>();
	
	public Worker () {
		super();
	}
	
	Object lockStageOne = new Object();
	Object lockStageTwo = new Object();

	public void stageOne() {
		synchronized (lockStageOne) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lst1.add(new Random().nextInt(100));
		}
	}
	
	public void stageTwo() {
		synchronized (lockStageTwo) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lst2.add(new Random().nextInt(100));
		}
	}
	
	public void process() {
		IntStream.range(0, 1000).forEach( d -> {
			stageOne();
			stageTwo();
		});
	}
	
	
	public void execute() {
		System.out.println("Excecuting..");
		long start = System.currentTimeMillis();
		
		Runnable runProcess = () -> {
			process();
		};
		Thread t1 = new Thread(runProcess);
		Thread t2 = new Thread(runProcess);
		
		try {
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		
		System.out.println("Time Taken: " + (end - start));
		System.out.println("Lst One: " + lst1.size());
		System.out.println("Lst Two: " + lst2.size());
	}
	
}
