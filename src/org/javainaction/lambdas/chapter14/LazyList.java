package org.javainaction.lambdas.chapter14;

import org.functionalInterface.Predicate;

import java.util.function.Supplier;

public class LazyList<T> implements MyList<T>{
    T head;
    Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail){
        this.head = head;
        this.tail = tail;
    }
    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ?
                this :
                p.test(head()) ?
                        new LazyList<>(head(), () -> tail().filter(p)) :
                        tail().filter(p);
    }
}
