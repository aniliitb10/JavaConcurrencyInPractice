package ch02_counter;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CounterTest {
    private final int taskCount = 100000;
    private final int threadCount = 15;

    private final int repeatCount = 10;

    private void logDescription(String prefix, CounterInterface counter) {
        System.out.println(prefix + ", Task count: " + this.taskCount + ", Thread count: " + this.threadCount + ", Difference: " + (this.taskCount - counter.getCount()));
    }

    void counterTest(CounterInterface counter) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(this.threadCount)) {
            for (int i = 0; i < this.taskCount; ++i) {
                executorService.execute(counter::increament);
            }
            executorService.shutdown();
        }
    }

    @Test
    void safeCounterTest() {
        for (int i = 0; i < this.repeatCount; ++i) {
            CounterInterface counter = new SafeCounter();
            counterTest(counter);
            assertEquals(this.taskCount, counter.getCount());
            this.logDescription("(Safe)", counter);
        }
    }

    @Test
    void unsafeCounterTest() {
        for (int i = 0; i < this.repeatCount; ++i) {
            CounterInterface counter = new UnsafeCounter();
            counterTest(counter);
            assertTrue(this.taskCount >= counter.getCount());
            this.logDescription("(Unsafe)", counter);
        }
    }
}