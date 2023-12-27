package ch02_counter;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounter implements CounterInterface{
    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public void increament() {
        this.count.incrementAndGet();
    }

    @Override
    public int getCount() {
        return count.get();
    }
}