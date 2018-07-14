package com.kkpa.java8.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamService {

    public int sumNumbersofStream(int initValue, List<Integer> numbers) {
	return numbers.stream().reduce(initValue, (a, b) -> a + b);
    }

    public Map groupBySex(List<PersonDTO> list) {

	Map<Boolean, List<PersonDTO>> map = list.stream().collect(Collectors.groupingBy(PersonDTO::isFemale));

	return map;

    }

    public boolean matchAgeOfPersons(List<PersonDTO> persons, int age) {
	return persons.stream().anyMatch(p -> p.getAge() == age);
    }

    public Map<Integer, String> collectToMapGroupingByAgeJoiningNames(List<PersonDTO> persons) {
	return persons.stream().collect(Collectors.toMap(PersonDTO::getAge, PersonDTO::getName));
    }

}
