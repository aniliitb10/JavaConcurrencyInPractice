package lock;

public class ThreadSafeInteger {
    LockInterface lock = new MyLock();
    int simpleInt = 0;

    int getSimpleInt() {
        int copy;
        lock.getLock();
        copy = simpleInt;
        lock.unlock();
        return copy;
    }

    int incrementBy(int number) {
        int copy;
        lock.getLock();
        simpleInt += number;
        copy = simpleInt;
        lock.unlock();
        return copy;
    }

    void increment() {
        this.incrementBy(1);
    }

    void setTo(int number) {
        lock.getLock();
        simpleInt = number;
        lock.unlock();
    }
}
