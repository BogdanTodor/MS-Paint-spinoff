import org.junit.jupiter.api.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class FillTest {

    public Boolean fillStatus;

    @Test
    public void FillTrueTest(){
        String input = "FILL #FFFF00";

        String s[] = input.split(" ");
        if (s[1].substring(0,3).equals("OFF")) {
            fillStatus = false;
        }
        else {
            fillStatus = true;
        }

        Fill newFill = new Fill(input);
        assertEquals(newFill.fill, fillStatus);
        System.out.println(newFill.fill);
    }

    @Test
    public void FillFalseTest(){
        String input = "FILL OFF";

        String s[] = input.split(" ");
        if (s[1].substring(0,3).equals("OFF")) {
            fillStatus = false;
        }
        else {
            fillStatus = true;
        }

        Fill newFill = new Fill(input);
        assertEquals(newFill.fill, fillStatus);
    }

    @Test
    public void toStringTest(){
        String input = "FILL #FFFF00";
        Fill newFill = new Fill(input);
        assertEquals(newFill.toString(), input);
    }




}
