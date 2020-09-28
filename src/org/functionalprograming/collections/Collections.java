package org.functionalprograming.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Collections {
    public static void main(String[] args) {
        final List<String> friends =
                Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        final List<String> editors =
                Arrays.asList("Brian", "Jackie", "John", "Mike");

        final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");
        friends.forEach(System.out::println);

        System.out.println("Uppercase: ");
        friends.stream().map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("counting words:");
        friends.stream()
                .map(name -> name.length())
                .forEach(System.out::println);

        System.out.println("Finding Elements: ");
        final List<String> startsWithN = friends.stream()
                .filter(name -> name.startsWith("N"))
                .collect(Collectors.toList());
        System.out.println(String.format("Found %d names", startsWithN.size()));

        System.out.println("Use lambda expression multiple times");
        final Predicate<String> startsWithN2 = name -> name.startsWith("N");

        final long countFriendsStartN =
                friends.stream()
                        .filter(startsWithN2)
                        .count();
        final long countEditorsStartN =
                editors.stream()
                        .filter(startsWithN2)
                        .count();
        final long countComradesStartN =
                comrades.stream()
                        .filter(startsWithN2)
                        .count();


        final long countFriendsStartN2 =
                friends.stream()
                        .filter(checkIfStartsWith("N")).count();
        final long countFriendsStartB =
                friends.stream()
                        .filter(checkIfStartsWith("B")).count();

        System.out.println("Total number of characters in all names: " +
                friends.stream()
                        .mapToInt(name -> name.length()).sum());

        final Optional<String> aLongName =
                friends.stream()
                .reduce((name1, name2) ->
                    name1.length() >= name2.length() ? name1 : name2);
        aLongName.ifPresent(name ->
            System.out.println(String.format("A longest name: %s", name)));

        final String steveOrLonger =
                friends.stream()
                .reduce("Steve", (name1, name2) ->
                        name1.length() >= name2.length() ? name1 : name2);

        System.out.println("steveOrLonger: " + steveOrLonger);

        System.out.println(String.join(", ", friends));
        System.out.println(friends.stream()
        .map(String::toUpperCase)
        .collect(Collectors.joining(", ")));

        final String str = "w00t";
        str.chars()
                .forEach(ch -> System.out.println(ch));

        str.chars()
                .mapToObj(ch -> Character.valueOf((char) ch))
                .forEach(System.out::println);

    }

    private static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }

    // or
    final Function<String, Predicate<String>> startsWithLetter2 =
            (String letter) -> name -> name.startsWith(letter);

    private static void pickName(final List<String> names, final String startingLetter) {
        final Optional<String> foundName =
                names.stream()
                        .filter(name -> name.startsWith(startingLetter))
                        .findFirst();

        System.out.println(String.format("A name starting with %s: %s",
                startingLetter, foundName.orElse("No name found")));


    }

}
