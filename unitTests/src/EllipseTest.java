import org.junit.jupiter.api.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EllipseTest{

    Graphics2D g;
    int size = 500;
    double[] positions = new double[4];
    double[] scaledPositions = new double [4];
    static double[] firstClickPositions = new double[2];
    String in = "ELLIPSE 0.5 0.5 1.0 1.0";

    Ellipse ellipse = new Ellipse(in);


    @BeforeEach
    public void setup(){

        ellipse.drawShape(g, size);

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
        assertEquals(ellipse.toString(), in);
        System.out.println(ellipse.toString());
    }

    @Test
    public void getXTest(){
        assertEquals(ellipse.getX(), scaledPositions[0]);
        System.out.println(ellipse.getX());
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

        System.out.println(ellipse.getWidth());

        assertEquals(ellipse.getWidth(), scaledPositions[2]-scaledPositions[0]);
    }

    @Test
    public void getHeightTest(){
        assertEquals(ellipse.getHeight(), scaledPositions[3]-scaledPositions[1]);
    }

    @Test
    public void getFirstClickXAndYTest(){
        double firstClickXPosition = ellipse.getX();
        double firstClickYPosition = ellipse.getY();
        ellipse.firstClick(firstClickXPosition, firstClickYPosition);

        assertEquals(ellipse.getFirstClickX(), firstClickPositions[0]);
        assertEquals(ellipse.getFirstClickY(), firstClickPositions[1]);
    }

    @Test
    public void isEmptyTest(){
        assertEquals(ellipse.isEmpty(), false);
    }

}
