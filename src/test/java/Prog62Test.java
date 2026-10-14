import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
/**
 * @version (20220522) supporting both println and print("\n") on Windows
 * @version (20261014) revised for partial scoring, index safety, and zero divisor handling
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
    public void testMessageAndStructure() {
        Prog62.main(new String[]{"7", "6"});

        String output = bos.toString().replace("\r\n", "\n");
        String[] lines = output.split("\n");

        assertTrue(lines.length >= 4, 
            "実行結果が4行分確認できません! メッセージ出力の直後に改行（println）を入れているか確認してください。"
        );

        assertTrue(output.contains("積を計算します"), "「積を計算します」というメッセージが正しく出力されていません。");
        assertTrue(output.contains("商を計算します"), "「商を計算します」というメッセージが正しく出力されていません。");
    }

    @Test
    public void testMainCalculations() {
        // --- パターン1: 通常計算 ("72", "66") ---
        Prog62.main(new String[]{"72", "66"});

        String[] lines1 = bos.toString().replace("\r\n", "\n").split("\n");
        assertTrue(lines1.length >= 4, "実行結果の行数が不足しています。");
        assertTrue(lines1[1].contains("4752"), "引数 「72 66」 に対する積の計算結果（4752）が正しく出力されていません。");

        try {
            assertEquals(1.09091, Double.parseDouble(lines1[3].trim()), 0.001, 
                "引数 「72 66」 に対する商の計算結果（1.09091...）が正しく出力されていません。"
            );
        } catch (NumberFormatException e) {
            fail("4行目の計算結果を数値（double）として読み込めませんでした。");
        }

        // --- パターン2: 実行例 ("2", "7") ---
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        Prog62.main(new String[]{"2", "7"});

        String[] lines2 = bos.toString().replace("\r\n", "\n").split("\n");
        assertTrue(lines2.length >= 4, "実行結果の行数が不足しています。");
        assertTrue(lines2[1].contains("14"), "実行例の引数 「2 7」 に対する積の計算結果（14）が正しく出力されていません。");

        try {
            assertEquals(0.285714, Double.parseDouble(lines2[3].trim()), 0.001, 
                "実行例の引数 「2 7」 に対する商の計算結果（0.285714...）が正しく出力されていません。"
            );
        } catch (NumberFormatException e) {
            fail("4行目の計算結果を数値（double）として読み込めませんでした。");
        }

        // --- パターン3: ゼロ除算 ("10", "0") ---
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        Prog62.main(new String[]{"10", "0"});

        String[] lines3 = bos.toString().replace("\r\n", "\n").split("\n");
        assertTrue(lines3.length >= 4, "実行結果の行数が不足しています。");
        assertTrue(lines3[1].contains("0"), "10 × 0 の結果（0）が出力されていません。");

        try {
            assertEquals(0.0, Double.parseDouble(lines3[3].trim()), 0.0001, 
                "第2引数が 0 の場合、商の出力結果は 0 (0.0) になる必要があります。"
            );
        } catch (NumberFormatException e) {
            fail("4行目の計算結果を数値（double）として読み込めませんでした。");
        }
    }
}
