import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import static java.lang.Double.parseDouble;

// Extends Rectangle2D to make use of the fill() method for future functionality.

public class Rectangle extends Rectangle2D implements Shape {

    double[] coords = new double[4];
    double[] coordsScaled = new double [4];

    Rectangle( String input) {
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

    @Override
    public double getX() {
        return coordsScaled[0];
    }

    @Override
    public double getY() {
        return coordsScaled[1];
    }

    public double getX2() {
        return coordsScaled[2];
    }

    public double getY2() {
        return coordsScaled[3];
    }

    @Override
    public double getWidth() {
        return coordsScaled[2] - coordsScaled[0];
    }

    @Override
    public double getHeight() {
        return coordsScaled[3] - coordsScaled[1];
    }

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
