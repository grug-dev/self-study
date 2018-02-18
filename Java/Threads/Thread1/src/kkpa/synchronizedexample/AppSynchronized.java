package kkpa.synchronizedexample;

import java.util.stream.IntStream;

public class AppSynchronized {
	
	private int count = 0;
	
	public synchronized void increment() {
		count++;
	}
	
	public static void main (String[] args) {
		AppSynchronized app = new AppSynchronized();
		app.doWork();
	}
	
	public void doWork() {
		Runnable r1 = () -> {
			IntStream.range(0, 10000).forEach( v -> {
				increment();
			});
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1);
		Thread t3 = new Thread(r1);
		
		t1.start();
		t2.start();
		t3.start();
		try {
			System.out.println("Count: " + count);
			t1.join();
			System.out.println("Count: " + count);
			t2.join();
			System.out.println("Count: " + count);
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Count: " + count);
	}

}
