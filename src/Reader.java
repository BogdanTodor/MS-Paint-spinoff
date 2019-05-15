import javax.swing.*;
import java.awt.*;

public class Reader {

    static String star = "LINE 0.51 0.08 0.65 0.31\n" +
            "LINE 0.65 0.31 0.95 0.29\n" +
            "LINE 0.95 0.29 0.78 0.52\n" +
            "LINE 0.78 0.52 0.95 0.74\n" +
            "LINE 0.95 0.74 0.65 0.72\n" +
            "LINE 0.65 0.72 0.51 0.95\n" +
            "LINE 0.51 0.95 0.37 0.72\n" +
            "LINE 0.37 0.72 0.07 0.73\n" +
            "LINE 0.07 0.73 0.25 0.52\n" +
            "LINE 0.25 0.52 0.07 0.29\n" +
            "LINE 0.07 0.29 0.38 0.31\n" +
            "LINE 0.38 0.31 0.51 0.08";

    static String line1 = "LINE 0.0 0.0 1.0 1.0";

    static String plot1 = "PLOT 0.5 0.5\n" +
            "PLOT 0.49 0.49\n" +
            "PLOT 0.48 0.48\n" +
            "PLOT 0.51 0.49\n" +
            "PLOT 0.52 0.48\n" +
            "PLOT 0.51 0.51\n" +
            "PLOT 0.52 0.52\n" +
            "PLOT 0.49 0.51\n" +
            "PLOT 0.48 0.52";

    static String coloredRectangles = "PEN #FF0000\n" +
            "RECTANGLE 0.1 0.1 0.9 0.3\n" +
            "PEN #00FF00\n" +
            "RECTANGLE 0.1 0.4 0.9 0.6\n" +
            "PEN #0000FF\n" +
            "RECTANGLE 0.1 0.7 0.9 0.9";

    static String coloredRectangles2 = "RECTANGLE 0.1 0.1 0.5 0.5\n" +
            "RECTANGLE 0.2 0.2 0.6 0.6\n" +
            "PEN #0000FF\n" +
            "RECTANGLE 0.3 0.3 0.7 0.7\n" +
            "RECTANGLE 0.4 0.4 0.8 0.8\n" +
            "RECTANGLE 0.5 0.5 0.9 0.9";

    static String fillRectangle = "PEN #FF0000\n" +
            "FILL #FFFF00\n" +
            "RECTANGLE 0.2 0.2 0.8 0.8";

    static String fillRectangle2 = "RECTANGLE 0.1 0.1 0.4 0.4\n" +
            "RECTANGLE 0.15 0.15 0.45 0.45\n" +
            "FILL #FFFFFF\n" +
            "RECTANGLE 0.6 0.1 0.9 0.4\n" +
            "RECTANGLE 0.65 0.15 0.95 0.45";

    static String fillRectangle3 = "FILL #FFFFFF\n" +
            "RECTANGLE 0.1 0.1 0.4 0.4\n" +
            "RECTANGLE 0.15 0.15 0.45 0.45\n" +
            "FILL OFF\n" +
            "RECTANGLE 0.6 0.1 0.9 0.4\n" +
            "RECTANGLE 0.65 0.15 0.95 0.45";


    public static void readFile(String input){
        String commands[] = input.split("\n");
        for (int i = 0; i < commands.length; i++) {
            String s[] = commands[i].split(" ");
            if (s[0].equals("LINE")) {
                Shape.lineCommands.add(new Line(commands[i]));
            }
            else if (s[0].equals("PLOT")) {
                Shape.lineCommands.add(new Plot(commands[i]));
            }
            else if (s[0].equals("RECTANGLE")) {
                Shape.lineCommands.add(new Rectangle(commands[i]));
            }
            else if (s[0].equals("PEN")) {
                Shape.lineCommands.add(new Pen(commands[i]));
            }
            else if (s[0].equals("FILL")) {
                Shape.lineCommands.add(new Fill(commands[i]));
            }
        }
    }

    public static void main(String[] args) {
//        readFile(star);
//        readFile(line1);
//        readFile(plot1);
//        readFile(coloredRectangles);
//        readFile(coloredRectangles2);
        /*
        FOLLOWING VEC FORMATS NOT SUPPORTED YET
         */
//        readFile(fillRectangle);
        readFile(fillRectangle2);
//        readFile(fillRectangle3);
        SwingUtilities.invokeLater(new GUI());
    }
}