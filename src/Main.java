import javax.swing.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Float> queue = new ArrayBlockingQueue<>(30);

        JFrame f = new JFrame("Store Queue Monitor");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 100);
        f.add(new Monitor(queue));
        f.setVisible(true);

        Thread client = new Thread(new Client(queue));

        short checkoutSize = 10;
        ExecutorService checkouts = Executors.newFixedThreadPool(checkoutSize);
        for(int i = 0; i < checkoutSize; i++){
            checkouts.submit(new Checkout(queue));
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client.start();

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client.interrupt();

        checkouts.shutdownNow();

        try {
            checkouts.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
