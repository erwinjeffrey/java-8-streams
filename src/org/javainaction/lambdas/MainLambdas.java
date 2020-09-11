package org.javainaction.lambdas;

import org.comparators.Comparator;
import org.javainaction.parameterization.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import static java.util.Comparator.comparing;

public class MainLambdas {
    public static void main(String[] args) throws IOException {
        Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        //Processing one line
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println("One Line: " +oneLine);

        //Processing two lines
        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println("Two Lines: " +twoLines);

        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        str.stream().forEach(System.out::println);

        List<Integer> weights = Arrays.asList(7,3,4,10);
        List<Apple> apples = map(weights, Apple::new);
        apples.stream().forEach(System.out::println);

        Map<String, Integer> applesMap = new HashMap<String, Integer>(){
            {
                put("green", 120);
                put("red", 1150);

            }

        };

        System.out.println("Bifunction");
        List<Apple> apples2 = map(applesMap,Apple::new);
        apples2.stream().forEach(System.out::println);

        // first way of sorting
        apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        // second way of sorting
        Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());


        // Third way of sorting
        apples.sort(comparing((a)-> a.getWeight()));
        // Fourth way of sorting and final solution
        apples.sort(comparing(Apple::getWeight));

        // chaining comparators
        apples.sort(comparing(Apple::getWeight)
            .reversed()
            .thenComparing(Apple::getCountry));


        System.out.println("Bifunction2");
        BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple cb3 = c3.apply("Rojo",201);
        System.out.println(cb3);

        System.out.println("Bifunction3");
        BiFunction<String, Integer, Apple> bf3 =
                (color, weight) -> new Apple(color, weight);
        Apple c4 = bf3.apply("Verde", 110);
        System.out.println(c4);



    }

    public static String processFile() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(("/Users/erwin/Downloads/java-8-courses/src/org/javainaction/lambdas/data.txt")))) {
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("/Users/erwin/Downloads/java-8-courses/src/org/javainaction/lambdas/data.txt"))) {
            return p.process(br);
        }
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Integer e: list){
            result.add(f.apply(e));
        }
        return result;
    }

    public static List<Apple> map(Map<String, Integer> apples, BiFunction<String, Integer, Apple> bf){
        List<Apple> result = new ArrayList<>();
        for(Map.Entry<String, Integer> entryValues: apples.entrySet()){
            result.add(bf.apply(entryValues.getKey(), entryValues.getValue()));
        }
        return result;
    }
}
