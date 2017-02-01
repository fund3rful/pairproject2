
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import model.Patron;
import model.PatronCollection;

/**
 *
 * @author Neoaptt
 */
public class PatronTester {

    private static final int INSERT = 1;
    private static final int FIND_DATE_OLDER = 2;
    private static final int FIND_DATE_YOUNGER = 3;
    private static final int FIND_ZIP = 4;
    private static final int FIND_NAME = 5;

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter \n1 to insert a new patron \n2 to find patrons that are older than a given date \n3 to find patrons that are younger than a given date \n4 to find patrons by zip \n5 to find patrons by name");
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = Integer.parseInt(br.readLine());
                switch (input) {
                    case INSERT:
                        insertCommand();
                        validInput = true;
                        break;
                    case FIND_DATE_OLDER:
                        System.out.println("Input 2");
                        validInput = true;
                        break;
                    case FIND_DATE_YOUNGER:
                        System.out.println("Input 3");
                        validInput = true;
                        break;
                    case FIND_ZIP:
                        System.out.println("Input 4");
                        validInput = true;
                        break;
                    case FIND_NAME:
                        System.out.println("Input 5");
                        validInput = true;
                        break;
                    default:
                        System.out.println("Not valid input try again");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid Number Try Again");
            }
        }
        System.out.println("Finished");
    }

    private void insertCommand() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Properties prop = new Properties();
        try {
            System.out.println("name:");
            prop.setProperty("name", br.readLine());
            System.out.println("address:");
            prop.setProperty("address", br.readLine());
            System.out.println("city:");
            prop.setProperty("city", br.readLine());
            System.out.println("stateCode:");
            prop.setProperty("stateCode", br.readLine());
            System.out.println("zip:");
            prop.setProperty("zip", br.readLine());
            System.out.println("email:");
            prop.setProperty("email", br.readLine());
            System.out.println("dateOfBirth:");
            prop.setProperty("dateOfBirth", br.readLine());
            Patron p = new Patron(prop);
            p.update();
        } catch (Exception e) {
        }

    }
}
