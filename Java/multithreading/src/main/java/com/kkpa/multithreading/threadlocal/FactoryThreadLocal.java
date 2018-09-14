package com.kkpa.multithreading.threadlocal;

public class FactoryThreadLocal {
	
	
	private ThreadLocal<CustomDTO> threadLocal;
	
	
	public FactoryThreadLocal() {
		threadLocal = ThreadLocal.withInitial( () -> new CustomDTO());
	}
	
	
	public CustomDTO getObject() {
		return threadLocal.get();
	}
	
	public void setObject(CustomDTO dto) {
		threadLocal.set(dto);
	}

}
