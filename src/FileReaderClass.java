import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/** Used to construct string representation of shapes from array elements, open files and save files. */
public class FileReaderClass {


    public static String input;


    /** Extract each element from the input array and save them as a string.
     * @param Array The array of elements to be extracted and saved as a string.
     * @return The string constructed from the elements of the input array.*/
    public static String stringExtractor(String Array[]){
        input = "";
        for(int wordPosition = 0; wordPosition < Array.length; wordPosition++){
            input += Array[wordPosition] + " ";
        }
        return input;
    }

    /** Opens the selected VEC formatted file and loads the commands stored within the file.
     * @param path The path of the file to be opened.*/
    public static void open(String path)throws Exception{
        File file = new File(path);

        BufferedReader filecontent = new BufferedReader(new FileReader(file));

        String line;
        Shape.lineCommands.clear(); // clears any previously opened VEC file

        while((line = filecontent.readLine()) != null){
            String[] splitted = line.split(" ");
            // Display output of the read lines.
            System.out.println(stringExtractor(splitted));

            if (splitted[0].equals("LINE")) {
                Shape.lineCommands.add(new Line(stringExtractor(splitted)));
            }
            else if (splitted[0].equals("PLOT")) {
                Shape.lineCommands.add(new Plot(stringExtractor(splitted)));
            }
            else if (splitted[0].equals("RECTANGLE")) {
                Shape.lineCommands.add(new Rectangle(stringExtractor(splitted)));
            }
            else if (splitted[0].equals("PEN")) {
                Shape.lineCommands.add(new Pen(stringExtractor(splitted)));
            }
            else if (splitted[0].equals("FILL")) {
                Shape.lineCommands.add(new Fill(stringExtractor(splitted)));
            }
            else if (splitted[0].equals("ELLIPSE")) {
                Shape.lineCommands.add(new Ellipse(stringExtractor(splitted)));
            }
            else if (splitted[0].equals("POLYGON")) {
                Shape.lineCommands.add(new Polygon(stringExtractor(splitted)));
            }
        }
        filecontent.close();
    }

    /** Saves the current canvas and drawing data as a VEC formatted file.
     * @param SaveLocation The path to the save location of the file.*/
    public static void save(String SaveLocation) throws Exception {
        PrintWriter out = new PrintWriter(SaveLocation);

        for(Shape shape: Shape.lineCommands){
            String l = shape.toString();
            out.println(l);
            System.out.println(l);
        }
        out.close();
    }

}
