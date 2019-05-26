import java.awt.*;

/** Used to set the colour of the pen and store the pattern as a string.*/
public class Pen implements Shape {
    /** The colour of the pen.*/
    private Color color;

    /** The string representation of the chosen colour.*/
    private String inputString;

    /** Sets the colour of the pen based on the input pattern.
     * @param input The character representation of the chosen colour.*/
    Pen (String input) throws ShapeException {
        inputString = input;
        String s[];
        if(!input.contains("#")){
            throw new ShapeException("No '#' found in Pen command.");
        }
        s = input.split(" #");
        if(s[1].length() != 6){
            throw new ShapeException("Invalid colour command - Incorrect number of characters.");
        }
        else {
            int rr = Integer.parseInt(s[1].substring(0,2), 16);
            int gg = Integer.parseInt(s[1].substring(2,4), 16);
            int bb = Integer.parseInt(s[1].substring(4,6), 16);
            color = new Color(rr, gg, bb);
            Colors.setPenColor(this.color);
        }
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
