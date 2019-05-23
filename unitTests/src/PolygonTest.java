import org.junit.jupiter.api.*;
import java.awt.*;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PolygonTest {

   int clickCount = 0;
   String in = "POLYGON 0.51 0.08 0.65 0.31 0.95 0.29 0.78 0.52 0.95 0.74 0.65 0.72 0.51 0.95 0.37 0.72 0.07 0.73 0.25 0.52 0.07 0.29 0.38 0.31";

   Polygon newPolygon = new Polygon(in);


   @Test
   public void toStringTest(){
       assertEquals(newPolygon.toString(), in);
   }

   // to write the next few tests, fill in the coords array and the number of clicks should equal the number of x coordinates stored

   @Test
   public void addClickTest(){

   }

   @Test
    public void resetClickTest(){
//       clickCount = 10;
//       assertEquals(newPolygon.resetClickCount(), 0);
   }


}
