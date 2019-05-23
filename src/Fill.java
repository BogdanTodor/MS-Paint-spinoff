import java.awt.*;

/** Used to fill hollow shapes with colour. */
public class Fill implements Shape {

    /** The colour of the fill or pen fill.*/
    public Color color;
    /** The state of whether or not fill is on or off*/
    public Boolean fill;
    /** The mouse click position expressed as x,y coordinate pairs*/
    String inputString ;

    /** Fills the inside portion of the shape with colour if the fill instruction is declared on the gui or in an
     * opened file.
     * @param input The string representation of a shape object*/
    Fill (String input) throws ShapeException{
        inputString = input;
        String s[] = input.split(" ");
        if (s[1].substring(0,3).equals("OFF")) {
            fill = false;
        }
        else {
            fill = true;
            int rr = Integer.parseInt(s[1].substring(1,3), 16);
            int gg = Integer.parseInt(s[1].substring(3,5), 16);
            int bb = Integer.parseInt(s[1].substring(5,7), 16);

            try{
                this.color = new Color(rr, gg, bb);
            } catch(Exception e){
                throw new ShapeException("Invalid colour pattern.");
            }
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
