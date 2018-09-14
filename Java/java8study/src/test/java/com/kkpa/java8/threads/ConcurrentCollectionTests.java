package com.kkpa.java8.threads;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentCollectionTests {

  private ConcurrentHashMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
  private List<Integer> lst;

  @Before
  public void setup() {
    lst = new ArrayList<Integer>();
    IntStream.range(0, 10).forEach(n -> lst.add(n));
  }

  @Test(expected = ConcurrentModificationException.class)
  public void removeElementIntoCollectionShouldThrowException() {
    ConcurrentCollections concurrentCollections = new ConcurrentCollections();
    concurrentCollections.removeElementsFromList(lst);
  }

  @Test()
  public void removeElementIntoCopyWriteCollectionShouldBeSuccess() {
    ConcurrentCollections concurrentCollections = new ConcurrentCollections();
    lst = new CopyOnWriteArrayList<>();
    IntStream.range(0, 10).forEach(n -> lst.add(n));

    concurrentCollections.removeElementsFromList(lst);
  }

}
