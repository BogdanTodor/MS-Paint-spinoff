import java.awt.*;
import java.awt.geom.Line2D;

public class Line implements Shape {

    String inputString;
    double[] coords = new double[4];
    double[] coordsScaled = new double [4];

    Line( String input) {
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
        g.draw(new Line2D.Double(getx1(), gety1(), getx2(), gety2()));
    }

    public String toString() {
       return inputString;
    }

    public double getx1() {
        return coordsScaled[0];
    }

    public double gety1() {
        return coordsScaled[1];
    }

    public double getx2() {
        return coordsScaled[2];
    }

    public double gety2() {
        return coordsScaled[3];
    }

}
