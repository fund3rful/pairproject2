
import exception.InvalidPrimaryKeyException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.GetUserInputConsole;
import model.Book;
import model.BookCollection;

public class BookTester{
    
    private static final int MAIN_MENU = 0;
    private static final int INSERT = 1;
    private static final int FIND_DATE_OLDER = 2;
    private static final int FIND_DATE_YOUNGER = 3;
    private static final int FIND_TITLE_LIKE = 4;
    private static final int FIND_AUTHOR_LIKE = 5;
    
    
      public static void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int userSelection= 0;
        boolean selectionLoop = false;
        boolean printMenu = true;
        while (!selectionLoop) {
            try {
                if (printMenu) {
                    /*print menu can be set after long commands*/
                    System.out.println("\nBook Menu \n0 to go back to main menu "
                            + "\n1 to insert a new book "
                            + "\n2 to find books older than a given date "
                            + "\n3 to find books younger than a given date "
                            + "\n4 to find books with a title like: "
                            + "\n5 to find books with an author name like: ");
                    printMenu = false;
                }
                System.out.print("Please make a selection: ");
                userSelection = Integer.parseInt(br.readLine());
                switch (userSelection) {
                    case MAIN_MENU:
                        selectionLoop = true;
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
                    case FIND_TITLE_LIKE:
                        findTitle();
                        printMenu = true;
                        break;
                    case FIND_AUTHOR_LIKE:
                        findAuthor();
                        printMenu = true;
                        break;
                    default:
                        System.out.println("Pleae make a valid selection.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("/nIN CASE CATCH\n Input not valid, please select again."+e);
            }
        }
        System.out.println("\nReturning to Main Menu\n\n");
    }//end selectionLoop

    
    private static void insertCommand() throws IOException {
        Properties prop = new Properties();
        /*get all the information via command line and populate a properties object*/
        System.out.print("Please enter author name (30 char max, 1 char min): ");
        prop.setProperty("author", GetUserInputConsole.get(1, 30, GetUserInputConsole.STRING));
        System.out.print("Please enter book title (50 char max): ");
        prop.setProperty("title", GetUserInputConsole.get(0, 50, GetUserInputConsole.STRING));
        System.out.print("Please enter publication year (4 char max in format yyyy): ");
        prop.setProperty("pubYear", GetUserInputConsole.get(4,4, GetUserInputConsole.NUMBER));
  //getting stuck here on pub year
        
        /*use the information to create a new book*/
        Book b = new Book(prop);
        b.update();
        System.out.println("\nInsert Successful Returning to Book Menu \n");
        
    }

    private static void findDateOlder() {
        BookCollection b = new BookCollection();
        System.out.print("Find books older than year (format YYYY): ");
        try {
            b.findBooksOlderThan(GetUserInputConsole.get(10, 10, GetUserInputConsole.STRING));
            printBookCollectionToConsole(b);
        }catch (InvalidPrimaryKeyException ex) {
            System.out.println(ex);
        }
        
    }
    private static void findDateYounger() {
        BookCollection b = new BookCollection();
        System.out.print("Find books yonger than year (format YYYY): ");
         try {
            b.findBooksYoungerThan(GetUserInputConsole.get(10, 10, GetUserInputConsole.STRING));
            printBookCollectionToConsole(b);
        } catch (InvalidPrimaryKeyException ex) {
            System.out.println(ex);
        }
        
    }

    private static void findTitle() {
        System.out.println("in title");
        BookCollection b = new BookCollection();
       try {
            b.findBooksWithTitleLike(GetUserInputConsole.get(1, 30, GetUserInputConsole.STRING));
            printBookCollectionToConsole(b);
        } catch (InvalidPrimaryKeyException ex) {
            System.out.println(ex);
        }
        
    }
    
     private static void findAuthor() {
        BookCollection b = new BookCollection();
        System.out.print("Find author with name like: ");
        try {
            b.findBooksWithAuthorLike(GetUserInputConsole.get(1, 30, GetUserInputConsole.STRING));
            printBookCollectionToConsole(b);
        } catch (InvalidPrimaryKeyException ex) {
            System.out.println(ex);
        }
        } 
        
           private static void printBookCollectionToConsole(BookCollection b) {
        Vector<Book> bookList = (Vector<Book>) b.getState("Books");
        String leftAlignFormat = "| %-30s | %-50s | %-20s";
        System.out.println("");
        System.out.format("+--------------------------------+----------------------------------------------------+----------------------+----+---%n");
        System.out.format("| Title                           | Author                                            | Publish Year                 | %n");
        System.out.format("+--------------------------------+----------------------------------------------------+----------------------+----+---%n");
        for (Book book : bookList) {
            System.out.format(leftAlignFormat, book.getState("Title"), book.getState("Author"), book.getState("Publish Year"));
        System.out.format("+--------------------------------+----------------------------------------------------+----------------------+----+-----%n");
        System.out.println("Press any key to continue...");
         try {
            System.in.read();
            } catch (Exception e) {
            }
         }
        }
           
}// end class