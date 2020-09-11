package org.javainaction.lambdas;

import java.util.function.Function;
import java.util.stream.Stream;

public class chapter15 {
    public static void main(String[] args) {
        Stream.of(1,3,5,7)
                .map(multiplyCurry(2))
                .forEach(System.out::println);
    }

    static Function<Integer, Integer> multiplyCurry(int x){
        return (Integer y) -> x * y;
    }

}
