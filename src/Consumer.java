import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    private final int id;
    private final BlockingQueue<Float> queue;

    public Consumer(int id, BlockingQueue<Float> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        Random r = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Poll retorna Null se não houver itens na fila
                Float price = queue.poll(500, TimeUnit.MILLISECONDS);

                if (price != null) {
                    // Simula tempo aleatório para pagamento
                    Thread.sleep(r.nextLong(2000, 5001));
                    System.out.println("Consumer Thread #" + id + ": Pagamento de " + price + " realizado!");
                } else {
                    System.out.println("Consumer Thread #" + id + ": Não foi possível adquirir cliente para pagamento");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer Thread #" + id + " interrupted!");
                break;
            }
        }
    }
}
