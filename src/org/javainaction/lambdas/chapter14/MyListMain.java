package org.javainaction.lambdas.chapter14;

public class MyListMain {
    public static void main(String[] args) {
        MyList<Integer> l = new MyLinkedList<>(
                5, new MyLinkedList<>(10,new Empty<>()));



        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();

        System.out.println(two + " " + three + " " +four);

        /*  Calculating prime */

        LazyList<Integer> number2 = from(2);
        int tow2= primes(numbers).head();
        int three3 = primes(numbers).tail().head();
        int five = primes(numbers).tail().tail().head();
        System.out.println(tow2 + " " + three3 + " " +five);

    }

    private static LazyList<Integer> from(int n){
        return new LazyList<Integer>(n ,()-> from(n+1));
    }

    private static MyList<Integer> primes(MyList<Integer> numbers){
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail()
                        .filter(n -> n % numbers.head() != 0)
                )
        );
    }
}
