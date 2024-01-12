package lock;

import java.util.concurrent.atomic.AtomicInteger;

public class MyLock implements LockInterface {
    AtomicInteger lock = new AtomicInteger(0);

    @Override
    public void getLock() {
        while (!lock.compareAndSet(0, 1));
    }

    @Override
    public void unlock() {
        lock.set(0);
    }
}
