package org.javainaction.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainLambdas3 {
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

        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        System.out.println("isHealthy: " + isHealthy);

        boolean isHealthy2 = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println("isHealthy2: " +isHealthy2);

        System.out.println("findAny: ");
        Optional<Dish> dish =
                menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        if(dish.isPresent()){
            System.out.println(dish.get());
        }

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        System.out.println("Optional2: ");
        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
        if(firstSquareDivisibleByThree.isPresent()){
            System.out.println(firstSquareDivisibleByThree.get());
        }

        System.out.println("reduce: ");
        int sum = someNumbers.stream().reduce(0, (a,b) -> a + b);
        System.out.println(sum);

        int sum2 = someNumbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        Optional<Integer> sum3 = someNumbers.stream().reduce((a,b) -> (a + b));
        if(sum3.isPresent()){
            System.out.println(sum3.get());
        }

        System.out.println("Max with reduce: ");
        Optional<Integer> max = someNumbers.stream().reduce(Integer::max);
        System.out.println(max.get());

        System.out.println("Min with reduce: ");
        Optional<Integer> min = someNumbers.stream().reduce(Integer::min);
        System.out.println(min.get());

        System.out.println("count the number of dishes: ");
        int count = menu.stream()
                .map(d -> 1)
                .reduce(0,(a ,b) -> a + b);
        System.out.println(count);

        System.out.println(menu.stream().count());

    }
}
