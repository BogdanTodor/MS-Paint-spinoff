import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.Line2D;


public class GUI extends JFrame implements Runnable {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;

    private void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);

        //Set up the content pane.
        addComponentsToPane(getContentPane());

        repaint();
        this.setVisible(true);
    }

    public static void addComponentsToPane(Container pane) {

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel mainPanel, leftPanel, topPanel, rightPanel, bottomPanel;
        JButton openButton, saveButton;

        openButton = new JButton("Open");
        c.gridx = 0;
        c.gridy = 0;
        pane.add(openButton, c);

        saveButton = new JButton("Save");
        c.gridx = 1;
        c.gridy = 0;
        pane.add(saveButton, c);

        c.fill = GridBagConstraints.BOTH; // Turn on for following panels

        topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setBorder(new LineBorder(Color.GRAY));
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(topPanel, c);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(leftPanel, c);

        mainPanel = new drawingPanel();
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(mainPanel, c);

        rightPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(pane.getWidth() - mainPanel.getWidth() - leftPanel.getWidth() - 1, 0);
            }
        };
        rightPanel.setBackground(Color.LIGHT_GRAY);
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 1;
        pane.add(rightPanel, c);

        bottomPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, pane.getHeight() - mainPanel.getHeight() - topPanel.getHeight() -1);
            }
        };
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(bottomPanel, c);
    }


    @Override
    public void run() {
        createAndShowGUI();
    }
}

class drawingPanel extends JPanel {

    public drawingPanel() {
        setSize(500,500);
        setBorder(new LineBorder(Color.GRAY));
        setVisible(true);
    }

    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int size = Math.min(getWidth(), getHeight());
        setSize(size, size);
        g.setColor(Color.BLACK);
        Colors.setIsFillOn(false);
        for (Shape shape : Shape.lineCommands) {
            shape.drawShape(g, size);
        }
    }
}
