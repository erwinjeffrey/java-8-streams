package org.javainaction.lambdas;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectorsStream {
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

        long howManyDishes = menu.stream().collect(Collectors.counting());
        System.out.println(howManyDishes);
        // OR
        long howManyDishes2 = menu.stream().count();

        System.out.println("Find the highest-calorie dish in the menu:");
        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparing(Dish::getCalories);
        Optional<Dish> mostCalorieDish =
                menu.stream()
                        .collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish);

        System.out.println("Summarization:");
        int totalCalories = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        System.out.println("Averaging");
        double avgCalories =
                menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        System.out.println("Summarizing Operation");
        IntSummaryStatistics menuStatistics =
                menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        System.out.println("Joining:");
        String shortMenu = menu.stream()
                .map(Dish::getName).collect(Collectors.joining());
        System.out.println(shortMenu);

        String shortMenu2 = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(shortMenu2);

        int totalCalories2 = menu.stream()
                .collect(Collectors.reducing(0,Dish::getCalories, (i,j) -> i + j));
        System.out.println(totalCalories2);

        Optional<Dish> mostCalorieDish2 =
                menu.stream().collect(Collectors.reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2
                ));
        System.out.println(mostCalorieDish2);

        int totalCalories3 = menu.stream().collect(Collectors.reducing(0,
                Dish::getCalories,
                Integer::sum));

        System.out.println("Grouping");
        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);

       Map<CaloricLevel,List<Dish>> dishesByCaloricLevel = menu.stream().collect(
               Collectors.groupingBy(dish -> {
                   if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                   else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                   else return CaloricLevel.FAT;
               })
       );
        System.out.println(dishesByCaloricLevel);

        System.out.println("Multilevel grouping: ");
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.groupingBy(dish -> {
                                    if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }))
                );
        System.out.println(dishesByTypeCaloricLevel);

        System.out.println("TypesCount: ");
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.counting())
        );
        System.out.println(typesCount);

        System.out.println("Most Calories by Type");
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparing(Dish::getCalories))));
        System.out.println(mostCaloricByType);

        System.out.println("Finding the highest-calorie Dish in each subgroup");
        Map<Dish.Type, Dish> mostCaloricByType2 =
                menu.stream()
                    .collect(Collectors.groupingBy(Dish::getType,
                            Collectors.collectingAndThen(
                                    Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                    Optional::get
                            )));

        System.out.println("Total Calories By Type: ");
        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(Collectors.groupingBy(Dish::getType,
                        Collectors.summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);

        System.out.println("Caloric Levels by type");
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType, Collectors.mapping(
                                dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                   else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                   else return CaloricLevel.FAT;},
                                Collectors.toSet()))
                );
        System.out.println(caloricLevelsByType);

        System.out.println("Partitioning: ");
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        System.out.println("Retrieve vegetarian dishes: ");
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println(vegetarianDishes);

        System.out.println("Vegetarian Dishes By Type");
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(
                        Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType))
                );
        System.out.println(vegetarianDishesByType);

        System.out.println("Most Caloric Partitioned By Vegetarian");
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(
                        Collectors.partitioningBy(Dish::isVegetarian,
                                Collectors.collectingAndThen(
                                    Collectors.maxBy(Comparator.comparing(Dish::getCalories)) ,
                                    Optional::get
                                ))
                );
        System.out.println(mostCaloricPartitionedByVegetarian);

        Map<Boolean, Map<Boolean,List<Dish>>> menu10 = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian,
                Collectors.partitioningBy(d -> d.getCalories() > 500)));
        System.out.println(menu10);

        System.out.println("Counting vegetarian and no vegetarian dishes");
        Map<Boolean, Long> countingVegAndNoVeg = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.counting()));
        System.out.println(countingVegAndNoVeg);

        System.out.println("Is Prime");

        System.out.println(isPrime(4));

        System.out.println(" Partition Prime: ");
        System.out.println(partitionPrimes(5));

        System.out.println("Word Counter: ");
        String sentence = "Nel mezzo del cammin di nostra vita "+
                "mi ritrovai in una selva oscura"+
                " ché la dritta via era smarrita";

        Stream<Character> stream = IntStream.range(0, sentence.length())
                .mapToObj(sentence::charAt);
        System.out.println("Found " + countWords(stream) + " words");
}
    public static  boolean isPrime(int candidate){
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime2(int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(Collectors.partitioningBy(canditate -> isPrime2(canditate)));
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);

        return wordCounter.getCounter();
    }

}
