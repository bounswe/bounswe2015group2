import java.lang.System;
import java.util.Scanner;

public class MathematicalOperations {

    public static int dummyFunction() {
        return 1;
    }

    public static int additionFunction(int a, int b) {
        return a + b;
    }

    public static double divide(int a, int b) {
        return a / b;
    }

    /**
     * Multiply two integer and returns the result
     * Added by Onur Neşvat
     * @param a integer1
     * @param b integer 2
     * @return result
     */
    public static double multiply(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which operation you would like to try: \n" +
                "For Dummy Function, Press 1:" +
                "For Addition Function, Press2:");
        int functionChoice = input.nextInt();
        if (functionChoice == 1)
            dummyFunction();
        else if (functionChoice == 2) {
            Scanner input2 = new Scanner(System.in);
            System.out.println("Enter two numbers to add: ");
            int a = input2.nextInt();
            int b = input2.nextInt();
            additionFunction(a, b);
        }
    }


}
