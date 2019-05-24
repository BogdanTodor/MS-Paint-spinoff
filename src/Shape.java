import java.awt.*;
import java.util.LinkedList;

/** Used to construct shapes*/
public interface Shape {
    /** Linkedlist to store the instructions of each shape. Contains the type of shape and the coordinates to draw it.*/
    LinkedList<Shape> lineCommands = new LinkedList<>();
    LinkedList<Shape> deletedLineCommands = new LinkedList<>();
    LinkedList<Shape> previewShape = new LinkedList<>();

    /** @param size The size.
     * @param g The graphics engine.*/
    void drawShape(Graphics2D g, int size);

    /** Convert to string.*/
    String toString();
}
