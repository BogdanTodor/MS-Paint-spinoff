import javax.swing.*;

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

    static String rectangle1 = "RECTANGLE 0.25 0.25 0.75 0.5";

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

        }
    }

    public static void main(String[] args) {
        readFile(star);
        readFile(line1);
        readFile(plot1);
        readFile(rectangle1);
        SwingUtilities.invokeLater(new GUI());
    }
}