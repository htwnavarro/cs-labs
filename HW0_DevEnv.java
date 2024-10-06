// This is a Java program will print, "Hello World" 5 times and prompt the user for input.
// Aside from receiving input "yes" (case-insensitive) the program will exit.

import java.util.*;

public class HW0_DevEnv {

    public static void main(String[] args) {
        boolean inProgress = true;

        while (inProgress) {
            // do the output loop
            doPrintHelloWorldLoop();

            // prompt for input
            String response = getUserInput();

            // decide to continue based on input
            inProgress = isResponseYes(response);
        }
    }

    public static void doPrintHelloWorldLoop() {
        // loop 1 through 5
        for (int i = 1; i <= 5; i++) {
            System.out.println("Hello World!");
        }
    }

    public static String getUserInput() {
        Scanner termScanner = new Scanner(System.in);

        System.out.println("Would you like to see Hello again? (Type \"yes\" to continue)");

        String response = termScanner.nextLine();

        return response;
    }

    public static boolean isResponseYes(String userResponse) {
        String yes = "yes";

        // compare to returns 0 for exact match ignoring case
        return yes.compareToIgnoreCase(userResponse) == 0;
    }
}

/*
Hello World!
Hello World!
Hello World!
Hello World!
Hello World!
Would you like to see Hello again? (Type "yes" to continue)
yes
Hello World!
Hello World!
Hello World!
Hello World!
Hello World!
Would you like to see Hello again? (Type "yes" to continue)
yes
Hello World!
Hello World!
Hello World!
Hello World!
Hello World!
Would you like to see Hello again? (Type "yes" to continue)
bye
 */
