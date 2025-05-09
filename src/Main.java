import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Fila única do supermercado com 30 posições
        BlockingQueue<Float> queue = new ArrayBlockingQueue<>(30);

        // JFrame não estará no produto final por ser uma interface gráfica (que não é suportado em compiladores online)
        JFrame f = new JFrame("Store Queue Monitor");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 100);
        f.add(new Monitor(queue));
        f.setVisible(true);

        // Thread para gerar clientes na fila
        Thread client = new Thread(new Producer(queue));

        // Instanciar threads para processar pagamentos
        short consumerSize = 10;
        Thread[] consumers = new Thread[consumerSize];
        for (int i = 0; i < consumerSize; i++) {
            consumers[i] = new Thread(new Consumer((i + 1), queue));
            consumers[i].start();
        }

        // Sleep tático
        Thread.sleep(3000);

        // Geramos clientes por 30 segundos

        client.start();

        Thread.sleep(30000);

        client.interrupt();

        for (Thread t : consumers) {
            t.interrupt();
        }

        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
    }
}
