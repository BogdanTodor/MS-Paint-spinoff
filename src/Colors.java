import java.awt.*;

/** Used to set the colours of the pen and fill functionality.*/
public class Colors {

    /** The fill colour*/
    private static Color fillColor;

    /** The pen colour*/
    private static Color penColor = Color.BLACK;

    /** Boolean field which sets the fill to off.*/
    private static Boolean isFillOn = false;

    /**
     * Gets the state of the isFillOn variable.
     * @return returns the boolean value of isFillOn.
     * */
    public static Boolean getIsFillOn() {
        return isFillOn;
    }
    /**
     * Sets the fill functionality on or off.
     * @param arg the boolean value of isFillOn.
     *  */
    public static void setIsFillOn(Boolean arg) {
        isFillOn = arg;
    }
    /**
     * Gets the current fill colour.
     * @return returns the fill colour.
     * */
    public static Color getFillColor() {
        return fillColor;
    }

    /**
     * Gets the current colour of the pen.
     * @return the colour of the pen.
     * */
    public static Color getPenColor() {
        return penColor;
    }
    /**
     * Sets the colour for the fill.
     * @param color The desired color to change to.
     * */
    public static void setFillColor(Color color) {
        fillColor = color;
    }

    /**
     * Sets the colour of the pen to the selected colour.
     *  @param color The chosen colour to change the pen to.
     *  */
    public static void setPenColor(Color color) {
        penColor = color;
    }
}
