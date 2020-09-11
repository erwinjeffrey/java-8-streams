package org.javainaction.lambdas.chapter14;

import org.functionalInterface.Predicate;

public interface MyList<T> {
    T head();
    MyList<T> tail();

    MyList<T> filter(Predicate<T> p);

    default  boolean isEmpty(){
        return true;
    }
}
