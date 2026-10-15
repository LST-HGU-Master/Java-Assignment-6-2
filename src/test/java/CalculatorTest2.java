import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * logic.Calculator パッケージ版単体テストコード
 * (注意) logic パッケージ内に Calculator.java が作成され、
 *      time / divide メソッドが public static で宣言されている必要があります。
 *  @version (20261014) revised
 */
public class CalculatorTest2 {
    String pkgMthdName = "logicパッケージ内のCalculatorの";
    
    @Test
    public void testMultiply()
    {
        // assertion
        assertEquals(2,  logic.Calculator.multiply(1 , 2), pkgMthdName+"積の計算結果が間違っています!");
        assertEquals(36, logic.Calculator.multiply(4 , 9), pkgMthdName+"積の計算結果が間違っています!");
        assertEquals(-15, logic.Calculator.multiply(-3, 5), pkgMthdName + "積の計算結果が間違っています!（負の数の計算を確認してください）");
    }

    @Test
    public void testDivide()
    {
        assertEquals(0.5, logic.Calculator.divide(1, 2), 0.0001, 
            pkgMthdName + "商の計算結果が 0.5 になっていません!（int同士の除算 a / b で整数切捨てになっていませんか？）"
        );
        assertEquals(5.0, logic.Calculator.divide(15, 3), 0.0001, pkgMthdName + "商の計算結果が間違っています!");
        assertEquals(-3.5, logic.Calculator.divide(-7, 2), 0.0001, pkgMthdName + "商の計算結果が間違っています!");
    }

    @Test
    public void testDivideZero()
    {
        assertEquals(0.0, logic.Calculator.divide(1, 0), 0.0001, pkgMthdName + "商の計算結果が指示と異なります!（b が 0 の場合は 0 を返してください）");
        assertEquals(0.0, logic.Calculator.divide(26, 0), 0.0001, pkgMthdName + "商の計算結果が指示と異なります!（b が 0 の場合は 0 を返してください）");
    }
}
