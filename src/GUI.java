import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.geom.Line2D;


public class GUI extends JFrame implements Runnable {

    private void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        drawingPanel mainPanel = new drawingPanel();
        add(mainPanel);
        setSize(250,250);
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
        for (Shape shape : Shape.lineCommands) {shape.drawShape(g);
        }
    }
}
