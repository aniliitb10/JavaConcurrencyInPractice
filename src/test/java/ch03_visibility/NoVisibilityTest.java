package ch03_visibility;

import org.junit.jupiter.api.Test;

class NoVisibilityTest {
    // modify run configuration to run until fail

    @Test
    void makeReady() throws InterruptedException {
        NoVisibility noVisibility = new NoVisibility();

        // Launch following 2 thread
        Thread readyMakerThread = new Thread(noVisibility::makeReady);
        Thread runnerThread = new Thread(noVisibility);

        readyMakerThread.start();
        runnerThread.start();

        runnerThread.join();
        readyMakerThread.join();
        org.junit.jupiter.api.Assertions.assertNotEquals(noVisibility.getRunnerThreshold(), noVisibility.getRunner());
    }
}