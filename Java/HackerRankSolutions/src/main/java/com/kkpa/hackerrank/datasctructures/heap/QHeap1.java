package com.kkpa.datasctructures.heap;

import java.util.PriorityQueue;
import java.util.Scanner;

public class QHeap1 {

    static PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	int n = scan.nextInt();
	int value = 0;

	scan.nextLine();
	for (int i = 0; i < n; i++) {
	    String[] operationLine = scan.nextLine().split(" ");
	    int operation = Integer.parseInt(operationLine[0]);

	    switch (operation) {
	    case 1:
		value = Integer.parseInt(operationLine[1]);
		addToHead(value);
		break;
	    case 2:
		value = Integer.parseInt(operationLine[1]);
		removeFromHeap(value);
		break;
	    default:
		printMinimumValueFromHeap();
		break;
	    }
	}
    }

    private static void printMinimumValueFromHeap() {
	System.out.println(pQueue.peek());

    }

    private static void addToHead(int value) {
	pQueue.add(value);
    }

    private static void removeFromHeap(int value) {
	pQueue.remove(value);
    }

}
