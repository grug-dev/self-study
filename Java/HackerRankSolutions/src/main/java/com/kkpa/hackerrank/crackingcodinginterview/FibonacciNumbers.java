package com.kkpa.crackingcodinginterview;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FibonacciNumbers {

    private static Map<Integer, Integer> hash = new HashMap<>();
    private static Map<Integer, Integer> link = new LinkedHashMap<>();

    static {
	hash.put(0, 0);
	hash.put(1, 1);
    }

    public static int fibonacci(int n) {
	Integer result = hash.get(n);
	if (result == null) {
	    result = fibonacci(n - 1) + fibonacci(n - 2);
	    hash.put(n, result);
	}
	return result;
    }

    public static int fibonacciLinked(int n) {
	Integer result = link.get(n);
	if (result == null) {
	    result = fibonacci(n - 1) + fibonacci(n - 2);
	    link.put(n, result);
	}
	return result;
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int n = scanner.nextInt();
	scanner.close();
	long init = System.nanoTime();
	System.out.println(fibonacci(n));
	long end = System.nanoTime();
	System.out.println("time: " + (end - init));
	init = System.nanoTime();
	System.out.println(fibonacciLinked(n));
	end = System.nanoTime();
	System.out.println("time Link: " + (end - init));
    }
}
