package org.javainaction.lambdas.chapter11;

import java.util.concurrent.Future;

public class ShopMain {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        /* Query the shop to retrieve the price of a product */
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start ) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        doSomethingElse();

        // While the price of the product is being calculated
        try {
            /* Read the price from the Future or block until it won't be available */
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        }catch (Exception e ){
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() -  start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private static  void  doSomethingElse(){

    }
}
