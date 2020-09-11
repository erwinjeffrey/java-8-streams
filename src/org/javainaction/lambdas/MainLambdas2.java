package org.javainaction.lambdas;

import org.functionalInterface.Predicate;
import org.javainaction.parameterization.Apple;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class MainLambdas2 {
    public static void main(String[] args) {

        Predicate<Apple> redApple = (Apple a) -> "red".equalsIgnoreCase(a.getColor());

        // chaining two predicates to produce another predicate
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);

        //Chaining Predicate's methods to construct a more complex Predicate object
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight() > 150)
                .or(a -> "green".equals(a.getColor()));

        //Composing Functions
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println(result);

        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> g2 = x -> x * 2;
        Function<Integer, Integer> h2 = f.compose(g);
        int result2 = h2.apply(1);
        System.out.println(result2);

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println(transformationPipeline.apply("labda"));

        List<Dish> menu = Arrays.asList(new Dish("Rice", 450), new Dish("Chicken", 350));

        List<String> lowCaloriesDishesName = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(lowCaloriesDishesName);

    }
}
