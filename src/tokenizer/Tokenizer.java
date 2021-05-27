package tokenizer;

/**
 *
 * @author faraj
 *
 * final int[] S = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
 * 18, 19, 20, 21, 22, 23}; 
 * final String[] A = {"=", "+", "-", "*", "/", "i", "n", "t", "f", "l", "o", "a", "t", "[0-9]", "[azA-Z]"}; 
 * final int i = 1;
 * final String spChar = "[\"[$&+,:;=?@#|'<>.-^*()%!]\"]"; 
 * final int[] T = {7, 8, 9, 10, 11, 21, 17, 2, 5, 23};
 *
 */
import java.util.*;

public class Tokenizer {

    public static void main(String[] args) {
        Scanner IN = new Scanner(System.in);

        System.out.println("enter your code here...");
        String code = IN.nextLine().trim();

        if (code.length() <= 0) {
            System.err.println("\n please enter your code to pass it into lexical analyser... \n ");
            return; // input should never be empty
        }
        
        System.out.println(" \n your entered code => \" " + code + " \" \n");

        // create a new object from Input class and pass the input string that user enter...
        Input input = new Input(code);

        // execute the app ...
        input.execute();
    }

}
