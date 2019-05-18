import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI extends JFrame implements Runnable {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;
    public static double dynamicWidth;
    public static double dynamicHeight;
    public static boolean plotToggle;

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
        JButton newButton, openButton, saveButton;
        JToggleButton plotButton;

        newButton = new JButton("New");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newButtonClick(pane);
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        pane.add(newButton, c);

        openButton = new JButton("Open");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openButtonClick(pane);
            }
        });
        c.gridx = 1;
        c.gridy = 0;
        pane.add(openButton, c);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonClick(pane);
            }
        });
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(saveButton, c);

        // Moved panel creation here to not break GUI design
        mainPanel = new drawingPanel();

        // Create a mouse listener object
        MouseListener mousePosition = new MouseClickEvent();

        // Adds a toggle button which allows user to trigger mouse clicks as coordinates for plotting.
        plotButton = new JToggleButton("Plot");
        plotButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    System.out.println("Plotting enabled");
                    plotButton.setUI(new MetalToggleButtonUI(){
                        @Override
                        protected Color getSelectColor(){
                            return Color.GREEN;
                        };
                    });
                    plotToggle = true;
                    mainPanel.addMouseListener(mousePosition);

                } else if(e.getStateChange() == ItemEvent.DESELECTED){
                    System.out.println("Plotting disabled");
                    plotToggle = false;
                    mainPanel.removeMouseListener(mousePosition);
//                    for(int i = 1; i<mainPanel.getMouseListeners().length-1;i++){
//                        mainPanel.removeMouseListener(mainPanel.getMouseListeners()[i]);
//                    }
                }

            }
        });

        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 3;
        c.gridy = 0;
        pane.add(plotButton, c);

        topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setBorder(new LineBorder(Color.GRAY));
        setConstraints(topPanel,0,0,4,1,0,0,c,pane);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        setConstraints(leftPanel,0,1,2,1,0,0,c,pane);

//        mainPanel = new drawingPanel();
        setConstraints(mainPanel,2,1,1,1,1,1,c,pane);


        rightPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(pane.getWidth() - mainPanel.getWidth() - leftPanel.getWidth() - 3, 0);
            }
        };
        rightPanel.setBackground(Color.LIGHT_GRAY);
        setConstraints(rightPanel,3,1,1,1,0,0,c,pane);

        bottomPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, pane.getHeight() - mainPanel.getHeight() - topPanel.getHeight() -3);
            }
        };
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        setConstraints(bottomPanel,0,2,4,1,0,0,c,pane);

        mainPanel.addComponentListener(new canvasResizeEvent());
    }

    public static class MouseClickEvent implements MouseListener{
        public void mouseClicked(MouseEvent e) {
            if(plotToggle){
            System.out.println(e.getX()/dynamicWidth + ", " + e.getY()/dynamicHeight);
//            System.out.println(e.getX());
//            System.out.println(dynamicHeight);
            }
        }
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
    }

    public static class canvasResizeEvent implements ComponentListener{
        public void componentResized(ComponentEvent c) {
//            System.out.println(c.getComponent().getHeight());
//            System.out.println(WIDTH);
//            System.out.println((c));
            dynamicHeight = c.getComponent().getHeight();
            dynamicWidth = c.getComponent().getHeight();
        }
        public void componentMoved(ComponentEvent c) {
//            System.out.println(c);
        }
        public void componentShown(ComponentEvent c) {
            System.out.println(c);
        }
        public void componentHidden(ComponentEvent c) {
//            System.out.println(c);
        }

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

    public static void newButtonClick(Container pane) {
        Shape.lineCommands.clear();
        pane.validate();
    }

    public static void openButtonClick(Container pane) {
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
                pane.validate();
            }
            catch (Exception ex) {
            }
        }
    }

    public static void saveButtonClick(Container pane) {
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
                pane.validate();
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
