import org.junit.jupiter.api.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EllipseTest{

    int size = 500;

    String in = "ELLIPSE 0.0 0.0 1.0 1.0";

    double[] positions = new double[4];

    double[] scaledPositions = new double [4];

    static double[] firstClickPositions = new double[2];

    Ellipse ellipse = new Ellipse(in);

    @BeforeEach
    public void setup(){
        String s[] = in.split(" ");
        for (int i = 0; i < positions.length; i++) {
            positions[i] = parseDouble(s[i+1]);
        }
        for (int i = 0; i < scaledPositions.length; i++) {
            scaledPositions[i] = positions[i]*size;
            System.out.println(scaledPositions[i]);
        }
    }

    @Test
    public void toStringTest(){
        assertEquals(ellipse.toString(), in);
    }

    @Test
    public void getXTest(){
        assertEquals(ellipse.getX(), scaledPositions[0]);
    }

    @Test
    public void getYTest(){
        assertEquals(ellipse.getY(), scaledPositions[1]);
    }

    @Test
    public void getBounds2DTest(){
        assertEquals(ellipse.getBounds2D(), null);
    }

    @Test
    public void getWidthTest(){
        assertEquals(ellipse.getWidth(), scaledPositions[2]-scaledPositions[0]);
    }

    @Test
    public void getHeightTest(){
        assertEquals(ellipse.getHeight(), scaledPositions[3]-scaledPositions[1]);
    }

    @Test
    public void getFirstClickXTest(){
        double firstClickXPosition = ellipse.getX();
        ellipse.firstClick(firstClickXPosition,ellipse.getY());
        assertEquals(ellipse.getFirstClickX(), ellipse.getX());
    }

}
