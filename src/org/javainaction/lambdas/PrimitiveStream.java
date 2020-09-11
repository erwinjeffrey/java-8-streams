package org.javainaction.lambdas;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStream {
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

        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("calories " + calories);

        // convert Stream to numeric Stream
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);

        // convert numeric Stream to a Stream
        Stream<Integer> stream = intStream.boxed();

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1); // Provide an explicit default maximum if the'res no value

        /* rangeClose and range */
        IntStream evenNumbers = IntStream.rangeClosed(1,100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());

        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1,100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                    .filter(b -> Math.sqrt(a * a + b *b ) % 1 == 0)
                 .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a *a + b * b)}));
         pythagoreanTriples.limit(5)  .forEach(t -> System.out.println(t[0] + "," + t[1]+ "," + t[2]));
         // or
        Stream<double[]> pytagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(
                                b -> new double[]{ a, b, Math.sqrt(a * a + b * b)}
                        ).filter(t -> t[2] % 1 == 0));

        System.out.println("Creating Streams from values");
        Stream<String> stream1 = Stream.of("Java 8", "Lambdas","In","Action");
        stream1.map(String::toUpperCase).forEach(System.out::println);

        // empty stream
        Stream<String> emptyStream = Stream.empty();

        System.out.println("Streams from arrays");
        int [] numbers = { 2,3,5,7,11,13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        System.out.println("Streams from files");
        long uniqueWords = 0;
        try(Stream<String> lines =
                    Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }
        catch (IOException e){}

        System.out.println("Streams from functions");
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("Fibonacci tuples series");
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" +t[0] + "," + t[1] + ")"));

        System.out.println("Generate");
        Stream.generate(Math:: random)
                .limit(5)
                .forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
