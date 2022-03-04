import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.*;

public class Prog62Test {

    @Test
    public void testMain()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        // in.inputln("2"); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog62.main(new String[]{"72", "66"});

        // assertion
        String prints[] = bos.toString().split("\n");
        assertEquals("4752", prints[1]);
        assertEquals(1.0909, Double.parseDouble(prints[3]), 0.0001f);

        // undo the binding in System
        System.setOut(originalOut);
    }
}
