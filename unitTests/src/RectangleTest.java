import org.junit.jupiter.api.*;
import java.awt.*;
//import java.awt.Rectangle;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTest {

    int size = 500;
    double[] positions = new double[4];
    double[] scaledPositions = new double [4];
    static double[] firstClickPositions = new double[2];
    String in = "RECTANGLE 0.15 0.15 0.45 0.45";

    Rectangle newRectangle = null;
//    Rectangle newRectangle = null;

    @BeforeEach
    public void setup(){
        try{
            newRectangle  = new Rectangle(in);
        }catch(ShapeException z){}


        String s[] = in.split(" ");
        for (int i = 0; i < positions.length; i++) {
            positions[i] = parseDouble(s[i+1]);
        }
    }

    @Test
    public void toStringTest(){
        assertEquals(newRectangle.toString(), in);
    }

    @Test
    public void coordinateFormatExceptionTest(){
        String inputTest = "RECTANGLE 0.15 0.15 0.45 0.45";
        ShapeException rectThrown1 = assertThrows(ShapeException.class, () ->{
            Rectangle rectangleTest = new Rectangle(inputTest);
            throw new ShapeException("Invalid format for coordinate input.");
        });
        assertEquals("Invalid format for coordinate input.", rectThrown1.getMessage());
    }

    @Test
    public void lessThan4CoordinatesExceptionTest(){
        String inputTest = "RECTANGLE 0.15 0.15 0.45 0.45";
        ShapeException rectThrown2 = assertThrows(ShapeException.class, () ->{
            Rectangle rectangleTest = new Rectangle(inputTest);
            throw new ShapeException("Invalid number of coordinates - Less than 4 coordinates provided.");
        });
        assertEquals("Invalid number of coordinates - Less than 4 coordinates provided.", rectThrown2.getMessage());
    }

    @Test
    public void moreThan4CoordinatesExceptionTest(){
        String inputTest = "RECTANGLE 0.15 0.15 0.45 0.45";
        ShapeException rectThrown3 = assertThrows(ShapeException.class, () ->{
            Rectangle rectangleTest = new Rectangle(inputTest);
            throw new ShapeException("Invalid number of coordinates - More than 4 coordinates provided.");
        });
        assertEquals("Invalid number of coordinates - More than 4 coordinates provided.", rectThrown3.getMessage());
    }
}
