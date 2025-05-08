import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Checkout implements Runnable{
    private final BlockingQueue<Float> queue;

    public Checkout(BlockingQueue<Float> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random r = new Random();
        while(!Thread.currentThread().isInterrupted()){
            try{
                queue.take();

                // Tempo para realizar a compra
                Thread.sleep(r.nextLong(2000, 5001));
            } catch (InterruptedException e) {
                System.out.println("Checkout interrupted!");
                e.printStackTrace();
            }
        }
    }
}
