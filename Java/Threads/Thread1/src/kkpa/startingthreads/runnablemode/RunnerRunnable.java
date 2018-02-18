package kkpa.startingthreads.runnablemode;

import java.util.stream.IntStream;

public class RunnerRunnable implements Runnable {

	@Override
	public void run() {
		IntStream.range(0, 10).forEach( n -> {
			System.out.println("Hello " + n + " - " + this.getClass());
		});
		
	}

}
