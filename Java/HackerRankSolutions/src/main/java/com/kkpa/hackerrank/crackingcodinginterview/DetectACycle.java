package com.kkpa.crackingcodinginterview;

import java.util.LinkedHashMap;
import java.util.Map;

public class DetectACycle {

    static class Node {
	int data;
	Node next;

	Node(int data) {
	    this.data = data;
	}

	void assignNext(Node next) {
	    this.next = next;
	}
    }

    private static Map<Integer, Integer> nodeMap = new LinkedHashMap<Integer, Integer>();

    private static int cont = 0;

    static boolean hasCycle(Node head) {
	if (head == null) {
	    return false;
	}
	Node slow = head;
	Node fast = head.next;
	while (slow != fast) {
	    System.out.println(cont++);
	    if (fast == null || fast.next == null) {
		return false;
	    }
	    slow = slow.next;
	    fast = fast.next.next;
	}
	return true;
    }

    public static void main(String[] args) {
	Node head = new Node(1);

	Node node2 = new Node(2);
	Node node3 = new Node(3);
	Node node4 = new Node(4);
	Node node5 = new Node(5);

	head.assignNext(node2);
	node2.assignNext(node3);
	node3.assignNext(node4);
	node4.assignNext(node5);

	System.out.println(hasCycle(head));
    }

}
