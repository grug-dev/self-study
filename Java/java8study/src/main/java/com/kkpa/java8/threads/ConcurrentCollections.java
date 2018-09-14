package com.kkpa.java8.threads;

import java.util.Iterator;
import java.util.List;

public class ConcurrentCollections {

  public void removeElementsFromList(List<Integer> lst) {

    Iterator<Integer> it = lst.iterator();

    while (it.hasNext()) {
      int value = it.next();
      if (value == 3) {
        lst.remove(5);
      }
    }

  }

}
