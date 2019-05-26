import java.awt.*;

/** Used to fill hollow shapes with colour. */
public class Fill implements Shape {

    /** The colour of the fill or pen fill.*/
    private Color color;
    /** The state of whether or not fill is on or off*/
    private Boolean fill;
    /** The mouse click position expressed as x,y coordinate pairs*/
    String inputString ;

    /** Fills the inside portion of the shape with colour if the fill instruction is declared on the gui or in an
     * opened file.
     * @param input The string representation of a shape object*/
    Fill (String input) throws ShapeException {
        inputString = input;
        String s[] = input.split(" ");
        if (s[1].substring(0,3).equals("OFF")) {
            fill = false;
        }
        else if(!s[1].substring(0,1).equals("#")){
            throw new ShapeException("'#' value incorrect in Fill command");
        }
        else if(s[1].length() != 7){
            throw new ShapeException("Invalid colour code combination");
        }
        else {
            fill = true;
            int rr = Integer.parseInt(s[1].substring(1,3), 16);
            int gg = Integer.parseInt(s[1].substring(3,5), 16);
            int bb = Integer.parseInt(s[1].substring(5,7), 16);
            this.color = new Color(rr, gg, bb);
        }
        Colors.setIsFillOn(fill);
    }

    /** Sets fill to false and the colour to what the user has chosen.
     * @param g The graphics engine used.
     * @param size The size.
     * */
    @Override
    public void drawShape(Graphics2D g, int size) {
        Colors.setIsFillOn(fill);
        Colors.setFillColor(this.color);
    }

    /** return The string representation of the fill input instructions*/
    @Override
    public String toString() {
        return inputString;
    }
}
