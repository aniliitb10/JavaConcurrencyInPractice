package ch02_counter;

class UnsafeCounter implements CounterInterface {
    private int count = 0;

    @Override
    public void increament() {
        this.count++;
    }

    @Override
    public int getCount() {
        return count;
    }
}