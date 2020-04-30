package org.comparators.org.collection;

import org.comparators.Person;

import java.util.*;

public class MainAPICollection {

    public static void main(String[] args) {

        Person p1 = new Person("Alice", 23);
        Person p2 = new Person("Brian", 56);
        Person p3 = new Person("Chelsea", 46);
        Person p4 = new Person("David", 28);
        Person p5 = new Person("Erica", 37);
        Person p6 = new Person("Francisco", 18);

        List<Person> people = new ArrayList(Arrays.asList(p1, p2, p3, p4, p5, p6));

        // people.forEach(person -> System.out.println(person));
        people.forEach(System.out::println);

        people.removeIf(person -> person.getAge() < 30);

        System.out.println("===========removeIf=================");

        people.forEach(System.out::println);

        System.out.println("===========ReplaceAll=================");

        people.replaceAll(person -> new Person(person.getFirstName().toUpperCase(), person.getAge()));
        people.forEach(System.out::println);

        System.out.println("===========Comparator=================");
        people.sort(Comparator.comparing(Person::getAge));
        people.forEach(System.out::println);

        System.out.println("===========Comparator Descending order=================");
        people.sort(Comparator.comparing(Person::getAge).reversed());
        people.forEach(System.out::println);


        System.out.println("===========Map=================");

        City newYork = new City("New York");
        City shanghai = new City("Shanghai");
        City paris = new City("Paris");

        Map<City, List<Person>> map = new HashMap<>();
        System.out.println("People from paris : " + map.getOrDefault(paris, Collections.EMPTY_LIST));

        map.putIfAbsent(paris, new ArrayList<>());
        map.get(paris).add(p1);
        System.out.println("People from paris : " + map.getOrDefault(paris, Collections.EMPTY_LIST));

        map.computeIfAbsent(newYork, city -> new ArrayList<>()).add(p2);
        System.out.println("People from New York : " + map.getOrDefault(newYork, Collections.EMPTY_LIST));

        map.computeIfAbsent(newYork, city -> new ArrayList<>()).add(p3);
        System.out.println("People from New York : " + map.getOrDefault(newYork, Collections.EMPTY_LIST));


        System.out.println("Map 1");
        Map<City, List<Person>> map1 = new HashMap<>();
        map1.computeIfAbsent(newYork, city -> new ArrayList<>()).add(p1);
        map1.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p2);
        map1.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p3);

        map1.forEach((city, people1) -> System.out.println(city + ": " + people1));

        Map<City, List<Person>> map2 = new HashMap<>();
        map2.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p4);
        map2.computeIfAbsent(paris, city -> new ArrayList<>()).add(p5);
        map2.computeIfAbsent(paris, city -> new ArrayList<>()).add(p6);

        System.out.println("Map 2");
        map2.forEach(((city, people2) -> System.out.println(city + " : " + people2)));

        System.out.println("Merging map 2 into map 1");

        map2.forEach(((city, people1) -> {
            map1.merge(city, people,
                    (peopleFromMap1, peopleFromMap2) -> {
                        peopleFromMap1.addAll(peopleFromMap2);
                        return peopleFromMap1;
                    });
        }));

        System.out.println("Merged map1");
        map1.forEach(
                (city, people1) -> System.out.println(city + " : " + people1)
        );

    }
}
