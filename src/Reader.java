import javax.swing.*;
import java.util.LinkedList;
import java.util.SplittableRandom;

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

    public static void readFile(String input){
        String commands[] = input.split("\n");
        for (int i = 0; i < commands.length; i++) {
            String s[] = commands[i].split(" ");
            if (s[0].equals("LINE")) {
                Shape.lineCommands.add(new Line(commands[i]));
            }
        }
    }

    public static void main(String[] args) {
        readFile(star);
        SwingUtilities.invokeLater(new GUI());
    }

}