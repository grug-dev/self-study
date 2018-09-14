package com.kkpa.multithreading.threadlocal;

public class TaskThreadLocal implements Runnable {
	
	
	private FactoryThreadLocal factory;

	public TaskThreadLocal(FactoryThreadLocal factory) {
		this.factory = factory;
	}

	@Override
	public void run() {
		CustomDTO custom = factory.getObject();
		String name = Thread.currentThread().getName() ;
		System.out.println("The thread: " + name + " has this object: " + Integer.toHexString(System.identityHashCode(custom)));
		
		CustomDTO custom2 = factory.getObject();
		System.out.println("Are they equals:? " + custom.equals(custom2) + " in " + name);
	}

}
