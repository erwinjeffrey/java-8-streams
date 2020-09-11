package org.javainaction.lambdas.chapter14;

import org.functionalInterface.Predicate;

public class MyLinkedList <T> implements  MyList<T>{
    private T head;
    private MyList<T> tail;
    public MyLinkedList(T head, MyList<T> tail){
        this.head = head;
        this.tail = tail;
    }
    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail;
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
