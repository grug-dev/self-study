package com.kkpa.java8.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class ParallelSortTest {
	
	private ParallelSorter sorter = new ParallelSorter();
	
	
	@Test
	public void giveBigCollectionThenSorteIt() {
		List<Double> source = IntStream.rangeClosed(1, 1000000).asDoubleStream()
		.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		
		sorter.paralellSort(source);
		
	}

}
