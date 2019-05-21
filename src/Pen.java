import java.awt.*;

/** Used to set the colour of the pen and store the pattern as a string.*/
public class Pen implements Shape {
    /** The colour of the pen.*/
    public Color color;

    /** The string representation of the chosen colour.*/
    String inputString;

    /** Sets the colour of the pen based on the input pattern.
     * @param input The character representation of the chosen colour.*/
    Pen (String input) {
        inputString = input;
        String s[] = input.split(" #");
        int rr = Integer.parseInt(s[1].substring(0,2), 16);
        int gg = Integer.parseInt(s[1].substring(2,4), 16);
        int bb = Integer.parseInt(s[1].substring(4,6), 16);
        color = new Color(rr, gg, bb);
    }

    /** Sets the colour of the pen in the graphics driver.
     * @param size The size.
     * @param g The graphics driver.*/
    @Override
    public void drawShape(Graphics2D g, int size) {
        Colors.setPenColor(this.color);
        g.setColor(Colors.getPenColor());
    }

    /** @return Character representation of the chosen colour.*/
    @Override
    public String toString() {
        return inputString;
    }
}
