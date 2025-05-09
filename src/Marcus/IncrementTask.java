package Marcus;
public class IncrementTask implements Runnable {

    private final SharedCounter counter;

    public IncrementTask(SharedCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
    }

}
