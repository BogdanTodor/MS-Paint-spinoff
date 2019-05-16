import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.geom.Line2D;


public class GUI extends JFrame implements Runnable {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;

    private void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        drawingPanel mainPanel = new drawingPanel();
        setBackground(Color.WHITE);
        getContentPane().add(mainPanel,BorderLayout.CENTER);
        setSize(WIDTH,HEIGHT);
        repaint();
        this.setVisible(true);
    }

    @Override
    public void run() {
        createAndShowGUI();
    }
}

class drawingPanel extends JPanel {

    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int size = Math.min(getWidth(), getHeight());
        g.setColor(Color.WHITE);
        Colors.setIsFillOn(false);
        for (Shape shape : Shape.lineCommands) {
            shape.drawShape(g, size);
        }
    }
}
