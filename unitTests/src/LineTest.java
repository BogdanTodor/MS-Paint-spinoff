import org.junit.jupiter.api.*;
import java.awt.*;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineTest {

    int size = 500;
    double[] positions = new double[4];
    double[] scaledPositions = new double [4];
    static double[] firstClickPositions = new double[2];
    String in = "LINE 0.0 0.0 1.0 1.0";

    Line newLine = null;

    @BeforeEach
    public void setup(){

        try{
            newLine = new Line(in);
        } catch(ShapeException z){
            System.out.println(z.getMessage());
        }

        String s[] = in.split(" ");
        for (int i = 0; i < positions.length; i++) {
            positions[i] = parseDouble(s[i+1]);
        }
    }

    @Test
    public void toStringTest(){
        assertEquals(newLine.toString(), in);
    }

    @Test
    public void coordinateFormatExceptionTest(){
        String inputTest = "LINE 0.0.0.0 0.0 1.0 1.0";
        ShapeException lineThrown1 = assertThrows(ShapeException.class, () ->{
            Line lineTest = new Line(inputTest);
            throw new ShapeException("Invalid format for coordinate input.");
        });
        assertEquals("Invalid format for coordinate input.", lineThrown1.getMessage());
    }

    @Test
    public void lessThan4CoordinatesExceptionTest(){
        String inputTest = "LINE 0.0 0.0 1.0";
        ShapeException lineThrown2 = assertThrows(ShapeException.class, () ->{
            Line lineTest = new Line(inputTest);
            throw new ShapeException("Invalid number of coordinates - Less than 4 coordinates provided.");
        });
        assertEquals("Invalid number of coordinates - Less than 4 coordinates provided.", lineThrown2.getMessage());
    }

    @Test
    public void moreThan4CoordinatesExceptionTest(){
        String inputTest = "LINE 0.0 0.0 1.0 1.0 1.0";
        ShapeException lineThrown3 = assertThrows(ShapeException.class, () ->{
            Line lineTest = new Line(inputTest);
            throw new ShapeException("Invalid number of coordinates - More than 4 coordinates provided.");
        });
        assertEquals("Invalid number of coordinates - More than 4 coordinates provided.", lineThrown3.getMessage());
    }

}
