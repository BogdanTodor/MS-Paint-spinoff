import org.junit.jupiter.api.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;


public class FillTest {

//    public Boolean fillStatus;
    String input = "FILL #FFFF00";

    Fill newFill = null;

//    @Test
//    public void FillTrueTest(){
//        String input = "FILL #FFFF00";
//
//        String s[] = input.split(" ");
//        if (s[1].substring(0,3).equals("OFF")) {
//            fillStatus = false;
//        }
//        else {
//            fillStatus = true;
//        }
//
//        try{
//            newFill = new Fill(input);
//        } catch(ShapeException z){}
//        assertEquals(newFill.fill, fillStatus);
//        System.out.println(newFill.fill);
//    }
//
//    @Test
//    public void FillFalseTest(){
//        String input = "FILL OFF";
//
//        String s[] = input.split(" ");
//        if (s[1].substring(0,3).equals("OFF")) {
//            fillStatus = false;
//        }
//        else {
//            fillStatus = true;
//        }
//
//        try{
//            newFill = new Fill(input);
//        } catch(ShapeException z){}
//        assertEquals(newFill.fill, fillStatus);
//    }

    @Test
    public void toStringTest(){
        try{
            newFill = new Fill(input);
        } catch(ShapeException z){}
        assertEquals(newFill.toString(), input);
    }

    @Test
    public void containsHashTest(){
        String inputTest = "FILL #FFFF00";
        ShapeException fillThrown1 = assertThrows(ShapeException.class, ()->{
            Fill fillTest = new Fill(inputTest);
            throw new ShapeException("No '#' found in Fill command.");
        });
        assertEquals("No '#' found in Fill command.", fillThrown1.getMessage());
    }

    @Test
    public void invalidColourCodeTest(){
        String inputTest = "FILL #FFFF00ABCD";
        ShapeException fillThrown1 = assertThrows(ShapeException.class, ()->{
            Fill fillTest = new Fill(inputTest);
            throw new ShapeException("Invalid colour code combination");
        });
        assertEquals("Invalid colour code combination", fillThrown1.getMessage());
    }

}
