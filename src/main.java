
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Test the functionality of patron and book console commands.
 */
public class main {

    private static final int PATRON = 1;
    private static final int BOOK = 2;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter 1 for Patrion 2 for Book:");
        int input = 0;
        while (input != PATRON && input != BOOK) {
            try {
                input = Integer.parseInt(br.readLine());
            } catch (Exception e) {
                System.out.println("Invalid Number Try Again");
            }
            switch (input) {
                case PATRON:
                    PatronTester p = new PatronTester();
                    p.run();
                    break;
                case BOOK:
                    System.out.println("Input 2");
                    break;
                default:
                    System.out.println("Not 1 or 2 Try Again");
                    break;
            }
        }
        System.out.println("Finished");
    }
}
