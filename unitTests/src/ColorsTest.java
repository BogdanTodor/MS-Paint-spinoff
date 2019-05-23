import org.junit.jupiter.api.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class ColorsTest{

    Colors Colour = new Colors();

    @BeforeEach
    public void setup(){
        Colour = null;
    }

    @Test
    public void getIsFillOnTest(){
        Boolean fillStatus = Colour.getIsFillOn();
        assertEquals(fillStatus, false);
    }

    @Test
    public void setIsFillOnTest(){
        Boolean Actual = true;

        Colour.setIsFillOn(true);
        assertEquals(Colour.getIsFillOn(), Actual);

        Colour.setIsFillOn(false);
        assertNotEquals(Colour.getIsFillOn(), Actual);
    }

    @Test
    public void getAndSetFillColorTest(){
        Colour.setFillColor(Color.BLACK);
        assertEquals(Colour.getFillColor(),Color.BLACK);
    }

    @Test
    public void getAndSetPenColorTest(){
        Colour.setPenColor(Color.BLACK);
        assertEquals(Colour.getPenColor(), Color.BLACK);
    }

}
