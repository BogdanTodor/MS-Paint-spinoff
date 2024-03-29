import java.awt.*;
import java.awt.geom.Path2D;

import static java.lang.Double.parseDouble;

/** Used to draw the polygon shape object*/
public class Polygon implements Shape {

    /** An array storing all the horizontal mouse click locations.*/
    private double[] xcoords;

    /** An array storing all the vertical mouse click locations.*/
    private double[] ycoords;

    /** xcoords scaled to the screen size, lies between 0 and 1.*/
    private double[] xcoordsScaled;

    /** ycoords scaled to the screen size, lies between 0 and 1.*/
    private double[] ycoordsScaled;

    /** Stores the raw horizontal mouse click coordinates.*/
    static double[] ClickCoordsX = new double[100];

    /** Stores the raw vertical mouse click coordinates.*/
    static double[] ClickCoordsY = new double[100];

    /** The number of clicks registered for the polygon.*/
    static int clickCount = 0;

    /** The string representation of the shape.*/
    String inputString;

    /** Takes in the mouse click coordinates and stores the results in xcoords and ycoords.
     * @param input The raw position of each mouse click registered by the Polygon shape function
     * @exception ShapeException Throws a Shape Exception if the input cannot be handled.*/
    Polygon( String input) throws ShapeException {
        inputString = input;
        String s[] = input.split(" ");
        if((s.length % 2) == 0){
            throw new ShapeException("Number of x coordinates does not match number of y coordinates.");
        }
        if(s.length > 201){
            throw new ShapeException("Polygon has too many points.");
        }
        xcoords = new double[(s.length - 1)/2];
        ycoords = new double[(s.length - 1)/2];
        xcoordsScaled = new double[xcoords.length];
        ycoordsScaled = new double[ycoords.length];
        int xindex = 0;
        int yindex = 0;
        for (int i = 1; i < s.length; i++) {
            if (i%2 == 1 && i != 0) {
                try{
                    xcoords[xindex++] = parseDouble(s[i]);
                } catch(NumberFormatException e){
                    throw new ShapeException("Invalid format for coordinate input.");
                }

            }
            else if (i%2 == 0 && i != 0) {
                try{
                    ycoords[yindex++] = parseDouble(s[i]);
                } catch(NumberFormatException e){
                    throw new ShapeException("Invalid format for coordinate input.");
                }

            }
        }
    }

    /** Draws the polygon based on the arrays of scaled x and y coordinates.*/
    @Override
    public void drawShape(Graphics2D g, int size) {
        for (int i = 0; i < xcoords.length; i++) {
            xcoordsScaled[i] = xcoords[i]*size;
            ycoordsScaled[i] = ycoords[i]*size;
        }
        Path2D path = new Path2D.Double();
        path.moveTo(xcoordsScaled[0], ycoordsScaled[0]);
        for(int i = 1; i < xcoordsScaled.length; ++i) {
            path.lineTo(xcoordsScaled[i], ycoordsScaled[i]);
        }
        path.closePath();
        g.draw(path);
        if(Colors.getIsFillOn() == true) {
            g.setColor(Colors.getFillColor()); // set color to fill color
            g.fill(path);
        }
        g.setColor(Colors.getPenColor()); // set color back to pen color for other shapes
    }

    /** @return string representation of polygon.*/
    @Override
    public String toString() {
        return inputString;
    }

    /** Increments the click counter by 1.*/
    public static void addClick() {
        clickCount++;
    }

    /**@return click counter.*/
    public static int getClickCount() {
        return clickCount;
    }

    /**Resets the click counter to 0.*/
    public static void resetClickCount() {
        clickCount = 0;
    }
}
