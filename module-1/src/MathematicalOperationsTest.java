import static org.junit.Assert.*;
import org.junit.Test;
import MathematicalOperations.java;
public class MathematicalOperationsTest {

    @Test // Added by Onur Eken
    public void testDummyFunction() throws Exception {
        int a = 14; // oops typo mistake
        assertEquals("Var a must equal 13",13,a);
    }
    @Test // Added by Sýla Güler
    public void testAdditionFunction() throws Exception{
        assertEquals("Addition of 0 and 10 must equal 10",10,additionFunction(0, 10));
        assertEquals("Addition of 10 and 0 must equal 10",10,additionFunction(10, 0));
    }


}