package com.kkpa.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import com.kkpa.java8.dto.User;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    Map<User, Integer> mapUsers = new HashMap<>();
    IntStream.range(1, 100000).forEach(n -> {
      User u = new User(n);
      mapUsers.put(u, n);
    });
    long endTime = System.currentTimeMillis();
    System.out.println("Putting took " + (endTime - startTime) + " milliseconds");

    startTime = System.currentTimeMillis();
    mapUsers.forEach((a, b) -> {
      a.getId();
    });
    endTime = System.currentTimeMillis();
    System.out.println("Printing took " + (endTime - startTime) + " milliseconds");

  }


}
