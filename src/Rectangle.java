import java.awt.*;
import java.awt.geom.Line2D;

public class Rectangle implements Shape {

    double[] coords = new double[4];
    double[] coordsScaled = new double [4];

    Rectangle( String input) {
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
        g.draw(new Line2D.Double(getx1(), gety1(), getx2(), gety1()));
        g.draw(new Line2D.Double(getx2(), gety1(), getx2(), gety2()));
        g.draw(new Line2D.Double(getx2(), gety2(), getx1(), gety2()));
        g.draw(new Line2D.Double(getx1(), gety2(), getx1(), gety1()));
    }

    @Override
    public double getx1() {
        return coordsScaled[0];
    }

    @Override
    public double gety1() {
        return coordsScaled[1];
    }

    @Override
    public double getx2() {
        return coordsScaled[2];
    }

    @Override
    public double gety2() {
        return coordsScaled[3];
    }
}
