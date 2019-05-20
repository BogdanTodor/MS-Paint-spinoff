import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
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
    JToggleButton fillToggleButton;
    public static final int WIDTH = 460;
    public static final int HEIGHT = 470;
    public static double dynamicWidth;
    public static double dynamicHeight;

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setMinimumSize(new Dimension(460, 470));

        addComponentsToPane(getContentPane());
        setJMenuBar(createMenuBar());

        repaint();
        this.setVisible(true);
    }

    /**
     * Create the menu bar.
     */
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

        menu = new JMenu("Edit");
        menuBar.add(menu);

        menuItem = new JMenuItem("Grid size");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editGridSize();
            }
        });
        menu.add(menuItem);

        return menuBar;
    }

    /**
     * Adds components to the content pane, including:
     * Panels, Buttons, ColorChoosers, Tabs
     *
     * @parem pane the pane to add components to.
     *
     */
    public void addComponentsToPane(Container pane) {
        // Setup layout and components
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel mainPanel, leftPanel, rightPanel, bottomPanel;
        JButton undoButton;
        ButtonGroup drawingButtons = new ButtonGroup();
        JToggleButton plotButton, lineButton, rectangleButton, ellipseButton, polyButton, gridButton;
        JColorChooser penColorChooser, fillColorChooser;
        JTabbedPane colorTabs;

        // Initialise require components first
        mainPanel = new drawingPanel();
        fillColorChooser = new JColorChooser();

        /*
        ADD BUTTONS
         */
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
        fillToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fillToggleButton.isSelected()) {
                    Color newFillColor = fillColorChooser.getColor();
                    String hex = String.format("#%02x%02x%02x", newFillColor.getRed(), newFillColor.getGreen(), newFillColor.getBlue());
                    Shape.lineCommands.add(new Fill("FILL " + hex));
                    fillToggleButton.setText("Fill: On");
                }
                else if (!fillToggleButton.isSelected()) {
                    Shape.lineCommands.add(new Fill("FILL OFF"));
                    fillToggleButton.setText("Fill: Off");
                }
                revalidate();
                repaint();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        pane.add(fillToggleButton, c);

        gridButton = new JToggleButton("Grid: Off");
        gridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gridButton.isSelected()) {
                    gridButton.setText("Grid: On");

                }
                else if (!gridButton.isSelected()) {
                    gridButton.setText("Grid: Off");
                }
                revalidate();
                repaint();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(gridButton, c);

//        c.gridwidth = 1;
//        JLabel gridSizeLabel = new JLabel("     Grid size:");
//        c.gridx = 0;
//        c.gridy = 5;
//        pane.add(gridSizeLabel, c);
//
//        JTextField gridSizeField = new JTextField(5);
//        c.gridx = 1;
//        c.gridy = 5;
//        c.fill = GridBagConstraints.NONE;
//        pane.add(gridSizeField, c);

        /*
        ADD COLOR CHOOSERS
         */
        // Pen Color Chooser
        penColorChooser = new JColorChooser();
        penColorChooser.setPreviewPanel(new JPanel()); // removes the preview panel
        AbstractColorChooserPanel[] panels = penColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) { // remove other tabs
            if (!accp.getDisplayName().equals("Swatches")) {
                penColorChooser.removeChooserPanel(accp);
            }
        }
        ColorSelectionModel penModel = penColorChooser.getSelectionModel();
        ChangeListener changeListenerPen = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                Color newPenColor = penColorChooser.getColor();
                String hex = String.format("#%02x%02x%02x", newPenColor.getRed(), newPenColor.getGreen(), newPenColor.getBlue());
                Shape.lineCommands.add(new Pen("PEN " + hex));
            }
        };
        penModel.addChangeListener(changeListenerPen);

        // Fill Color Chooser
        fillColorChooser.setPreviewPanel(new JPanel()); // removes the preview panel
        AbstractColorChooserPanel[] panels2 = fillColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels2) { // remove other tabs
            if (!accp.getDisplayName().equals("Swatches")) {
                fillColorChooser.removeChooserPanel(accp);
            }
        }
        ColorSelectionModel fillModel = fillColorChooser.getSelectionModel();
        ChangeListener changeListenerFill = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                if (fillToggleButton.isSelected()) {
                    Color newFillColor = fillColorChooser.getColor();
                    String hex = String.format("#%02x%02x%02x", newFillColor.getRed(), newFillColor.getGreen(), newFillColor.getBlue());
                    Shape.lineCommands.add(new Fill("FILL " + hex));
                }
            }
        };
        fillModel.addChangeListener(changeListenerFill);

        // Add color choosers to respective tabs
        colorTabs = new JTabbedPane();
        colorTabs.addTab("Pen", penColorChooser);
        colorTabs.addTab("Fill", fillColorChooser);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        pane.add(colorTabs,c);

        /*
        ADD PANELS
         */

        // Left panel
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        setConstraints(leftPanel,0,0,2,6,0,0,c,pane);

        // Right panel
        rightPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(pane.getWidth() - mainPanel.getWidth() - leftPanel.getWidth() - 3, 0);
            }
        };
        rightPanel.setBackground(Color.LIGHT_GRAY);
        setConstraints(rightPanel,3,0,1,6,0,0,c,pane);

        // Bottom panel
        bottomPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, pane.getHeight() - mainPanel.getHeight() - menuBar.getHeight() + 20);
            }
        };
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        setConstraints(bottomPanel,0,6,4,1,0,0,c,pane);

        // Main drawing canvas panel
        setConstraints(mainPanel,2,0,1,6,1,1,c,pane);

        // Keep track of main drawing canvas panel size
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent c) {
                dynamicHeight = c.getComponent().getHeight();
                dynamicWidth = c.getComponent().getHeight();
            }
        });

        // Drawing canvas mouse listener: Listens for clicks (plot and polygon),
        // presses and releases (Line, rectangle, ellipse)
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

    /**
     * Sets constraints of the panel component and adds it to the pane.
     *
     * @parem panel the component to add to the pane
     * @parem gridx the x position
     * @parem gridy the y position
     * @parem gridwidth the width
     * @parem gridheight the height
     * @parem weightx x weighting
     * @parem weighty y weighting
     * @parem c the Grid Bag Constraints
     */
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


    public static void editGridSize() {
        String input = JOptionPane.showInputDialog(null, "Enter grid size ranging from 0 to 0.5");
        System.out.println(input);
    }

    /**
     * When menu bar > 'File' > 'new' is clicked, clear the current list of VEC shapes and repaint the canvas blank.
     * Fill toggle button is toggled to off.
     * Pen color defaults to black.
     *
     */
    public void newButtonClick() {
        Shape.lineCommands.clear();
        resetFillButton();
    }

    /**
     * Untoggles Fill button, and updates text to "Fill: Off".
     *
     */
    public void resetFillButton() {
        fillToggleButton.setSelected(false);
        fillToggleButton.setText("Fill: Off");
        revalidate();
        repaint();
    }

    /**
     * When menu bar > 'File' > 'open' is clicked, opens up a file chooser. Files with extension
     * '.VEC' are filtered for. When the file is selected, overwrite the current VEC shape file
     * with the target file, and immediately show on the drawing canvas. Fill and pen color
     * configuration is maintained from the open file.
     *
     */
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
                if (Colors.isFillOn) {
                    fillToggleButton.setSelected(true);
                    fillToggleButton.setText("Fill: On");
                }
                if (!Colors.isFillOn) {
                    resetFillButton();
                }
                revalidate();
                repaint();
            }
            catch (Exception ex) {
            }
        }
    }

    /**
     * When menu bar > 'File' > 'save' is clicked, opens up a file chooser. Files with extension
     * '.VEC' are filtered for. When the file name is specified, save the current VEC shape file
     * to the target file. The extension ".VEC" is added automatically.
     *
     */
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

//    /**
//     * Configure the grid.
//     *
//     * @parem state if the grid is on or off.
//     *
//     */
//    public void configureGrid(boolean state) {
//        if (state == true) {
//            gridButton.setText("Grid: On");
//        }
//    }

    @Override
    public void run() {
        createAndShowGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUI());
    }
}

class drawingPanel extends JPanel {

    /**
     * Sets default size to 300x300 and adds a Gray line border.
     *
     */
    public drawingPanel() {
        setSize(300,300);
        setBorder(new LineBorder(Color.GRAY));
        setVisible(true);
    }

    /**
     * Sets the size of the drawing panel to be the maximum possible square size of available space.
     * Iterates over each VEC shape command and draws to screen.
     *
     */
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int size = Math.min(getWidth(), getHeight());
        setSize(size, size);
        g.setColor(Color.BLACK);
        Colors.setPenColor(Color.BLACK);
        Colors.setIsFillOn(false);
        for (Shape shape : Shape.lineCommands) {
            shape.drawShape(g, size);
        }
    }
}
