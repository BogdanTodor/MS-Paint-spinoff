import javax.swing.*;

public class Reader {

    public static void main(String[] args) throws Exception {
        FileReaderClass.open("/Users/jakesykes/CAB302/Vector_Design_Tool/inputTests/colorTest.VEC");
        SwingUtilities.invokeLater(new GUI());
    }
}