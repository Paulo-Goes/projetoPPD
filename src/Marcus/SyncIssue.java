package Marcus;

public class SyncIssue {
    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter();

        Thread t1 = new Thread(new IncrementTask(counter));
        Thread t2 = new Thread(new IncrementTask(counter));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final do contador (esperado: 200000): " + counter.getValue());
    }
}
