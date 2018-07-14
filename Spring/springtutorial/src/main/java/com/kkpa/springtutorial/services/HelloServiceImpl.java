package com.kkpa.springtutorial.services;

import org.springframework.stereotype.Service;

@Service("helloSrv")
public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello " + name);
		throw new HelloException("Hello Exception");
	}

}
