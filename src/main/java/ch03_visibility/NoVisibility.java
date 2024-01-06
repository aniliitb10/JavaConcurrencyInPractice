package ch03_visibility;

import lombok.Setter;

@Setter
public class NoVisibility extends Thread {
    private boolean ready = false;
    private int number = 0;

    @Override
    public void run() {
        while (!this.ready) Thread.yield();
        System.out.println(this.number);
    }

    public static void main(String[] args) throws InterruptedException {
        NoVisibility noVisibility = new NoVisibility();
        Thread thread = new Thread(noVisibility);
        thread.start();
        noVisibility.setNumber(42); // it is really difficult to test re-ordering here
        noVisibility.setReady(true);
        thread.join();
    }
}
