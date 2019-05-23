import org.junit.jupiter.api.*;
import java.awt.*;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlotTest {

    int size = 500;
    double[] positions = new double[2];
    double[] scaledPositions = new double [2];
    String in = "PLOT 0.49 0.49";

    Plot newPlot = new Plot(in);

    @BeforeEach
    public void setup(){
//        Graphics2D graphic2D = (Graphics2D) graphic;
        String s[] = in.split(" ");
        for (int i = 0; i < positions.length; i++) {
            positions[i] = parseDouble(s[i+1]);
        }
        for (int i = 0; i < scaledPositions.length; i++) {
            scaledPositions[i] = positions[i]*size;
        }
    }

    @Test
    public void toStringTest(){
        assertEquals(newPlot.toString(), in);
    }

    @Test
    public void getx1Test(){
        assertEquals(newPlot.getx1(), scaledPositions[0]);
    }

    @Test
    public void gety1Test(){
        assertEquals(newPlot.gety1(), scaledPositions[1]);
    }

}
