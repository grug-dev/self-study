package com.kkpa.java8.streams;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;

public class StreamTest {

  List<Integer> numbers = new ArrayList<>();

  List<PersonDTO> persons = new ArrayList<>();

  StreamService streamService = new StreamService();

  @Before
  public void setup() {
    IntStream.range(1, 10).forEach(n -> {
      numbers.add(n);
      fillPersons("V1", n * 100);
      fillPersons("V2", n);
    });
  }

  private void fillPersons(String prefixName, int age) {
    PersonDTO p = new PersonDTO();
    p.setAge(age);
    p.setName(prefixName + age);
    p.setFemale(new Random().nextBoolean());
    persons.add(p);
  }

  @Test
  public void sumNumbersUsingReduce() {
    int initValue = 0;
    int resultExpected = initValue;

    for (int s : numbers) {
      resultExpected += s;
    }

    int result = streamService.sumNumbersofStream(initValue, numbers);

    assertTrue(result == resultExpected);
  }

  @Test
  public void groupPersonsBySexShouldReturnTwoGroups() {
    int lengthSex = 2;

    Map result = streamService.groupBySex(persons);

    assertTrue(lengthSex == result.size());
  }

  @Test
  public void matchPersonsWithAgeGreatherThan() {
    int age = 20;

    boolean result = streamService.matchAgeOfPersons(persons, age);

    assertFalse(result);

  }

  @Test
  public void collectToMapGroupingByAgeJoiningNames() {
    Map<Integer, String> personsMap = streamService.collectToMapGroupingByAgeJoiningNames(persons);

    assertThat(10, is(equalTo(personsMap.size())));
  }
  
  @Test
  public void streamSequentialAndParalell() {
    streamService.streamSequential(persons);   
  }

}
