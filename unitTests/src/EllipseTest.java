import org.junit.jupiter.api.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.*;

public class EllipseTest{

    Graphics2D g;
    int size = 500;
    double[] positions = new double[4];
    double[] scaledPositions = new double [4];
    static double[] firstClickPositions = new double[2];
    String in = "ELLIPSE 0.5 0.5 1.0 1.0";


    Ellipse ellipse = null;
//    = new Ellipse(in);

    @BeforeEach
    public void setup(){
        try{
            ellipse = new Ellipse(in);
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
        assertEquals(ellipse.toString(), in);
        System.out.println(ellipse.toString());
    }

    @Test
    public void isEmptyTest(){
        assertEquals(ellipse.isEmpty(), false);
    }

    @Test
    public void coordinateFormatExceptionTest(){
        String inputTest = "ELLIPSE 0.5.5 0.5 1.0 1.0";
        ShapeException ellipseThrown1 = assertThrows(ShapeException.class, () ->{
            Ellipse ellipseTest = new Ellipse(inputTest);
            throw new ShapeException("Invalid format for coordinate input.");
        });
        assertEquals("Invalid format for coordinate input.", ellipseThrown1.getMessage());
    }

    @Test
    public void lessThan4CoordinatesExceptionTest(){
        String inputTest = "ELLIPSE 0.5 0.5 1.0";
        ShapeException ellipseThrown2 = assertThrows(ShapeException.class, () ->{
            Ellipse ellipseTest = new Ellipse(inputTest);
            throw new ShapeException("Invalid number of coordinates - Less than 4 coordinates provided.");
        });
        assertEquals("Invalid number of coordinates - Less than 4 coordinates provided.", ellipseThrown2.getMessage());
    }

    @Test
    public void moreThan4CoordinatesExceptionTest(){
        String inputTest = "ELLIPSE 0.5 0.5 1.0 1.0 0.5";
        ShapeException ellipseThrown3 = assertThrows(ShapeException.class, () ->{
            Ellipse ellipseTest = new Ellipse(inputTest);
            throw new ShapeException("Invalid number of coordinates - More than 4 coordinates provided.");
        });
        assertEquals("Invalid number of coordinates - More than 4 coordinates provided.", ellipseThrown3.getMessage());
    }
}
