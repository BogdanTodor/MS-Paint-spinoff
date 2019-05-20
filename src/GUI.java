import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame implements Runnable {

    JMenuBar menuBar;
    public static final int WIDTH = 460;
    public static final int HEIGHT = 470;
    public static double dynamicWidth;
    public static double dynamicHeight;

    private void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setMinimumSize(new Dimension(460, 470));

        addComponentsToPane(getContentPane());
        setJMenuBar(createMenuBar());

        repaint();
        this.setVisible(true);
    }

    public JMenuBar createMenuBar() {
        JMenu menu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();

        menu = new JMenu("File");
        menuBar.add(menu);

        menuItem = new JMenuItem("New");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newButtonClick();
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Open");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openButtonClick();
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonClick();
            }
        });
        menu.add(menuItem);

        return menuBar;
    }

    public void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel mainPanel, leftPanel, rightPanel, bottomPanel;
        JButton undoButton;
        ButtonGroup drawingButtons = new ButtonGroup();
        JToggleButton plotButton, lineButton, rectangleButton, ellipseButton, polyButton, fillToggleButton, gridButton;
        JColorChooser penColorChooser, fillColorChooser;
        JTabbedPane colorTabs;

        mainPanel = new drawingPanel();

        // Add buttons
        c.fill = GridBagConstraints.HORIZONTAL;

        plotButton = new JToggleButton("Plot");
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        drawingButtons.add(plotButton);
        pane.add(plotButton, c);

        lineButton = new JToggleButton("Line");
        c.gridx = 0;
        c.gridy = 1;
        drawingButtons.add(lineButton);
        pane.add(lineButton, c);

        rectangleButton = new JToggleButton("Rectangle");
        c.gridx = 1;
        c.gridy = 0;
        drawingButtons.add(rectangleButton);
        pane.add(rectangleButton, c);

        polyButton = new JToggleButton(" Polygon ");
        c.gridx = 1;
        c.gridy = 1;
        drawingButtons.add(polyButton);
        pane.add(polyButton, c);

        ellipseButton = new JToggleButton("Ellipse");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        drawingButtons.add(ellipseButton);
        pane.add(ellipseButton, c);

        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {Shape.lineCommands.removeLast(); }
                catch (Exception undoException) {
                    
                }
                revalidate();
                repaint();
            }
        });
        c.gridx = 0;
        c.gridy = 2;
        pane.add(undoButton, c);

        fillToggleButton = new JToggleButton("Fill: Off");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        pane.add(fillToggleButton, c);

        gridButton = new JToggleButton("Grid: Off");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(gridButton, c);

        // Add color choosers
        penColorChooser = new JColorChooser();
        penColorChooser.setPreviewPanel(new JPanel()); // removes the preview panel
        AbstractColorChooserPanel[] panels = penColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) { // remove other tabs
            if (!accp.getDisplayName().equals("Swatches")) {
                penColorChooser.removeChooserPanel(accp);
            }
        }

        fillColorChooser = new JColorChooser();
        fillColorChooser.setPreviewPanel(new JPanel()); // removes the preview panel
        AbstractColorChooserPanel[] panels2 = fillColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels2) { // remove other tabs
            if (!accp.getDisplayName().equals("Swatches")) {
                fillColorChooser.removeChooserPanel(accp);
            }
        }

        colorTabs = new JTabbedPane();
        colorTabs.addTab("Pen", penColorChooser);
        colorTabs.addTab("Fill", fillColorChooser);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        pane.add(colorTabs,c);

        // Add Panels

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        setConstraints(leftPanel,0,0,2,5,0,0,c,pane);

        setConstraints(mainPanel,2,0,1,5,1,1,c,pane);

        rightPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(pane.getWidth() - mainPanel.getWidth() - leftPanel.getWidth() - 3, 0);
            }
        };
        rightPanel.setBackground(Color.LIGHT_GRAY);
        setConstraints(rightPanel,3,0,1,5,0,0,c,pane);

        bottomPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, pane.getHeight() - mainPanel.getHeight() - menuBar.getHeight() + 20);
            }
        };
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        setConstraints(bottomPanel,0,5,4,1,0,0,c,pane);

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent c) {
                dynamicHeight = c.getComponent().getHeight();
                dynamicWidth = c.getComponent().getHeight();
            }
        });

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(plotButton.isSelected()) {
                    Shape.lineCommands.add(new Plot("PLOT "+e.getX()/dynamicWidth+" "+e.getY()/dynamicHeight));
                }
                if(SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1 && polyButton.isSelected()) {
                    System.out.println("Right CLICK");
                    String input = "POLYGON";
                    for (int i = 0; i < Polygon.getClickCount(); i++) {
                        input+= " " + Polygon.ClickCoordsX[i] + " " + Polygon.ClickCoordsY[i];
                    }
                    input += " " + Polygon.ClickCoordsX[0] + " " + Polygon.ClickCoordsY[0];
                    System.out.println(input);
                    Shape.lineCommands.add(new Polygon(input));
                    Polygon.resetClickCount();

                }
                else if(polyButton.isSelected()) {
                    System.out.println("Clicked " +e.getX()/dynamicWidth+" "+e.getY()/dynamicHeight);
                    System.out.println("Click count " + Polygon.getClickCount());
                    Polygon.ClickCoordsX[Polygon.getClickCount()] = e.getX()/dynamicWidth;
                    Polygon.ClickCoordsY[Polygon.getClickCount()] = e.getY()/dynamicHeight;
                    Polygon.addClick();
                }
                revalidate();
                repaint();
            }
            @Override
            public void mousePressed(MouseEvent e) {
//                System.out.println("PRESS");
                if(lineButton.isSelected()) {
                    Line.firstClick(e.getX()/dynamicWidth, e.getY()/dynamicHeight);
                }
                else if(rectangleButton.isSelected()) {
                    Rectangle.firstClick(e.getX()/dynamicWidth, e.getY()/dynamicHeight);
                }
                else if(ellipseButton.isSelected()) {
                    Ellipse.firstClick(e.getX()/dynamicWidth, e.getY()/dynamicHeight);
                }
                revalidate();
                repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
//                System.out.println("RELEASE");
                if (lineButton.isSelected()) {
                    Shape.lineCommands.add(new Line("LINE "+ Line.getFirstClickX()+ " " + Line.getFirstClickY() + " " +e.getX()/dynamicWidth+" "+e.getY()/dynamicHeight));
                }
                else if (rectangleButton.isSelected()) {
                    Shape.lineCommands.add(new Rectangle("RECTANGLE "+ Rectangle.getFirstClickX()+ " " + Rectangle.getFirstClickY() + " " +e.getX()/dynamicWidth+" "+e.getY()/dynamicHeight));
                }
                else if (ellipseButton.isSelected()) {
                    Shape.lineCommands.add(new Ellipse("ELLIPSE "+ Ellipse.getFirstClickX()+ " " + Ellipse.getFirstClickY() + " " +e.getX()/dynamicWidth+" "+e.getY()/dynamicHeight));
                }
                revalidate();
                repaint();
            }
        });
    }

    public static void setConstraints(JPanel panel, int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty, GridBagConstraints c, Container pane) {
        c.fill = GridBagConstraints.BOTH;
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.weightx = weightx;
        c.weighty = weighty;
        pane.add(panel, c);
    }

    public void newButtonClick() {
        Shape.lineCommands.clear();
        revalidate();
        repaint();
    }

    //Container pane
    public void openButtonClick() {
        JFileChooser fileChooser = new JFileChooser();
        // Creates filter for VEC files, adds the filter to the open event and sets the filter as the default file type.
        FileFilter VECFilter = new FilterFileType(".VEC", "VEC Documents");
        fileChooser.addChoosableFileFilter(VECFilter);
        fileChooser.setFileFilter(VECFilter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                FileReaderClass.open(selectedFile.getPath());
                revalidate();
                repaint();
            }
            catch (Exception ex) {
            }
        }
    }

    public void saveButtonClick() {
        // set file filter
        JFileChooser fileChooser = new JFileChooser();

        FileFilter VECFilter = new FilterFileType(".VEC", "VEC Documents");
        fileChooser.addChoosableFileFilter(VECFilter);
        fileChooser.setFileFilter(VECFilter);

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                FileReaderClass.save(selectedFile.getPath()+".VEC");
                revalidate();
                repaint();
            }
            catch (Exception ex) {
            }
        }
    }

    @Override
    public void run() {
        createAndShowGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUI());
    }
}

class drawingPanel extends JPanel {

    public drawingPanel() {
        setSize(300,300);
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
