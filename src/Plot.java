import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Plot implements Shape {

    double[] coords = new double[2];
    double[] coordsScaled = new double [2];
    String inputString;

    Plot( String input) {
        inputString = input;
        String s[] = input.split(" ");
        for (int i = 0; i < coords.length; i++) {
            coords[i] = Double.parseDouble(s[i+1]);
        }
    }

    @Override
    public void drawShape(Graphics2D g, int size) {
        for (int i = 0; i < coordsScaled.length; i++) {
            coordsScaled[i] = coords[i]*size;
        }
        Ellipse2D.Double shape = new Ellipse2D.Double(getx1(), gety1(), 1, 1);
        g.draw(shape);
//        System.out.println("Plotted point");
    }

    @Override
    public String toString() {
        return inputString;
    }

    public double getx1() {
        return coordsScaled[0];
    }

    public double gety1() {
        return coordsScaled[1];
    }
}
