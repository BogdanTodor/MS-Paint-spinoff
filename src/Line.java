import java.awt.*;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Line implements Shape {

    double x1;
    double y1;
    double x2;
    double y2;

    Line( String input) {
        String s[] = input.split(" ");
        double p[] = new double[4];
        for(int i = 0 ; i < 4 ; i++) {
            p[i] = Double.parseDouble(s[i+1]) * 100;
        }
        this.x1 = p[0];
        this.y1 = p[1];
        this.x2 = p[2];
        this.y2 = p[3];
    }

    @Override
    public void drawShape(Graphics2D g) {
        g.draw(new Line2D.Double(getx1(), gety1(), getx2(), gety2()));
    }

    @Override
    public double getx1() {
        return x1;
    }

    @Override
    public double getx2() {
        return x2;
    }

    @Override
    public double gety1() {
        return y1;
    }

    @Override
    public double gety2() {
        return y2;
    }

}
