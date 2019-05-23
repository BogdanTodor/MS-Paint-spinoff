import java.awt.*;
import java.util.LinkedList;

/** Used to construct shapes*/
public interface Shape {
    /** Linkedlist to store the instructions of each shape. Contains the type of shape and the coordinates to draw it.*/
    public static LinkedList<Shape> lineCommands = new LinkedList<>();
    public static LinkedList<Shape> deletedLineCommands = new LinkedList<>();

    /** @param size The size.
     * @param g The graphics engine.*/
    public void drawShape(Graphics2D g, int size);

    /** Convert to string.*/
    public String toString();
}
