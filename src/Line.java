import java.awt.*;
import java.awt.geom.Line2D;

/** Used to draw the line shape object*/
public class Line implements Shape {

    /** Stores the input string broken into two pairs of raw x,y coordinates.*/
    private double[] coords = new double[4];

    /** Stores the pair of x,y coordinates stores in coords scaled according to the canvas resolution
     * such that the values lie between 0 and 1.*/
    private double[] coordsScaled = new double [4];

    /** Stores position of the initial mouse click*/
    private static double[] firstClickCoords = new double[2];

    /** The string used to parse the mouse position into the Ellipse shape.*/
    private String inputString;

    /** Recieves the position of the mouse clicks, splits them and populates the coords array
     * @param input The raw position of each mouse click registered by the line shape function.
     * @exception ShapeException Throws a Shape Exception if the input cannot be handled.*/
    Line( String input) throws ShapeException {
        inputString = input;
        String s[] = input.split(" ");
        if(s.length < 5){
            throw new ShapeException("Invalid number of coordinates - Less than 4 coordinates provided.");
        }
        else if(s.length > 5){
            throw new ShapeException("Invalid number of coordinates - More than 4 coordinates provided.");
        }
        for (int i = 0; i < coords.length; i++) {
            try{
                coords[i] = Double.parseDouble(s[i+1]);
            } catch (NumberFormatException e){
                throw new ShapeException("Invalid format for coordinate input.");
            }

        }
    }

    /** Scales the mouse click positions to the size of the drawing canvas and draws the line shape given the four
     * coordinates in "coordsScaled".
     * @param g The graphics engine.
     * @param size The size of the canvas side*/
    @Override
    public void drawShape(Graphics2D g, int size) {
        for (int i = 0; i < coordsScaled.length; i++) {
            coordsScaled[i] = coords[i]*size;
        }
        g.draw(new Line2D.Double(getx1(), gety1(), getx2(), gety2()));
    }

    /** @return The string representation of the line shape.*/
    @Override
    public String toString() {
        return inputString;
    }

    /** @return The starting horizontal position of the line.*/
    public double getx1() {
        return coordsScaled[0];
    }

    /** @return The starting vertical position of the line.*/
    public double gety1() {
        return coordsScaled[1];
    }

    /** @return The ending horizontal position of the line.*/
    public double getx2() {
        return coordsScaled[2];
    }

    /** @return The ending vertical position of the line.*/
    public double gety2() {
        return coordsScaled[3];
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

}
