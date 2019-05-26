import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/** Used to construct string representation of shapes from array elements, open files and save files. */
public class FileReader {


    private static String input;


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
     * @param path The path of the file to be opened.
     * @exception Exception Throws an exception on failure to open file.*/
    public static void open(String path) throws Exception{
        File file = new File(path);

        BufferedReader filecontent = new BufferedReader(new java.io.FileReader(file));

        String line;
        Shape.lineCommands.clear(); // clears any previously opened VEC file

        while((line = filecontent.readLine()) != null){
            String[] splitted = line.split(" ");
            // Display output of the read lines.
            System.out.println(stringExtractor(splitted));
            Shape.lineCommands.add(createShape(splitted[0],stringExtractor(splitted)));
        }
        filecontent.close();
    }

    /** Instantiates a new shape depending on the shape type.
     * @param shapeType A string representing the type of shape command to create.
     * @param command A string representing the coordinates for the shape command.
     * @exception ShapeException Throws a shape extension if shape command instantiation fails.
     * @return The shape created.
     * */
    public static Shape createShape(String shapeType, String command) throws ShapeException {
        Shape shape = null;
        try {
            if (shapeType.equals("LINE")) {
                shape = new Line(command);
            }
            else if (shapeType.equals("PLOT")) {
                shape = new Plot(command);
            }
            else if (shapeType.equals("RECTANGLE")) {
                shape = new Rectangle(command);
            }
            else if (shapeType.equals("PEN")) {
                shape = new Pen(command);
            }
            else if (shapeType.equals("FILL")) {
                shape = new Fill(command);
            }
            else if (shapeType.equals("ELLIPSE")) {
                shape = new Ellipse(command);
            }
            else if (shapeType.equals("POLYGON")) {
                shape = new Polygon(command);
            }
            else{
//            throw new FileTypeException();
            }
        }
        catch (ShapeException e) {
            throw e;
        }
        return shape;
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
