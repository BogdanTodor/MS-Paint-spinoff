import java.awt.*;
import java.awt.geom.Ellipse2D;

/** Used to draw pixel shapes on the canvas.*/
public class Plot implements Shape {

    /** Stores the x and y position of the most recent mouse click*/
    private double[] coords = new double[2];

    /** The values stores in 'coords' scaled to the screen size.*/
    private double[] coordsScaled = new double [2];

    /** The string representation of the plot shape.*/
    private String inputString;

    /** Recieves the position of the mouse clicks and populates the coords array.
     * @param input The raw position of each mouse click registered by the plot shape function*/
    Plot( String input) throws ShapeException {
        inputString = input;
        String s[] = input.split(" ");
        if(s.length < 3){
            throw new ShapeException("Invalid number of coordinates - Less than 2 coordinates provided.");
        }
        else if(s.length > 3){
            throw new ShapeException("Invalid number of coordinates - More than 2 coordinates provided.");
        }
        for (int i = 0; i < coords.length; i++) {
            try{
                coords[i] = Double.parseDouble(s[i+1]);
            } catch(NumberFormatException e){
                throw new ShapeException("Invalid format for coordinate input.");
            }
        }
    }

    /** Override function which scales the mouse click positions according to the canvas size and
     * draws a dot on the canvas according to the mouse click positions collected in Plot().
     * @param g The graphics driver for drawing 2-d shapes
     * @param size The length of the canvas sides
     * */
    @Override
    public void drawShape(Graphics2D g, int size) {
        for (int i = 0; i < coordsScaled.length; i++) {
            coordsScaled[i] = coords[i]*size;
        }
        Ellipse2D.Double shape = new Ellipse2D.Double(getx1(), gety1(), 1, 1);
        g.draw(shape);
    }

    /** @return The string representation of the Plot shape.*/
    @Override
    public String toString() {
        return inputString;
    }

    /** @return The horizontal coordinate of the mouse click.*/
    public double getx1() {
        return coordsScaled[0];
    }

    /** @return The vertical coordinate of the mouse click.*/
    public double gety1() {
        return coordsScaled[1];
    }
}
