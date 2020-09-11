package org.javainaction.lambdas.appendixc;

import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class BlockingQueueSpliterator<T> implements Spliterator{

    private BlockingQueue<T> q;

    BlockingQueueSpliterator(BlockingQueue<T> q){
        this.q = q;
    }

    @Override
    public boolean tryAdvance(Consumer action) {
        T t;
        while (true){
            try{
                t = q.take();
                break;
            }catch (InterruptedException e){}
        }

        if(t != ForkingStreamConsumer.END_OF_STREAM){
            action.accept(t);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator trySplit() {
        return null;
    }

    @Override
    public int characteristics() {
        return 0;
    }

    @Override
    public long estimateSize() {
        return 0;
    }
}
