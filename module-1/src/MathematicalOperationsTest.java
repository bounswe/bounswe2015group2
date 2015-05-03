import static org.junit.Assert.*;
import org.junit.Test;
public class MathematicalOperationsTest {

    @Test
    public void testDummyFunction() {
        int a = 14; // oops typo mistake
        assertEquals("Var a must equal 13",13,a);

    }

    @Test
    public void testSimpleDivide(){
        assertEquals("Division of 14 by 2 must equal 7",7,MathematicalOperations.divide(14,2) , 0.01);
    }

    @Test
    public void testEdgeDivide(){

        boolean exceptionThrown = false;
        try {
            MathematicalOperations.divide(14,0);
        }catch (Exception e){
            exceptionThrown = true;
        }
        assertTrue("Divison by zero must throw an exception",exceptionThrown);
    }


}