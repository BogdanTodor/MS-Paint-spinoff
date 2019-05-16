import java.awt.*;

public class Pen implements Shape {

    public Color color;

    String inputString;

    Pen (String input) {
        inputString = input;
        String s[] = input.split(" #");
        int rr = Integer.parseInt(s[1].substring(0,2), 16);
        int gg = Integer.parseInt(s[1].substring(2,4), 16);
        int bb = Integer.parseInt(s[1].substring(4,6), 16);
        color = new Color(rr, gg, bb);
    }

    @Override
    public void drawShape(Graphics2D g, int size) {
        Colors.setPenColor(this.color);
        g.setColor(Colors.getPenColor());
    }

    @Override
    public String toString() {
        return inputString;
    }
}
