package com.kkpa.springtutorial.components;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope("prototype")
public class PersonPrototype implements InitializingBean , DisposableBean , BeanPostProcessor{
	
	private int age;
	private String name;
	
	
	
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Initialized!: " + this.toString());
		
	}



	@Override
	public void destroy() throws Exception {
		System.out.println("Destroyed! " + this.toString());
		
	}

}
