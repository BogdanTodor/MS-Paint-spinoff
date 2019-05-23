import org.junit.jupiter.api.*;
import java.awt.*;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LineTest {

    int size = 500;
    double[] positions = new double[4];
    double[] scaledPositions = new double [4];
    static double[] firstClickPositions = new double[2];
    String in = "LINE 0.0 0.0 1.0 1.0";

    Line newLine = new Line(in);

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
        firstClickPositions[0] = scaledPositions[0];
        firstClickPositions[1] = scaledPositions[1];
    }

    @Test
    public void toStringTest(){
        assertEquals(newLine.toString(), in);
    }

    // Requires modifications to drawShape
    @Test
    public void getx1Test(){
        assertEquals(newLine.getx1(), scaledPositions[0]);
    }

    @Test
    public void gety1Test(){
        assertEquals(newLine.gety1(), scaledPositions[1]);
    }

    @Test
    public void getx2Test(){
        assertEquals(newLine.gety1(), scaledPositions[2]);
    }

    @Test
    public void gety2Test(){
        assertEquals(newLine.gety1(), scaledPositions[3]);
    }

    @Test
    public void getFirstClickXAndYTest(){
        double firstClickXPosition = newLine.getx1();
        double firstClickYPosition = newLine.gety1();
        newLine.firstClick(firstClickXPosition, firstClickYPosition);

        assertEquals(newLine.getFirstClickX(), firstClickPositions[0]);
        assertEquals(newLine.getFirstClickY(), firstClickPositions[1]);
    }


}
