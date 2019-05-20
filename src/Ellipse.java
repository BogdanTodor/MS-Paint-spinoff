import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import static java.lang.Double.parseDouble;

public class Ellipse extends Ellipse2D  implements Shape{

    String inputString;
    double[] coords = new double[4];
    double[] coordsScaled = new double [4];
    static double[] firstClickCoords = new double[2];

    Ellipse(String input) {
        inputString = input;
        String s[] = input.split(" ");
        for (int i = 0; i < coords.length; i++) {
            coords[i] = parseDouble(s[i+1]);
        }
    }

    @Override
    public void drawShape(Graphics2D g, int size) {
        for (int i = 0; i < coordsScaled.length; i++) {
            coordsScaled[i] = coords[i]*size;
        }
        g.draw(new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight()));
    }

    @Override
    public String toString() {
        return inputString;
    }

    @Override
    public double getX() {
        return coordsScaled[0];
    }

    @Override
    public double getY() {
        return coordsScaled[1];
    }

    @Override
    public double getWidth() {
        return coordsScaled[2]-coordsScaled[0];
    }

    @Override
    public double getHeight() {
        return coordsScaled[3]-coordsScaled[1];
    }

    public static void firstClick(double x1, double y1) {
        firstClickCoords[0] = x1;
        firstClickCoords[1] = y1;
    }

    public static double getFirstClickX() {
        return firstClickCoords[0];
    }

    public static double getFirstClickY() { return firstClickCoords[1]; }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {

    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }
}
