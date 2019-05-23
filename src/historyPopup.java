import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;

public class historyPopup {

    public JPopupMenu popup;
    public Container pane;
    public LinkedList<Shape> lineCommandsOriginal;

    public historyPopup(Container pane) {
        this.pane = pane;
        popup = new JPopupMenu();
        lineCommandsOriginal = Shape.lineCommands; // Copy to use if user clicks out of menu popup

        ActionListener menuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Shape shape = null;
                String vecItem = event.getActionCommand();
                String[] splitted = vecItem.split(" ");
                // Display output of the read lines.

                if (splitted[0].equals("LINE")) {
                    shape = new Line(vecItem);
                }
                else if (splitted[0].equals("PLOT")) {
                    shape = new Plot(vecItem);
                }
                else if (splitted[0].equals("RECTANGLE")) {
                    shape = new Rectangle(vecItem);
                }
                else if (splitted[0].equals("PEN")) {
                    shape = new Pen(vecItem);
                }
                else if (splitted[0].equals("FILL")) {
                    shape = new Fill(vecItem);
                }
                else if (splitted[0].equals("ELLIPSE")) {
                    shape = new Ellipse(vecItem);
                }
                else if (splitted[0].equals("POLYGON")) {
                    shape = new Polygon(vecItem);
                }

                int index = Integer.MAX_VALUE;
                for (int i = 0; i < Shape.lineCommands.size(); i++) {
                    if (shape.toString() == Shape.lineCommands.get(i).toString())
                    {
                        index = i;
                        System.out.println(i);
                        break;
                    }
                }
                while(Shape.lineCommands.size() > index+1) {
                    Shape.lineCommands.remove(index+1);
                }
            }
        };
        for (int i = lineCommandsOriginal.size(); i-- > 0; ) {
            JMenuItem item = new JMenuItem(lineCommandsOriginal.get(i).toString());
            popup.add(item);
            item.addActionListener(menuListener);
        }
        popup.show(pane, 10, 50);
    }
}
