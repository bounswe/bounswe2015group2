/**
 * Created by onureken on 02/05/15.
 */
import java.util.Scanner;
public class MathematicalOperations {
    public static int dummyFunction(){
         return 1;
     }

    public static int additionFunction(){ // Added by Sýla Güler
        Scanner input = new Scanner(System.in);
        int a,b ;
        System.out.println("Enter two numbers to add: ");
        a = input.nextInt();
        b = input.nextInt();
        return a+b;
    }
    public static void main(String[] args){
        Scanner input1 = new Scanner(System.in);
        System.out.println("Which operation you would like to try: \n" +
                "For Dummy Function, Press 1:" +
                "For Addition Function, Press2:");
        int functionChoice = input.nextInt();
        if(functionChoice==1)
            dummyFunction();
        else if (functionChoice==2)
            additionFunction();
    }
}
