
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Test the functionality of patron and book console commands.
 */
public class main {

    private static final int EXIT = 0;
    private static final int PATRON = 1;
    private static final int BOOK = 2;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        int input;
        boolean printMenu = true;
        while (!exit) {
            try {
                if (printMenu) {
                    /*print menu can be set after long commands*/
                    System.out.println("Main Menu \n0 to exit \n1 to enter the Patron menu \n2 to enter the Book menu");
                    printMenu = false;
                }
                System.out.print("Enter Command: ");
                input = Integer.parseInt(br.readLine());
                switch (input) {
                    case EXIT:
                        exit = true;
                        break;
                    case PATRON:
                        PatronTester.run();
                        printMenu = true;
                        break;
                    case BOOK:
                        System.out.println("Book Menu not implemented Yet");
                        printMenu = true;
                        break;
                    default:
                        System.out.println("Not Valid Input, try again");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Not Valid Input, try again");
            }
        }
        System.out.println("Exiting Program");
    }
}
