package com.kkpa.springtutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;

import com.kkpa.springtutorial.components.Dog;
import com.kkpa.springtutorial.components.Elephant;
import com.kkpa.springtutorial.components.InterfaceOne;
import com.kkpa.springtutorial.components.PersonPrototype;
import com.kkpa.springtutorial.components.PersonSingleton;
import com.kkpa.springtutorial.services.HelloException;
import com.kkpa.springtutorial.services.HelloService;
import com.kkpa.springtutorial.services.HelloServiceImpl;

@SpringBootApplication
public class SpringtutorialApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext appCtx;
	
	@Autowired
	private PersonPrototype personPrototype;
	
	@Autowired
	private PersonSingleton personSingleton;
	
	@Autowired
	private HelloService helloService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringtutorialApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		comparePrototypeBeans();
		compareSingletonBeans();

		try {
			helloService.sayHello("Cristian");	
		}
		catch (HelloException ex) {
			System.out.println("Catch: " + ex.getMessage());
		}catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		finally {
			System.out.println("Finally ");
		}
		
		beanInheritance();
	}
	
	private void beanInheritance() {
		Elephant elephant = getAppContext().getBean(Elephant.class);
		Dog dog = getAppContext().getBean(Dog.class);
		
		System.out.println("Animals Equals? " +  elephant.equals(dog));
		
	}

	private void comparePrototypeBeans() throws Exception {
		PersonPrototype anotherPerson = getAppContext().getBean(PersonPrototype.class);
		
		System.out.println("Son Iguales?: " + anotherPerson.equals(personPrototype));
		boolean reference = personPrototype == anotherPerson;
		System.out.println("[ Reference == ] " + reference);
		
		AbstractApplicationContext absApp = (AbstractApplicationContext) appCtx;
		personPrototype = null;
		absApp.registerShutdownHook();
	}
	
	private void compareSingletonBeans() {
		personSingleton.setAge(20);
		personSingleton.setName("Singleton ");
		
		PersonSingleton anotherPerson = getAppContext().getBean(PersonSingleton.class);
		
		System.out.println("[Equals] " + personSingleton.equals(anotherPerson));
		boolean reference = personSingleton == anotherPerson;
		System.out.println("[ Reference == ] " + reference);
	}
	
	private ApplicationContext getAppContext() {
		return appCtx;
	}
	
	@Bean
	public HelloService helloService() {
		return new HelloServiceImpl();
	}

	
}
