package org.javainaction.lambdas.chapter14;

import org.functionalInterface.Predicate;

public class Empty<T> implements MyList<T>{


    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> tail() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return null;
    }
}
