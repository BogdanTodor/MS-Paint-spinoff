import java.awt.*;

public class Colors {
    public static Color fillColor;
    public static Color penColor = Color.BLACK;
    public static Boolean isFillOn = false;

    public static Boolean getIsFillOn() {
        return isFillOn;
    }

    public static void setIsFillOn(Boolean arg) {
        isFillOn = arg;
    }

    public static Color getFillColor() {
        return fillColor;
    }

    public static Color getPenColor() {
        return penColor;
    }

    public static void setFillColor(Color color) {
        fillColor = color;
    }

    public static void setPenColor(Color color) {
        penColor = color;
    }
}
