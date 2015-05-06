import static org.junit.Assert.*;

import org.junit.Test;

public class MathematicalOperationsTest {
    /**
     * Testing of Negation Function 1
     *
     * @result Negation of false should be true
     * Added by Murat Cenk Batman
     */
    @Test
    public void testNegateFunction1() throws Exception {
        assertEquals("negation of false must be equal to true", true, MathematicalOperations.negation(false));
    }

    /**
     * Testing of Negation Function 2
     *
     * @result Negation of true should be false
     * Added by Murat Cenk Batman
     */
    @Test
    public void testNegateFunction2() throws Exception {
        assertEquals("negation of true must be equal to false", false, MathematicalOperations.negation(true));
    }

    /**
     * Testing of Addition Function
     *
     * @result Addition of 0 and 10 is 10
     * Added by Sila Guler
     */
    @Test
    public void testAdditionFunction1() throws Exception {
        assertEquals("Addition of 0 and 10 must equal 10", 10, MathematicalOperations.additionFunction(0, 10));
    }

    /**
     * Testing of Addition Function
     *
     * @result Addition of 10 and 0 is 10
     * Added by Sila Guler
     */
    @Test
    public void testAdditionFunction2() throws Exception {
        assertEquals("Addition of 10 and 0 must equal 10", 10, MathematicalOperations.additionFunction(10, 0));
    }


    /**
     * Testing of Power Function
     *
     * @result 0 power 0 is undefined, throws Exception
     * Added by Oyku Yilmaz
     */
    @Test
    public void testPowerFunction1() {
        boolean exceptionThrown = false;
        try {
            MathematicalOperations.power(0, 0);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue("0 power of 0 must throw an exception", exceptionThrown);
    }

    /**
     * Testing of Power Function
     *
     * @result 0 power of 2147483648 is 1
     * Added by Oyku Yilmaz
     */
    @Test
    public void testPowerFunction2() throws Exception {
        assertEquals("0 power of 2147483647 must equal to 1", 1, MathematicalOperations.power(2147483647, 0));
    }

    /**
     * Testing of Absolute Value Function
     *
     * @result Absolute value of the minimum integer
     * Added by Mert Cotuk
     */
    @Test
    public void testAbsolute1() throws Exception {
        assertEquals("Absolute value of -2147483648 must equal to 2", -2147483648, MathematicalOperations.absolute(-2147483648));
    }

    /**
     * Testing of Absolute Value Function
     *
     * @result Absolute value of zero
     * Added by Mert Cotuk
     */
    @Test
    public void testAbsolute2() throws Exception {
        assertEquals("Absolute value of 0 must equal to 0", 0, MathematicalOperations.absolute(0));
    }

    /**
     * Testing of isNotEqual Function
     *
     * @result true, -1 and 1 are not equal
     * Added by Serhat ILBEY
     */
    @Test
    public void testIsNotEqual1() throws Exception {
        assertEquals("-1 and 1 are not equal", true, MathematicalOperations.isNotEqual(-1, 1));
    }

    /**
     * Testing of isNotEqual Function
     *
     * @result true, -2147483648 and 2147483647 are not equal
     * Added by Serhat ILBEY
     */
    @Test
    public void testIsNotEqual2() throws Exception {
        assertEquals("-2147483648 and 2147483647 are not equal", true, MathematicalOperations.isNotEqual(2147483647, -2147483648));
    }

    /**
     * Testing of isNotEqual Function
     *
     * @result false, 0 and 0 are not equal
     * Added by Serhat ILBEY
     */
    @Test
    public void testIsNotEqual3() throws Exception {
        assertEquals("0 and 0 are equal", false, MathematicalOperations.isNotEqual(0, 0));
    }

    /**
     * Testing of divde function with simple params
     */
    @Test
    public void testSimpleDivide() { // Added by oeken
        assertEquals("Division of 14 by 2 must equal 7", 7, MathematicalOperations.divide(14, 2), 0.01);
    }

    /**
     * Testing of Minus Function
     *
     * @result of -2147483646 minus 1 is -2147483645
     * Added by Ozan Mahir Yıldırım
     */
    @Test
    public void testSimpleMinus1() {
        assertEquals("-214748364 minus 1 must be equal to 2147483645", -2147483645, MathematicalOperations.minus(-2147483646, -1));
    }

    /**
     * Testing of Minus Function
     *
     * @result of 0 minus -1 is 1
     * Added by Ozan Mahir Yıldırım
     */
    @Test
    public void testSimpleMinus2() {
        assertEquals("0 minus -1 must be equal to 1", 1, MathematicalOperations.minus(0, -1), 1);
    }

    /**
     * Testing edge divide : Division by zero
     * Added by oeken
     */
    @Test
    public void testEdgeDivide() {

        boolean exceptionThrown = false;
        try {
            MathematicalOperations.divide(14, 0);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue("Divison by zero must throw an exception", exceptionThrown);
    }

    /**
     * Testing of lessthan Function
     *
     * @result 5<2 should be false
     * Added by Eren Söğüt
     */
    @Test // add by erensogut
    public void testlessthan() throws Exception {
        assertEquals("5 < 2 must be equal to false", false, MathematicalOperations.lessthan(5, 2));
    }

    /**
     * Testing of lessthan Function
     *
     * @result 1<3 should be true
     * Added by Eren Söğüt
     */
    @Test // add by erensogut
    public void testlessthan1() throws Exception {
        assertEquals("1 < 3 must be equal to true", true, MathematicalOperations.lessthan(1, 3));
    }

    /**
     * Testing of sqrt Function
     *
     * @result square root of 4  should be 2
     * Added by Murat Sinan Aclan
     */
    @Test // Added by Murat Sinan Aclan
    public void testSqrt() throws Exception {
        assertEquals("Square root of 4 is", 2, MathematicalOperations.Sqrt(4), 2);

    }

    /**
     * Testing of sqrt Function
     *
     * @result square root of 4  should be 2
     * Added by Murat Sinan Aclan
     */
    @Test // Added by Murat Sinan Aclan
    public void testSqrt2() throws Exception {
        assertEquals("Square root value of 15 is", 3.872, MathematicalOperations.Sqrt(15), 3.872);
    }

    /**
     * Testing of InverseDivide Function
     *
     * @result -1 divide 1 should be -1
     * Added by Ugur Tombul
     */
    @Test
    public void testInverseDivide1() {
        assertEquals("-1 divide 1 must be -1", -1, MathematicalOperations.InverseDivide(-1, 1), -1);
    }


    /**
     * Testing of InverseDivide Function
     *
     * @result 4 divide -32323 should be -8080.75
     * Added by Ugur Tombul
     */

    @Test
    public void testInverseDivide2() {
        assertEquals("4 divide -32323 must be  -8080.75 ", -8080.75, MathematicalOperations.InverseDivide(4, -32323), -8080.75);
    }

    /**
     * Testing of Remainder Function
     *
     * @result The remainder from the division of 27 by 6
     * @author Mustafa Taha Koçyiğit
     */
    @Test
    public void testRemainder() {
        assertEquals("The remainder from the division of 27 by 6 is 3 ", 3, MathematicalOperations.remainder(27, 6));
    }

    /**
     * Test for theRemainder Function
     *
     * @result The remainder from the division of 39 by 5
     * @author Mustafa Taha Koçyiğit
     */
    @Test
    public void testRemainder2() {
        assertEquals("The remainder from the division of 39 by 5 is 4 ", 4, MathematicalOperations.remainder(39, 5));
    }


}