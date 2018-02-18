package kkpa.waitandnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class AppWaitNotify {

	public static void main(String[] args) throws InterruptedException {
		ProcessorLInkedList p = new ProcessorLInkedList();
		Runnable runProduce = () -> {
			try {
				p.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		Runnable runConsumer = () -> {
			try {
				p.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		int n = 2000;
		ExecutorService executorProducer = Executors.newFixedThreadPool(n);
		IntStream.range(0, n).forEach( (g) -> {
			executorProducer.submit(runProduce);
		});
		
		
		ExecutorService executorConsumer = Executors.newFixedThreadPool(n);
		IntStream.range(0, n).forEach( (g) -> {
			executorConsumer.submit(runConsumer);
		});
		
		executorConsumer.shutdown();
		executorProducer.shutdown();
		
		System.out.println(p.lst.size());

	}

}
