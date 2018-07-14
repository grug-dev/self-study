package com.kkpa.velocity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.kkpa.velocity.Solution.Exc1;

public class Solution {
	
	static class Exc0 extends Exception {}
	
	static class Exc1 extends Exc0 {}

	public static void main(String[] args) {
		try {
			System.out.println("B");
		}
		finally {
			System.out.println("Finally");
		}

	}
	
	 private static void badMethod() {
		throw new RuntimeException();
		
	}

	static int[] printPrimeFibonacciNumbers(int n) {
	        //To generate the next Prime int number, call the existing method: PrimeGenerator.getNext();
	        //To generate the next Fibonacci int number, call the existing method: FibonacciGenerator.getNext();
		 
		 
		 int prime = 0;
		 List<Integer> primes = new ArrayList<>();
		 prime = PrimeGenerator.getNext();
		 while ( prime <= n) {
			 primes.add(prime);
			 prime = PrimeGenerator.getNext();
		 }
		 
		 List<Integer> fibonaccis = new ArrayList<>();
		 int fibonacci = 0;
		 fibonacci = FibonacciGenerator.getNext();
		 while (fibonacci <= n) {
			 fibonaccis.add(fibonacci);
			 fibonacci = FibonacciGenerator.getNext();
		 }
		 
		 primes.retainAll(fibonaccis);
		 
		 int[] result = new int[primes.size()];
		 int cont = 0;
		 for (Integer number : primes) {
			 result[cont] = number;
			 cont++;
		 }
		 
		 return result;
		 
	 }
}

final class FibonacciGenerator {
    
    private static int currentStep = 0; 

	private FibonacciGenerator() {
	}
	
	private static int fib(int n) {
		if (n < 2) {
			return n;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}

    /**
     * Resets the Fibonacci series.
     */
	public static void resetSeries() {
		currentStep = 0;
	}
	
    /**
     * Returns the last number in the current Fibonacci series.
     */
	public static int getNext() {
		return fib(++currentStep);
	}

}


final class PrimeGenerator {

	private static List<Integer> currentPrimes = new LinkedList<Integer>();

	private PrimeGenerator() {
	}

	/**
     * Returns the next prime number.
     */
	public static int getNext() {
		if (currentPrimes.size() == 0) {
			currentPrimes.add(2);
			return 2;
		}
		int candidate = currentPrimes.get(currentPrimes.size() - 1);

		while (true) {
			if(isPrime(++candidate)) {
                currentPrimes.add(candidate);
                return candidate;
            } 
		}
	}
    private static boolean isPrime(int num) {
        if (num % 2 == 0) return false;
        for (int i = 3; i * i < num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

}
