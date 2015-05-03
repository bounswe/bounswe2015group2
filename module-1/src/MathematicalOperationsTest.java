import static org.junit.Assert.*;
import org.junit.Test;

public class MathematicalOperationsTest {

    @Test
    public void testDummyFunction() throws Exception {
        int a = 14; // oops typo mistake
        assertEquals("Var a must equal 13",13,a);
    }
    @Test // Added by Sila Guler
    public void testAdditionFunction() throws Exception{
        assertEquals("Addition of 0 and 10 must equal 10",10,MathematicalOperations.additionFunction(0, 10));
        assertEquals("Addition of 10 and 0 must equal 10",10,MathematicalOperations.additionFunction(10, 0));
    }

    @Test // Added by Oyku Yilmaz
    public void testSimplePower() throws Exception{
        assertEquals("0 power of 5 must equal to 1",1,MathematicalOperations.power(5, 0));
        assertEquals("2 power of 5 must equal to 25",25,MathematicalOperations.power(5, 2));
    }

    @Test // Added by Mert Çotuk
    public void testAbsolute() throws Exception {
        assertEquals("Absolute value of -2 must equal to 2",2,MathematicalOperations.absolute(-2));
        assertEquals("Absolute value of 5 must equal to 5",5,MathematicalOperations.absolute(5));
    }

    @Test
    public void testSimpleDivide(){ // Added by oeken
        assertEquals("Division of 14 by 2 must equal 7",7,MathematicalOperations.divide(14, 2) , 0.01);
    }
    @Test
    public void testSimpleMinus(){
        assertEquals("4 minus 2 must be equal to 2",2,MathematicalOperations.minus(4,2), 2);
        assertEquals("6 minus 3 must be equal to 3",2,MathematicalOperations.minus(6,3), 3);
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
    @Test  //Added by Ugur Tombul
    public void testSimplePlus() {
        assertEquals("2 + 3 must be equal 5",5,MathematicalOperations.plus(2,3));
    }


}