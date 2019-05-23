import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import static java.lang.Double.parseDouble;

public class Ellipse extends Ellipse2D  implements Shape{

    /** The string used to parse the mouse position into the Ellipse shape.*/
    String inputString;
    /** Stores the input string broken into two pairs of raw x,y coordinates.*/
    double[] coords = new double[4];
    /** Stores the pair of x,y coordinates stores in coords scaled according to the canvas resolution
     * such that the values lie between 0 and 1.*/
    double[] coordsScaled = new double [4];
    /** Stores position of the initial mouse click*/
    static double[] firstClickCoords = new double[2];

    /** Recieves the position of the mouse clicks, splits them and populates the coords array
     * @param input The raw position of each mouse click registered by the Ellipse shape function*/
    Ellipse(String input) throws ShapeException{
        inputString = input;
        String s[] = input.split(" ");
        for (int i = 0; i < coords.length; i++) {
            try{
                coords[i] = parseDouble(s[i + 1]);
            }catch(Exception e){
                throw new ShapeException("Invalid coordinate input");
            }

        }
    }

    /** Override function which scales the mouse click positions according to the canvas size and
     * draws an ellipse on the canvas according to the mouse click positions collected in Elliple().
     * @param g The graphics driver for drawing 2-d shapes
     * @param size The length of the canvas sides
     * */
    @Override
    public void drawShape(Graphics2D g, int size) {
        for (int i = 0; i < coordsScaled.length; i++) {
            coordsScaled[i] = coords[i]*size;
        }
        g.draw(new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight()));
        if(Colors.getIsFillOn() == true) {
            g.setColor(Colors.getFillColor()); // set color to fill color
            g.fill(this);
        }
        g.setColor(Colors.getPenColor()); // set color back to pen color for other shapes
    }

    /** @return The mouse click positions.*/
    @Override
    public String toString() {
        return inputString;
    }

    /** @return The horizontal position of the mouse click.*/
    @Override
    public double getX() {
        return coordsScaled[0];
    }

    /** @return The vertical position of the mouse click.*/
    @Override
    public double getY() {
        return coordsScaled[1];
    }

    /** @return The width of the drawing canvas.*/
    @Override
    public double getWidth() {
        return coordsScaled[2]-coordsScaled[0];
    }

    /** @return The height of the drawing canvas.*/
    @Override
    public double getHeight() {
        return coordsScaled[3]-coordsScaled[1];
    }

    /** Assigns the x and y coordinates of the first mouse click.
     * @param x1 The horizontal position of the mouse click.
     * @param y1 The vertical position of the mouse click.*/
    public static void firstClick(double x1, double y1) {
        firstClickCoords[0] = x1;
        firstClickCoords[1] = y1;
    }

    /** @return The horizontal position of the first mouse click.*/
    public static double getFirstClickX() {
        return firstClickCoords[0];
    }

    /** @return The vertical position of the first mouse click.*/
    public static double getFirstClickY() {
        return firstClickCoords[1];
    }

    /** @return sets the field it is called on to false.*/
    @Override
    public boolean isEmpty() {
        return false;
    }

    /** Sets the rectangular frame which bounds the ellipse shape
     * @param x The horizontal position of the first vertex
     * @param y The vertical position of the first vertex
     * @param w The horizontal position of the second vertex which diagonally opposes the first vertex
     * @param h The vertical position of the second vertex*/
    @Override
    public void setFrame(double x, double y, double w, double h) {

    }

    /** Gets the bounds of the rectangle*/
    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }
}
