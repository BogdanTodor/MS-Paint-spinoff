import java.awt.*;

public class Fill implements Shape {

    public Color color;
    public Boolean fill;

    Fill (String input) {
        String s[] = input.split(" ");
        if (s[1].substring(0,3).equals("OFF")) {
            fill = false;
        }
        else {
            fill = true;
            int rr = Integer.parseInt(s[1].substring(1,3), 16);
            int gg = Integer.parseInt(s[1].substring(3,5), 16);
            int bb = Integer.parseInt(s[1].substring(5,7), 16);
            this.color = new Color(rr, gg, bb);
        }
    }

    @Override
    public void drawShape(Graphics2D g, int size) {
        Colors.setIsFillOn(fill);
        Colors.setFillColor(this.color);
    }

//    public static  Color getFillColor() {
//        return color;
//}
//
//    public static Boolean isFillOn() {
//        return fill;
//    }
}
