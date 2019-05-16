import java.awt.*;
import java.util.LinkedList;

public interface Shape {

    public static LinkedList<Shape> lineCommands = new LinkedList<>();

    public void drawShape(Graphics2D g, int size);

    public String toString();
}
