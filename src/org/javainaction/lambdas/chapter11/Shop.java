package org.javainaction.lambdas.chapter11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/* Async api calls */
public class Shop {

    public Shop(String input){

    }
    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            try{
                double price = calculatePrice(product);
                futurePrice.complete(price);
            }catch (Exception ex){
                futurePrice.completeExceptionally(ex);
            }

        }).start();
        return futurePrice;
    }
     // better
    public  Future<Double> getPriceAsync2(String product){
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product){
        return calculatePrice(product);
    }

    public static void delay(){
        try{
            Thread.sleep(1000L);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
