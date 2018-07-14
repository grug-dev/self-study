package com.kkpa.java8.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.kkpa.java8.interfaces.TestInterfaces;
import com.kkpa.java8.optional.TestOptional;
import com.kkpa.java8.threads.TestCompletableFuture;

@RunWith(Suite.class)
@SuiteClasses( { TestInterfaces.class, TestOptional.class } )
public class Java8Suite {

}
