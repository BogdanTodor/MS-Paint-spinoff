import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import static java.lang.Double.parseDouble;

// Extends Rectangle2D to make use of the fill() method for future functionality.

public class Rectangle extends Rectangle2D implements Shape {

    /** The string used to parse the mouse position into the rectangle shape.*/
    String inputString;

    /** Stores the input string broken into two pairs of raw x,y coordinates.*/
    double[] coords = new double[4];

    /** Stores the pair of x,y coordinates stores in coords scaled according to the canvas resolution
     * such that the values lie between 0 and 1.*/
    double[] coordsScaled = new double [4];

    /** Stores position of the initial mouse click*/
    static double[] firstClickCoords = new double[2];

    /** Recieves the position of the mouse clicks, splits them and populates the coords array
     * @param input The raw position of each mouse click registered by the rectangle shape function*/
    Rectangle( String input) {
        inputString = input;
        String s[] = input.split(" ");
        for (int i = 0; i < coords.length; i++) {
            try {
                coords[i] = parseDouble(s[i + 1]);
            } catch(NumberFormatException e){
            e.printStackTrace();
            }
        }
    }

    /** Draws the rectangle shape, sets the fill setting on or off and sets the colour.
     * @param size The size.
     * @param g The graphics engine.*/
    @Override
    public void drawShape(Graphics2D g, int size) {
        for (int i = 0; i < coordsScaled.length; i++) {
            coordsScaled[i] = coords[i]*size;
        }
        g.setColor(Colors.getPenColor());
        g.draw(new Line2D.Double(getX(), getY(), getX2(), getY()));
        g.draw(new Line2D.Double(getX2(), getY(), getX2(), getY2()));
        g.draw(new Line2D.Double(getX2(), getY2(), getX(), getY2()));
        g.draw(new Line2D.Double(getX(), getY2(), getX(), getY()));
        if(Colors.getIsFillOn() == true) {
            g.setColor(Colors.getFillColor()); // set color to fill color
            g.fill(this);
        }
        g.setColor(Colors.getPenColor()); // set color back to pen color for other shapes
    }

    /**@return The string representation of the rectangle shape.*/
    @Override
    public String toString() {
        return inputString;
    }

    /** @return The horizontal position of the first mouse click.*/
    @Override
    public double getX() {
        return coordsScaled[0];
    }

    /** @return The vertical position of the first mouse click.*/
    @Override
    public double getY() {
        return coordsScaled[1];
    }

    /** @return The horizontal position of the second mouse click.*/
    public double getX2() {
        return coordsScaled[2];
    }

    /** @return The vertical position of the second mouse click.*/
    public double getY2() {
        return coordsScaled[3];
    }

    /** @return The width of the rectangle.*/
    @Override
    public double getWidth() {
        return coordsScaled[2] - coordsScaled[0];
    }

    /** @return  The height of the rectangle.*/
    @Override
    public double getHeight() {
        return coordsScaled[3] - coordsScaled[1];
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
    public static double getFirstClickY() { return firstClickCoords[1]; }

    // Following methods not used, but required to override to extend Rectangle2D

    @Override
    public void setRect(double x, double y, double w, double h) {
    }

    @Override
    public int outcode(double x, double y) {
        return 0;
    }

    @Override
    public Rectangle2D createIntersection(Rectangle2D r) {
        return null;
    }

    @Override
    public Rectangle2D createUnion(Rectangle2D r) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
