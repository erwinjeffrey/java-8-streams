package org.javainaction.lambdas.trading;

import java.util.*;
import java.util.stream.Collectors;

public class TradingTransaction {
    public static void main(String[] args) {
        Trader  raoul = new Trader("Raoul","Cambridge");
        Trader  mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul,2012,1000 ),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario, 2012,700),
                new Transaction(alan, 2012,950)
        );

        System.out.println("1. Find all transactions in the year 2011 and sort them by value (small to high) :");
        List<Transaction> transactionYear2011 = transactions.stream()
                .filter(t -> t.getYear() ==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        transactionYear2011.forEach(System.out::println);

        System.out.println("2. What are all the unique cities where the traders work?");
        List<String> uniqueCities = transactions.stream()
                .map(t-> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        uniqueCities.forEach(System.out::println);
          // OR
        Set<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());

        System.out.println("3. Find all traders from Cambridge and sort them by name");
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader-> trader.getCity().equalsIgnoreCase("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        traders.forEach(System.out::println);

        System.out.println("4. Return a string of all traders’names sorted alphabetically.");
        String tradersName = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(a, b) -> a + b);
        System.out.println(tradersName);
        // OR,more efficient because of the use of stringBuilder
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());

        System.out.println("5. Are any traders based in Milan:");
        boolean anyMilanTrader = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equalsIgnoreCase("Milan"));
        System.out.println(anyMilanTrader);

        System.out.println("6. Print all transactions’values from the traders living in Cambridge");
       transactions.stream()
                .filter(t -> t.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("7. What's the highest value of all the transactions:");
        Optional<Integer> max = transactions.stream()
                 .map(t -> t.getValue())
                .reduce(Integer::max);
        if(max.isPresent()){
            System.out.println(max.get());
        }

        System.out.println("Find the transaction with the smallest value");
        Optional<Transaction> transaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        if(transaction.isPresent()){
            System.out.println(transaction);
        }
        // also
        Optional<Transaction> smallestTransaction =
                transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1: t2);

    }
}
