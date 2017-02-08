
import exception.InvalidPrimaryKeyException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.GetUserInputConsole;
import model.Patron;
import model.PatronCollection;

/**
 *
 * @author Neoaptt
 */
public class PatronTester {

    private static final int MAIN_MENU = 0;
    private static final int INSERT = 1;
    private static final int FIND_DATE_OLDER = 2;
    private static final int FIND_DATE_YOUNGER = 3;
    private static final int FIND_ZIP = 4;
    private static final int FIND_NAME = 5;

    /**
     * runs the main loop of the patron tester. Has several states it can run.
     */
    public static void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        boolean endLoop = false;
        boolean printMenu = true;
        while (!endLoop) {
            try {
                if (printMenu) {
                    /*print menu can be set after long commands*/
                    System.out.println("Patron Menu \n0 to go back to main menu \n1 to insert a new patron \n2 to find patrons that are older than a given date \n3 to find patrons that are younger than a given date \n4 to find patrons by zip \n5 to find patrons by name");
                    printMenu = false;
                }
                System.out.print("Enter Command: ");
                input = Integer.parseInt(br.readLine());
                switch (input) {
                    case MAIN_MENU:
                        /*only end the patron tester when the user wants to exit*/
                        endLoop = true;
                        break;
                    case INSERT:
                        insertCommand();
                        printMenu = true;
                        break;
                    case FIND_DATE_OLDER:
                        findDateOlder();
                        printMenu = true;
                        break;
                    case FIND_DATE_YOUNGER:
                        findDateYounger();
                        printMenu = true;
                        break;
                    case FIND_ZIP:
                        findZip();
                        printMenu = true;
                        break;
                    case FIND_NAME:
                        findName();
                        printMenu = true;
                        break;
                    default:
                        System.out.println("0x0004 Not Valid Input, try again");
                        break;
                }
            } catch (IOException e) {
                System.out.println("0x0003 Not Valid Input, try again");
            }
        }
        System.out.println("\nReturning to Main Menu\n\n");
    }

    /**
     * Insert a new patron into the database
     *
     * @return
     * @throws IOException
     */
    private static void insertCommand() throws IOException {

        Properties prop = new Properties();
        /*get all the information via command line and populate a properties object*/
        System.out.print("name (between 1-30 characters inclusive): ");
        prop.setProperty("name", GetUserInputConsole.get(1, 30, GetUserInputConsole.STRING));
        System.out.print("address (between 0-50 characters inclusive): ");
        prop.setProperty("address", GetUserInputConsole.get(0, 50, GetUserInputConsole.STRING));
        System.out.print("city (between 0-20 characters inclusive): ");
        prop.setProperty("city", GetUserInputConsole.get(0, 20, GetUserInputConsole.STRING));
        System.out.print("stateCode (between 0-2 characters inclusive): ");
        /*make all inputs uppercase by default*/
        prop.setProperty("stateCode", GetUserInputConsole.get(0, 2, GetUserInputConsole.STRING).toUpperCase());
        System.out.print("zip (between 0-5 numbers inclusive): ");
        prop.setProperty("zip", GetUserInputConsole.get(0, 5, GetUserInputConsole.NUMBER));
        System.out.print("email (format example@example.com): ");
        prop.setProperty("email", GetUserInputConsole.get(0, 30, GetUserInputConsole.EMAIL));
        System.out.print("date of birth (format YYYY-MM-DD including dashes): ");
        prop.setProperty("dateOfBirth", GetUserInputConsole.get(10, 10, GetUserInputConsole.DATE));
        /*use the information to create a new patron*/
        Patron p = new Patron(prop);
        /*since we didn't set the ID the update function will create a new patron*/
        p.update();
        System.out.println("\nInsert Successful Returning to Patron Menu \n");
    }

    private static void findDateOlder() {
        PatronCollection p = new PatronCollection();
        System.out.print("Find Patrons older than date (format YYYY-MM-DD including dashes): ");
        try {
            p.findPatronsOlderThan(GetUserInputConsole.get(10, 10, GetUserInputConsole.DATE));
            printPatronCollectionToConsole(p);
        } catch (InvalidPrimaryKeyException ex) {
            System.out.println(ex);
        }
    }

    private static void findDateYounger() {
        PatronCollection p = new PatronCollection();
        System.out.print("Find Patrons younger than date (format YYYY-MM-DD including dashes): ");
        try {
            p.findPatronsYoungerThan(GetUserInputConsole.get(10, 10, GetUserInputConsole.DATE));
            printPatronCollectionToConsole(p);
        } catch (InvalidPrimaryKeyException ex) {
            System.out.println(ex);
        }
    }

    private static void findZip() {
        PatronCollection p = new PatronCollection();
        System.out.print("Find Patrons at zip code (exactly 5 numbers): ");
        try {
            p.findPatronsAtZipCode(GetUserInputConsole.get(5, 5, GetUserInputConsole.NUMBER));
            printPatronCollectionToConsole(p);
        } catch (InvalidPrimaryKeyException ex) {
            System.out.println(ex);
        }
    }

    private static void findName() {
        PatronCollection p = new PatronCollection();
        System.out.print("Find Patrons by name (between 1-30 characters inclusive): ");
        try {
            p.findPatronsWithNameLike(GetUserInputConsole.get(1, 30, GetUserInputConsole.STRING));
            printPatronCollectionToConsole(p);
        } catch (InvalidPrimaryKeyException ex) {
            System.out.println(ex);
        }
    }

    private static void printPatronCollectionToConsole(PatronCollection p) {
        Vector<Patron> patronList = (Vector<Patron>) p.getState("patrons");
        String leftAlignFormat = "| %-30s | %-50s | %-20s | %-2s | %-5s | %-30s | %-10s |%n";
        System.out.println("");
        System.out.format("+--------------------------------+----------------------------------------------------+----------------------+----+-------+--------------------------------+------------+%n");
        System.out.format("| Name                           | Address                                            | City                 | SC | Zip   | Email                          | Birth Date |%n");
        System.out.format("+--------------------------------+----------------------------------------------------+----------------------+----+-------+--------------------------------+------------+%n");
        for (Patron person : patronList) {
            System.out.format(leftAlignFormat, person.getState("name"), person.getState("address"), person.getState("city"), person.getState("stateCode"), person.getState("zip"), person.getState("email"), person.getState("dateOfBirth"));
        }
        System.out.format("+--------------------------------+----------------------------------------------------+----------------------+----+-------+--------------------------------+------------+%n");
        System.out.println("Press any key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

}
