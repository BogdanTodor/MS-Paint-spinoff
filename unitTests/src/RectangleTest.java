import org.junit.jupiter.api.*;
import java.awt.*;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RectangleTest {

    int size = 500;
    double[] positions = new double[4];
    double[] scaledPositions = new double [4];
    static double[] firstClickPositions = new double[2];
    String in = "RECTANGLE 0.15 0.15 0.45 0.45";

    Rectangle newRectangle = new Rectangle(in);

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
        assertEquals(newRectangle.toString(), in);
    }

    @Test
    public void getX1Test(){
        assertEquals(newRectangle.getX(), scaledPositions[0]);
    }

    @Test
    public void getY1Test(){
        assertEquals(newRectangle.getY(), scaledPositions[1]);
    }

    @Test
    public void getX2Test(){
        assertEquals(newRectangle.getX2(), scaledPositions[2]);
    }

    @Test
    public void getY2Test(){
        assertEquals(newRectangle.getY2(), scaledPositions[3]);
    }

    @Test
    public void getWidthTest(){
        assertEquals(newRectangle.getWidth(), scaledPositions[2]-scaledPositions[0]);
    }

    @Test
    public void getHeightTest(){
        assertEquals(newRectangle.getHeight(), scaledPositions[3]-scaledPositions[1]);
    }

    @Test
    public void getFirstClickXAndYTest(){
        double firstClickXPosition = newRectangle.getX();
        double firstClickYPosition = newRectangle.getY();
        newRectangle.firstClick(firstClickXPosition, firstClickYPosition);

        assertEquals(newRectangle.getFirstClickX(), firstClickPositions[0]);
        assertEquals(newRectangle.getFirstClickY(), firstClickPositions[1]);
    }


}
