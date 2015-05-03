import static org.junit.Assert.*;
import org.junit.Test;

public class MathematicalOperationsTest {
    @test //Added by Murat Cenk Batman
    public void testNegateFunction()throws Exception{
        assertEquals("negation of false must be equal to true",true,negation(false));
        assertEquals("negation of true must be equal to false",false,negation(true));
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

    @Test // Added by Mert �otuk
    public void testAbsolute() throws Exception {
        assertEquals("Absolute value of -2 must equal to 2",2,MathematicalOperations.absolute(-2));
        assertEquals("Absolute value of 5 must equal to 5",5,MathematicalOperations.absolute(5));
    }

    @Test // Added by Serhat ?LBEY
        public void testIsNotEqual() throws Exception {
        assertEquals("-1 and 1 are not equal",true,MathematicalOperations.isNotEqual(-1, 1));
        assertEquals("-2147483648 and 2147483647 are not equal",true,MathematicalOperations.isNotEqual(2147483647,-2147483648 ));
        assertEquals("0 and 0 are equal",false,MathematicalOperations.isNotEqual(0, 0));
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

    @Test // add by erensogut
    public void testlessthan(){
        assertEquals("5 < 2 must be equal to false",false,MathematicalOperations.lessthan(5,2), false);
        assertEquals("1 < 3 must be equal to true",true,MathematicalOperations.minus(1,3), true);
    }
    @Test // Added by Murat Sinan Aclan
    public void testSqrt() throws Exception {
        assertEquals("Square root of 4 is", 2, MathematicalOperations.Sqrt(4),2);
        assertEquals("Square root value of 15 is", 3.872, MathematicalOperations.Sqrt(15),3.872);
    }
    @Test // add by ugurtombul
    public void testInverseDivide(){
        assertEquals("6 divide 2 must be 0.333",0.333,MathematicalOperations.lessthan(6,2), 0.333);
        assertEquals("6 divide 3 must be  0.5 ",0.5,MathematicalOperations.minus(6,3), 0.5);

}
}