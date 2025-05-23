import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private final BlockingQueue<Float> queue;
    private double total;

    public Producer(BlockingQueue<Float> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random r = new Random();
        while(!Thread.currentThread().isInterrupted()) {
            try{
                //float price = r.nextFloat(10, 1001);
                //price = Math.round(price * 100f) / 100f;

                float price = r.nextFloat();

                boolean added = queue.offer(price);

                if(added){
                    System.out.println("Producer criou novo cliente: " + price);
                    total+=price;
                } else {
                    System.out.println("Producer não consegue adicionar novo cliente, fila cheia!");
                }

                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted!");
                break;
            }
        }
    }
    
    public double getTotal() {
        return total;
    }
}
