import java.lang.System;
import java.util.Scanner;

public class MathematicalOperations {

    public static int dummyFunction() {
        return 1;
    }

    /**
     * Addition of two integer and returns the result
     * Added by Sila Guler
     *
     * @param a integer1
     * @param b integer 2
     * @return a+b
     */
    public static int additionFunction(int a, int b) {
        return a + b;
    }

    public static double divide(int a, int b) {
        return a / b;
    }

    /**
     * Multiply two integer and returns the result
     * Added by Onur Neşvat
     *
     * @param a integer1
     * @param b integer 2
     * @return result
     */
    public static int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Returns the remainder of the division of a to b
     * Added by Mustafa Taha Koçyiğit
     *
     * @param a integer1
     * @param b integer 2
     * @return result
     */
    public static int remainder(int a, int b) {
        return a % b;
    }

    //Added by Oyku Yilmaz
    public static int power(int base, int power) {
        if(power == 0) return 1;
        return base * power(base, --power);
    }
    public static int minus(int a, int b) { return a-b;}

    /**
     * @param
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which operation you would like to try: \n" +
                "For Dummy Function, Press 1:" +
                "For Addition Function, Press 2:" +
                "For Divide Function, Press 3:" +
                "For Multiply Function Press 4:" +
                "For Power Fuction, Press 5:" +
                "For Remainder Function Press 6" +
                "For Minus Function Press 7" +
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
                System.out.println("Enter two numbers as dividend and divisor: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                System.out.println(remainder(a, b));
            } else if (functionChoice == 7) {
                Scanner input2 = new Scanner(System.in);
                System.out.println("Enter two numbers to subtract: ");
                int a = input2.nextInt();
                int b = input2.nextInt();
                System.out.println(minus(a, b));
            }
        }
    }


}
