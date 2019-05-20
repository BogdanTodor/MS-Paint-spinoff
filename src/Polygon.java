import java.awt.*;
import java.awt.geom.Path2D;

import static java.lang.Double.parseDouble;

public class Polygon implements Shape {

    double[] xcoords;
    double[] ycoords;
    double[] xcoordsScaled;
    double[] ycoordsScaled;
    static double[] ClickCoordsX = new double[100];
    static double[] ClickCoordsY = new double[100];
    static int clickCount = 0;
    String inputString;

    Polygon( String input) {
        inputString = input;
        String s[] = input.split(" ");
        xcoords = new double[(s.length - 1)/2];
        ycoords = new double[(s.length - 1)/2];
        xcoordsScaled = new double[xcoords.length];
        ycoordsScaled = new double[ycoords.length];
        int xindex = 0;
        int yindex = 0;
        for (int i = 1; i < s.length; i++) {
            if (i%2 == 1 && i != 0) {
                xcoords[xindex++] = parseDouble(s[i]);
            }
            else if (i%2 == 0 && i != 0) {
                ycoords[yindex++] = parseDouble(s[i]);
            }
        }
    }

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

    @Override
    public String toString() {
        return inputString;
    }

    public static void addClick() {
        clickCount++;
    }

    public static int getClickCount() {
        return clickCount;
    }

    public static void resetClickCount() {
        clickCount = 0;
    }
}
