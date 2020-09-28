package org.functionalprograming.chapter7;
import java.math.BigInteger;

import static org.functionalprograming.chapter7.TailCalls.done;
import static org.functionalprograming.chapter7.TailCalls.call;

public class BigFactorial {

    public static BigInteger decrement(final BigInteger number){
        return number.subtract(BigInteger.ONE);
    }

    public static BigInteger multyply( final BigInteger first, final BigInteger second){
        return first.multiply(second);
    }

    final static BigInteger ONE = BigInteger.ONE;
    final static BigInteger FIVE = new BigInteger("5");
    final static BigInteger TWENTYK = new BigInteger("20000");

    public static TailCall<BigInteger> factorialTailRec(
            final  BigInteger factorial, final BigInteger number
    ){
        if(number.equals(BigInteger.ONE))
            return done(factorial);
        else
            return call(() -> factorialTailRec(multyply(factorial, number), decrement(number)));
    }

    public static  BigInteger factorial(final BigInteger number){
                return factorialTailRec(BigInteger.ONE, number).invoke();
    }

    public static void main(String[] args) {
       System.out.println(factorial(FIVE));
        System.out.println(String.format("%.ds10s...", factorial(TWENTYK)));
    }
}
