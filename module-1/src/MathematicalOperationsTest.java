import static org.junit.Assert.*;
import org.junit.Test;

public class MathematicalOperationsTest {

    @Test
    public void testDummyFunction() throws Exception {
        int a = 14; // oops typo mistake
        assertEquals("Var a must equal 13",13,a);
    }
    @Test // Added by S�la G�ler
    public void testAdditionFunction() throws Exception{
        assertEquals("Addition of 0 and 10 must equal 10",10,MathematicalOperations.additionFunction(0, 10));
        assertEquals("Addition of 10 and 0 must equal 10",10,MathematicalOperations.additionFunction(10, 0));
    }

    @Test // Added by Oyku Yilmaz
    public void testMultiply() throws Exception{
        assertEquals("Multiplication of 0 and 5 must equal 0",0,MathematicalOperations.additionFunction(0, 5));
        assertEquals("Multiplication of 5 and 10 must equal 50",50,MathematicalOperations.additionFunction(5, 10));
    }
    
    @Test
    public void testSimpleDivide(){ // Added by oeken
        assertEquals("Division of 14 by 2 must equal 7",7,MathematicalOperations.divide(14,2) , 0.01);
    }

    @Test
    public void testEdgeDivide(){ // Added by oeken

        boolean exceptionThrown = false;
        try {
            MathematicalOperations.divide(14,0);
        }catch (Exception e){
            exceptionThrown = true;
        }
        assertTrue("Divison by zero must throw an exception", exceptionThrown);
    }


}