package org.javainaction.lambdas.appendixc;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class ForkingStreamConsumer<T> implements Consumer<T>, Results{
    static Object END_OF_STREAM = new Object();

    private List<BlockingQueue<T>> queues;
    private Map<Object, Future<?>> actions;

    ForkingStreamConsumer(List<BlockingQueue<T>> queues,
                          Map<Object, Future<?>> actions){
        this.queues = queues;
        this.actions = actions;

    }


    @Override
    public void accept(T t) {
        queues.forEach(q -> q.add(t));
    }

    void finish(){
        accept((T) END_OF_STREAM);
    }

    @Override
    public <R> R get(Object key) {
        try{
            return ((Future<R>) actions.get(key)).get();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}