import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Client implements Runnable {

    private final BlockingQueue<Float> queue;

    public Client(BlockingQueue<Float> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random r = new Random();
        while(!Thread.currentThread().isInterrupted()) {
            try{
                float price = r.nextFloat(10, 1001);
                price = Math.round(price * 100f) / 100f;
                this.queue.put(price);
                Thread.sleep(250);
                System.out.println("Client added: " + price);
            } catch (InterruptedException e) {
                System.out.println("Client interrupted!");
                e.printStackTrace();
            }
        }
    }
}
