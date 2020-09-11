package org.javainaction.parameterization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple[]{new Apple("green",150),new Apple("green",170),new Apple("red",120) });

        List<Apple> result = filterApples(apples, new AppleHeavyWeightPredicate());
        result.stream().forEach(System.out::println);

        // or
        List<Apple> result2 = filterApples(apples, (Apple apple) -> "red".equals(apple.getColor()));
       //
        List<Apple> result3 = filter(apples,(Apple apple) -> "red".equals(apple.getColor()));

        Thread t = new Thread(() -> System.out.print("Hello world"));

    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static <T> List<T>  filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e: list){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }
}
