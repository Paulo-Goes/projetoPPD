import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.util.concurrent.BlockingQueue;

public class Monitor extends JPanel {
    private final JLabel status;
    private final BlockingQueue<Float> queue;

    public Monitor (BlockingQueue<Float> queue) {
        this.queue = queue;
        this.status = new JLabel();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Queue status"));
        add(status, BorderLayout.CENTER);

        Timer t = new Timer(1000, e -> updateStatus());
        t.start();
        updateStatus();
    }

    private void updateStatus(){
        int remaining = queue.remainingCapacity();
        int size = queue.size();
        status.setText("Queue size: " + size + " | Space left: " + remaining);
    }
}
