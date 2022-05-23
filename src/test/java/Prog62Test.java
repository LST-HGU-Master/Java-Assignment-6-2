import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220522)
 *   suporting both println and print("\n") on Windows
 * 
 * (注意) unit6フォルダ―の中にlogicフォルダが作られて、そこにCalculatorクラスのファイル が
 * 　　　　保存されない限り、このテストクラスは testPackageLogic() にエラーが表示される
 **/
public class Prog62Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        
        in = new StandardInputStream();
        System.setIn(in);
    }
    
    @AfterEach
    void after() {
       System.setOut(originalOut);
       System.setIn(originalIn);
    }

    @Test
    public void testMain()
    {
        // action
        // in.inputln("2"); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog62.main(new String[]{"72", "66"});

        // assertion
        String prints[] = bos.toString().split("\r\n|\n");
        try {
            assertEquals("4752", prints[1],"1つ目の計算結果が期待した結果と一致しません!");
            assertEquals(1.0909, Double.parseDouble(prints[3]), 0.0001f,
                        "２つ目の計算結果が期待した結果と一致しません!"
            );
         } catch (AssertionError err) {
            after();
            throw err;     
        } catch (ArrayIndexOutOfBoundsException aIOBExcpt) {
            after();
            throw new AssertionError("実行結果の行数が実行例と異なるかもしれません!");
        }
 
    }

    @Test
    public void testPrintedMessage()
    {
        // action
        // in.inputln("2"); // 標準入力をテストする場合
        // Hello.main(new String[]{"1", "2", "3"}); // 実行時引数をテストする場合
        Prog62.main(new String[]{"7", "6"});

        // assertion
        String prints[] = bos.toString().split("\r\n|\n");
        String msg;
        try {
            msg = "積を計算します";
            assertEquals(msg, prints[0],
                        "実行結果の1行目のprint文の出力が「" + msg +"」と違います!"
            );
            msg  ="商を計算します";        
            assertEquals(msg, prints[2],
                        "実行結果の3行目のprint文の出力が「" + msg +"」と違います!"
            );
        } catch (AssertionError err) {
            after();
            throw err;     
        } catch (ArrayIndexOutOfBoundsException aIOBExcpt) {
            after();
            throw new AssertionError("実行結果の行数が実行例と異なるかもしれません!");
        }
        
    }

    @Test
    public void testDef()
    {
        // default
    }
}
