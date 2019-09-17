package com.kkpa.java8.arrays;


import java.util.Arrays;
import java.util.List;

public class ParallelSorter {

	
	public void paralellSort(List<Double> arraySource) {
		Double [] myArray = new Double[1];
		myArray = arraySource.toArray(myArray);
		long startTime = System.currentTimeMillis();
		Arrays.sort(myArray);
		long endTime = System.currentTimeMillis();
		System.out.println("Time take in serial: "+
		(endTime-startTime)/1000.0);

		Double [] myArray2 = new Double[1];
		myArray2 = arraySource.toArray(myArray);
		startTime = System.currentTimeMillis();
		Arrays.parallelSort(myArray2);
		endTime = System.currentTimeMillis();
		System.out.println("Time take in parallel: "+
		(endTime-startTime)/1000.0);
	}
	
}
