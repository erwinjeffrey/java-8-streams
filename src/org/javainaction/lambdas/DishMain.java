package org.javainaction.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DishMain {
    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("frenchfries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("seasonfruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        List<String> threeHighCaloriesDishNames =
                menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(threeHighCaloriesDishNames);

        List<String> names =
                menu.stream()
                .filter(d -> {
                    System.out.println("Filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("Mapping" + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(names);

        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();
        System.out.println("count " + count);

        List<Dish> vegetarianDishes =
                menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println("Vegetarian Dishes:");
        System.out.println(vegetarianDishes);

        System.out.println("even numbers");
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        System.out.println("Filtering two meats:");
        List<Dish> twoMeats =
                menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(twoMeats);

        System.out.println("list of numbers of characters of each words:");
        List<String> listNames = Arrays.asList("Erwin","Mabel","Jenny", "Nisaer");
        List<Integer> namesLength = listNames.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(namesLength);

        System.out.println("filtering name length dishes:");
        List<Integer> dishLength = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishLength);

        System.out.println("not repeated strings:");
        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueWords = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        uniqueWords.forEach(System.out::print);

        System.out.println("Square:");
        List<Integer> numbers2 = Arrays.asList(1,2,3,4,5);
        numbers2.stream()
                .map(number -> number * number)
                .forEach(System.out::print);

        System.out.println("Pairs of Numbers:");
        List<Integer> numbers0 = Arrays.asList(1,2,3);
        List<Integer> numbers1 = Arrays.asList(3,4);
        List<List<Integer>> pairs =
                numbers0.stream()
                .flatMap(i -> numbers1.stream()
                .map(j -> Arrays.asList(i,j)))
                .collect(Collectors.toList());
        pairs.forEach(System.out::println);

        System.out.println("Pairs whose sum is divisible by 3:");
        List<List<Integer>> pairs2 =
                numbers0.stream()
                .flatMap(i -> numbers1.stream()
                .filter(j -> (i + j)% 3==0)
                .map(j-> Arrays.asList(i,j)
                )).collect(Collectors.toList());
        pairs2.forEach(System.out::print);
    }
}
