package lock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadSafeIntegerTest {

    private final int taskCount = 100000;
    private final int threadCount = 15;

    private void logDescription(ThreadSafeInteger threadSafeInteger) {
        System.out.println("Task count: " + this.taskCount + ", Thread count: " + this.threadCount + ", Difference: " + (this.taskCount - threadSafeInteger.getSimpleInt()));
    }

    void threadSafeIntegerTest(ThreadSafeInteger threadSafeInteger) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(this.threadCount)) {
            for (int i = 0; i < this.taskCount; ++i) {
                executorService.execute(threadSafeInteger::increment);
            }
            executorService.shutdown();
        }
    }

    @Test
    void safeCounterTest() {
        int repeatCount = 10;
        for (int i = 0; i < repeatCount; ++i) {
            ThreadSafeInteger threadSafeInteger = new ThreadSafeInteger();
            threadSafeIntegerTest(threadSafeInteger);
            assertEquals(this.taskCount, threadSafeInteger.getSimpleInt());
            this.logDescription(threadSafeInteger);
        }
    }
}