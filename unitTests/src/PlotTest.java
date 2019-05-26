import org.junit.jupiter.api.*;
import java.awt.*;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlotTest {

    int size = 500;
    double[] positions = new double[2];
    double[] scaledPositions = new double [2];
    String inp = "PLOT 0.49 0.49";

    Plot newPlot = null;

    @BeforeEach
    public void setup(){
        try{
            newPlot = new Plot(inp);
        } catch(ShapeException z){
            System.out.println(z.getMessage());
        }

        String s[] = inp.split(" ");
        for (int i = 0; i < positions.length; i++) {
            positions[i] = parseDouble(s[i+1]);
        }
    }

    @Test
    public void toStringTest(){
        assertEquals(newPlot.toString(), inp);
    }

    @Test
    void plotCoordinateFormatExceptionTest(){
        final ShapeException test1 = assertThrows(ShapeException.class, () ->{
            Plot plotTest = new Plot("PLOT 0.49 0.4.9");
            throw new ShapeException("Invalid format for coordinate input.");
        });
        assertEquals("Invalid format for coordinate input.", test1.getMessage());
    }

    @Test
    public void lessThan2CoordinatesExceptionTest(){
        String inputTest2 = "PLOT 0.49";
        final ShapeException test2 = assertThrows(ShapeException.class, () ->{
            Plot plotTest = new Plot(inputTest2);
            throw new ShapeException("Invalid number of coordinates - Less than 2 coordinates provided.");
        });
        assertEquals("Invalid number of coordinates - Less than 2 coordinates provided.", test2.getMessage());
    }

    @Test
    public void moreThan2CoordinatesExceptionTest(){
        String inputTest3 = "PLOT 0.49 0.49 0.49";
        final ShapeException test3 = assertThrows(ShapeException.class, () ->{
            Plot plotTest = new Plot(inputTest3);
            throw new ShapeException("Invalid number of coordinates - More than 2 coordinates provided.");
        });
        assertEquals("Invalid number of coordinates - More than 2 coordinates provided.", test3.getMessage());
    }

}
