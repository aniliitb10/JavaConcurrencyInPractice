package ch03_visibility;

// credit to https://stackoverflow.com/a/52775627/3701834
// and do set the configuration to keep running until failure
public class InstructionReorderingTest {

    // 'volatile' prevents re-ordering but 'static' has no effect
    int x, y, a, b;

    @org.junit.jupiter.api.BeforeEach
    public void init() {
        x = y = a = b = 0;
    }

    @org.junit.jupiter.api.Test
    public void test() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            a = 1;
            x = b;
        });
        Thread threadB = new Thread(() -> {
            b = 1;
            y = a;
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        org.junit.jupiter.api.Assertions.assertFalse(x == 0 && y == 0);
    }
}
