import java.lang.System;
import java.util.Scanner;

public class MathematicalOperations {

    public static int dummyFunction() {
        return 1;
    }

    /**
     * Addition of two integer and returns the result
     *
     * @param a integer1
     * @param b integer2
     * @return The result of addition
     * @author Sila Guler
     */
    public static int additionFunction(int a, int b) {
        return a + b;
    }

    /**
     * Compares two integers and returns true if they are NOT equal.
     *
     * @param a integer1
     * @param b integer2
     * @return result
     * @author Serhat ILBEY
     */
    public static boolean isNotEqual(int a, int b) {
        boolean result = (a != b);
        return result;
    }

    /**
     * Divides to integer an returns the result in double
     *
     * @param a integer1
     * @param b integer2
     * @return The result of division
     * @author Mustafa Onur Eken
     */
    public static double divide(int a, int b) {
        return a / b;
    }

    /**
     * Multiply two integer and returns the result
     *
     * @param a integer1
     * @param b integer2
     * @return The result of multipication
     * @author Onur Neşvat
     */
    public static int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Returns the remainder of the division of dividend to divisor
     *
     * @param dividend integer dividend
     * @param divisor  integer divisor
     * @return The remainder from the division of a by b
     * @author Mustafa Taha Koçyiğit
     */
    public static int remainder(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Dividing by 0");
        }
        return dividend % divisor;
    }

    /**
     * Returns the pow power of the base
     *
     * @param base  integer1
     * @param power integer2
     * @return result
     * @author Oyku Yilmaz
     */
    public static int power(int base, int power) {
        if (base == 0 && power == 0) {
            throw new ArithmeticException("0 power of 0 is undefined");
        }

        if (power == 0) return 1;
        return base * power(base, --power);
    }

    /**
     * Returns difference two numbers.
     *
     * @param a
     * @param b
     * @return a-b
     */
    public static int minus(int a, int b) {
        return a - b;
    }

    /**
     * Takes the absolute value of an integer and returns the result
     *
     * @param a integer1
     * @return result
     * @author Mert Çotuk
     */
    public static int absolute(int a) {
        if (a < 0) a = (a * -1);
        return a;
    }

    /**
     * Takes 2 int to find inverse divide
     *
     * @param a integer1
     * @param b integer2
     * @return result
     * @author Ugur Tombul
     */
    public static double inverseDivide(int a, int b) {
        if (a == 0) {
            throw new ArithmeticException("divided by 0");
        }

        return b * 1.0 / a;
    }


    /**
     * Takes 1 integer to find its square root value
     *
     * @param n number
     * @return result
     * @author Murat Sinan Aclan
     */
    public static double sqrt(double n) {

        double c = n;
        double epsilon = 1e-15;    // relative error tolerance
        double t = c;              // estimate of the square root of c

        // repeatedly apply Newton update step until desired precision is achieved
        while (Math.abs(t - c / t) > epsilon * t) {
            t = (c / t + t) / 2.0;
        }

        // print out the estimate of the square root of c
        return t;

    }

    /**
     * Check two integer and returns a boolean value
     *
     * @param a integer1
     * @param b integer2
     * @return boolean result
     * @author Mehmet Eren Sogut
     */
    public static boolean lessthan(int a, int b) {
        if (a >= b) return false;
        else return true;
    }

    /**
     * Takes a booelan value and returns the negation of it
     *
     * @param value value to negate
     * @return result
     */
    public static boolean negation(boolean value) {
        return !value;
    }

    /**
     * Executes the all the methods via simple CLI interface
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which operation you would like to try: \n" +
                "For Dummy Function, Press 1:\n" +
                "For Addition Function, Press 2:\n" +
                "For Divide Function, Press 3:\n" +
                "For Multiply Function Press 4:\n" +
                "For Power Fuction, Press 5:\n" +
                "For Remainder Function Press 6:\n" +
                "For Minus Function Press 7:\n" +
                "For absolute value Function Press 8\n" +
                "For isNotEqual Function Press 9\n" +
                "For lessthan Function Press 10\n" +
                "For sqrt Function Press 11\n" +
                "For inverse divide Press 12\n" +
                "For negation Press 13\n" +
                "For Exit Press 0:");
        int functionChoice = 0;
        while (true) {
            System.out.print("Command: ");
            functionChoice = input.nextInt();

            if (functionChoice == 0) {
                System.out.println("good bye!");
                break;
            }

            if (functionChoice == 1)
                dummyFunction();
            else if (functionChoice == 2) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter two numbers to add: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                additionFunction(a, b);
            } else if (functionChoice == 3) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter two numbers to divide: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                System.out.println(divide(a, b));
            } else if (functionChoice == 4) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter two numbers to multiply: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                System.out.println(multiply(a, b));
            } else if (functionChoice == 5) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter two numbers as base and power: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                System.out.println(power(a, b));
            } else if (functionChoice == 6) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter two numbers first dividend then divisor to get the remainder: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                System.out.println(remainder(a, b));
            } else if (functionChoice == 7) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter two numbers to subtract: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                System.out.println(minus(a, b));
            } else if (functionChoice == 8) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter a number to take its absolute value: ");
                int a = input2.nextInt();
                System.out.println(absolute(a));
            } else if (functionChoice == 9) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter two numbers to test if they are NOT equal: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                boolean result = isNotEqual(a, b);
                if (result) System.out.println("Not equal");
                else System.out.println("Equal");
            } else if (functionChoice == 10) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter two numbers to check less or not: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                System.out.println(lessthan(a, b));
            } else if (functionChoice == 11) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter a number to find its square root value: ");
                int n = input2.nextInt();
                System.out.println(sqrt(n));
            } else if (functionChoice == 12) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter 2 integer to find inverse divide");
                int a = input2.nextInt();
                int b = input2.nextInt();
                System.out.println(inverseDivide(a, b));
            } else if (functionChoice == 13) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter a boolean value to negate");
                boolean value = input2.nextBoolean();
                System.out.println(negation(value));
            }
        }
    }


}
