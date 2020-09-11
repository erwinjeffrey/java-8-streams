package org.javainaction.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class chapter8 {
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

        System.out.println("dishesByCaloricLevel: ");
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
                menu.stream().collect(Collectors.groupingBy(d -> getColoricLevel(d)));
        System.out.println(dishesByCaloricLevel);

        /* Strategy pattern using lambdas */
        Validator numericValidator =
                new Validator((String s) -> s.matches("[a-z]"));
        boolean b1 = numericValidator.validate("aaaaa");
        Validator lowerCaseValidator =
                new Validator((String s) -> s.matches("\\d+"));
        boolean b2 = lowerCaseValidator.validate("1234");

        System.out.println("Strategy design pattern with Lambdas: ");
        System.out.println("b1: " + b1 + " b2: " + b2);

        System.out.println("The chain of responsibility design pattern : ");
        UnaryOperator<String> headerProcessing  =
                (String text) -> "From Raoul, Mario and Alan: " + text;

        UnaryOperator<String> spellCheckerProcessing =
                (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline =
                headerProcessing.andThen(spellCheckerProcessing);

        String result = pipeline.apply("Arren't labdas really sexy? !!");
        System.out.println(result);

        System.out.println("Logging information: ");
       loginInformation();

    }


    public static CaloricLevel getColoricLevel(Dish dish){
        if(dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    }

    private  static void loginInformation (){
        List<Integer> numbers = Arrays.asList(2,3,4,5);

        List<Integer> result =
                numbers.stream()
                .peek(x -> System.out.println("from stream: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x))
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x))
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}
