package com.kkpa.crackingcodinginterview;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MakingAnagrams {

    private static final Scanner scanner = new Scanner(System.in);

    private static void makeAnagrams(String firstWord, String secondWord) {

	Map<String, Long> mapFirstWord = Arrays.stream(firstWord.split(""))
		.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

	Map<String, Long> mapSecondWord = Arrays.stream(secondWord.split(""))
		.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

	mapSecondWord.forEach((key, value) -> {
	    mapFirstWord.merge(key, value, (a, b) -> a - b);
	});

	System.out.println(mapFirstWord.values().stream().reduce((a, b) -> {
	    return Math.abs(a) + Math.abs(b);
	}).orElse(0l));
    }

    public static void main(String[] args) {
	String a = scanner.nextLine();

	String b = scanner.nextLine();

	scanner.close();

	makeAnagrams(a, b);

    }

}
