import static org.junit.Assert.*;
import org.junit.Test;

public class MathematicalOperationsTest {
    /** Testing of Negation Function 1
        * @ result Negation of false should be true
        * Added by Murat Cenk Batman
         */
    @test
    public void testNegateFunction1()throws Exception{
        assertEquals("negation of false must be equal to true",true,negation(false));
    }
    /** Testing of Negation Function 2
     * @ result Negation of true should be false
     * Added by Murat Cenk Batman
      */
    @test
    public void testNegateFunction2()throws Exception{
        assertEquals("negation of true must be equal to false",false,negation(true));
    }
    /** Testing of Addition Function
     * @ result Addition of 2147483647 and 1 is -2147483648
     * Added by Sila Guler
     */
    @Test
    public void testAdditionFunction1() throws Exception{
        assertEquals("Addition of 2147483647 and 1 must equal -2147483648",-2147483648,MathematicalOperations.additionFunction(2147483647, 1));
    }
    /** Testing of Addition Function
     * @ result Addition of -2147483648 and -1 is 2147483647
     * Added by Sila Guler
     */
    @Test
    public void testAdditionFunction2() throws Exception{
        assertEquals("Addition of -2147483648 and -1 must equal 2147483647",2147483647,MathematicalOperations.additionFunction(-2147483648, -1));
    }


    @Test // Added by Oyku Yilmaz
    public void testSimplePower() throws Exception{
        assertEquals("0 power of 5 must equal to 1",1,MathematicalOperations.power(5, 0));
        assertEquals("2 power of 5 must equal to 25",25,MathematicalOperations.power(5, 2));
    }

    /** Testing of Absolute Value Function
     * @ result Absolute value of the minimum integer
     * Added by Mert �otuk
     */

    @Test
    public void testAbsolute1() throws Exception {
        assertEquals("Absolute value of -2147483648 must equal to 2",-2147483648,MathematicalOperations.absolute(-2147483648));
    }

    /** Testing of Absolute Value Function
     * @ result Absolute value of zero
     * Added by Mert �otuk
     */

    @Test
    public void testAbsolute2() throws Exception {
        assertEquals("Absolute value of 0 must equal to 0",0,MathematicalOperations.absolute(0));
    }

    /** Testing of isNotEqual Function
     * @ result true, -1 and 1 are not equal
     * Added by Serhat ILBEY
     */
    @test
    public void testIsNotEqual1() throws Exception {
        assertEquals("-1 and 1 are not equal",true,MathematicalOperations.isNotEqual(-1, 1));
    }

    /** Testing of isNotEqual Function
     * @ result true, -2147483648 and 2147483647 are not equal
     * Added by Serhat ILBEY
     */
    @test
    public void testIsNotEqual2() throws Exception {
        assertEquals("-2147483648 and 2147483647 are not equal",true,MathematicalOperations.isNotEqual(2147483647, -2147483648));
    }

    /** Testing of isNotEqual Function
     * @ result false, 0 and 0 are not equal
     * Added by Serhat ILBEY
     */
    @test
    public void testIsNotEqual3() throws Exception {
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
    /** Testing of lessthan Function
     * @ result 5<2 should be false
     * Added by Eren Söğüt
     */

    @Test // add by erensogut
    public void testlessthan() throws Exception {
        assertEquals("5 < 2 must be equal to false",false,MathematicalOperations.lessthan(5, 2), false);
    }
    /** Testing of lessthan Function
     * @ result 1<3 should be true
     * Added by Eren Söğüt
     */
    @Test // add by erensogut
    public void testlessthan1() throws Exception {
        assertEquals("1 < 3 must be equal to true",true,MathematicalOperations.minus(1, 3), true);
    }
    /** Testing of sqrt Function
     * @ result square root of 4  should be 2
     * Added by Murat Sinan Aclan
     */
    @Test // Added by Murat Sinan Aclan
    public void testSqrt() throws Exception {
        assertEquals("Square root of 4 is", 2, MathematicalOperations.Sqrt(4),2);

    }
    /** Testing of sqrt Function
     * @ result square root of 4  should be 2
     * Added by Murat Sinan Aclan
     */
    @Test // Added by Murat Sinan Aclan
    public void testSqrt2() throws Exception {
        assertEquals("Square root value of 15 is", 3.872, MathematicalOperations.Sqrt(15),3.872);
    }
    @Test // add by ugurtombul
    public void testInverseDivide(){
        assertEquals("6 divide 2 must be 0.333",0.333,MathematicalOperations.lessthan(6,2), 0.333);
        assertEquals("6 divide 3 must be  0.5 ", 0.5, MathematicalOperations.minus(6, 3), 0.5);

}
}