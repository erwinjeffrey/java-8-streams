package org.javainaction.lambdas.chapterFourteen;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class chapter14 {

    public static void main(String[] args) {

        DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5,32);

        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6,0);

        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214,0);

        double gbp = convertUSDtoGBP.applyAsDouble(1000);
        System.out.println(gbp);

    }

       /* Curring: reusing function */

    static DoubleUnaryOperator curriedConverter(double f, double b){
        return (double x)-> x * f + b;
    }

    private static Stream<Integer> primes(int n){
        return Stream.iterate(2, i -> i + 1)
                .filter(i -> isPrime(i))
                .limit(n);
    }
    private static boolean isPrime(int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
