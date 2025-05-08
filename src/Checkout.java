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
                Thread.sleep(r.nextLong(1000, 5001));
            } catch (InterruptedException e) {
                System.out.println("Checkout interrupted!");
                e.printStackTrace();
            }
        }
    }
}
