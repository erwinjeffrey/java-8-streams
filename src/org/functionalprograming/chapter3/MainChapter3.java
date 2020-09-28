package org.functionalprograming.chapter3;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainChapter3 {
    public static void main(String[] args) {
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35)
        );

        List<Person> ascendingAge =
                people.stream()
                        .sorted((person1, person2) -> person1.ageDifference(person2))
                        .collect(Collectors.toList());
        printPeople("Sorted in ascending order by age: ", ascendingAge);

        //OR
        people.stream()
                .sorted(Person::ageDifference);

        Comparator<Person> compareAscending =
                (person1, person2) -> person1.ageDifference(person2);
        Comparator<Person> compareDescending = compareAscending.reversed();

        printPeople("Sorted in ascending order by age: ",
                people.stream()
                        .sorted(compareAscending)
                        .collect(Collectors.toList()));

        printPeople("Sorted in descending order by age: ",
                people.stream()
                        .sorted(compareDescending)
                        .collect(Collectors.toList()));

        people.stream()
                .min(Person::ageDifference)
                .ifPresent(youngest -> System.out.println(" Youngest: " + youngest));

        people.stream()
                .max(Person::ageDifference)
                .ifPresent(eldest -> System.out.println("Eldest: " + eldest));

        final Function<Person, Integer> byAge = person -> person.getAge();
        final Function<Person, String> byTheirName = person -> person.getName();

        printPeople("Sorted in ascending order by age and name: ",
                people.stream()
                        .sorted(Comparator.comparing(byAge).thenComparing(byTheirName))
                        .collect(Collectors.toList()));

        List<Person> olderThan20 =
                people.stream()
                        .filter(person -> person.getAge() > 20)
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People older than 20: " + olderThan20);

        Map<Integer, List<Person>> peopleByAge =
                people.stream()
                        .collect(Collectors.groupingBy(Person::getAge));
        System.out.println("Grouping by age: " + peopleByAge);

        Map<Integer, List<String>> nameOfPeopleByAge =
                people.stream()
                        .collect(
                                Collectors.groupingBy(Person::getAge,
                                        Collectors.mapping(Person::getName, Collectors.toList()))
                );
        System.out.println("People grouped by age: " + nameOfPeopleByAge);

        Comparator<Person> byAge2 = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonOfEachLetter =
                people.stream()
                .collect(Collectors.groupingBy(person -> person.getName().charAt(0),
                        Collectors.reducing(BinaryOperator.maxBy(byAge2))));
        System.out.println("Oldest person of each letter: ");
        System.out.println(oldestPersonOfEachLetter);
    }

    public static void printPeople(final String message, final List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }
}
