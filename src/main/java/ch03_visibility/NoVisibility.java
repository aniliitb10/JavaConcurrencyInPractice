package ch03_visibility;

import lombok.Getter;

@Getter
public class NoVisibility implements Runnable {

    private boolean ready = false;
    private int runner = 0;
    private final int runnerThreshold = 100000;


    @Override
    public void run() {
        for (; this.runner < this.runnerThreshold; ++this.runner) {
            if (this.ready) {
                break;
            }
        }
    }

    public void makeReady() {
        this.ready = true;
    }
}
