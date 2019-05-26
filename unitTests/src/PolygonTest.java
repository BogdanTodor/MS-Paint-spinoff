import org.junit.jupiter.api.*;
import java.awt.*;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PolygonTest {

   String in = "POLYGON 0.51 0.08 0.65 0.31 0.95 0.29 0.78 0.52 0.95 0.74 0.65 0.72 0.51 0.95 0.37 0.72 0.07 0.73 0.25 0.52 0.07 0.29 0.38 0.31";

   Polygon newPolygon = null;

   @BeforeEach
   public void setup(){
      try{
         newPolygon = new Polygon(in);
      } catch(ShapeException z){
         System.out.println(z.getMessage());
      }
   }


   @Test
   public void toStringTest(){
      assertEquals(newPolygon.toString(), in);
   }

   @Test
   public void maxPointsTest(){
      // input string with over 200 coordinates
      String inputTest = "POLYGON 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10 1 2 3 4 5 6 7 8 9 10";
      ShapeException polygonThrown1 = assertThrows(ShapeException.class, () ->{
         Polygon polygonTest = new Polygon(inputTest);
         throw new ShapeException("Polygon has too many points.");
      });
      assertEquals("Polygon has too many points.", polygonThrown1.getMessage());
      System.out.println(polygonThrown1);
   }

   @Test
   public void nonPairCoordinateTest(){
      String inputTest = "POLYGON 0.51 0.08 0.65 0.31 0.95 0.29 0.8";
      ShapeException polygonThrown2 = assertThrows(ShapeException.class, () ->{
         Polygon polygonTest = new Polygon(inputTest);
         throw new ShapeException("Number of x coordinates does not match number of y coordinates.");
      });
      assertEquals("Number of x coordinates does not match number of y coordinates.", polygonThrown2.getMessage());
      System.out.println(polygonThrown2);
   }

   @Test
   public void invalidInputExceptionTest(){
      String inputTest = "POLYGON 0.51 0.08 0.65 0.31 0.9.5 0.2.9";
      ShapeException polygonThrown3 = assertThrows(ShapeException.class, () ->{
         Polygon polygonTest = new Polygon(inputTest);
         throw new ShapeException("Invalid format for coordinate input.");
      });
      assertEquals("Invalid format for coordinate input.", polygonThrown3.getMessage());
      System.out.println(polygonThrown3);
   }
}
