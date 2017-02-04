
package model;

import exception.InvalidPrimaryKeyException;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author JJ
 */
public class BookCollection extends EntityBase {

    private String updateStatusMessage = "";
    private Vector<Book> bookList;
    private static final String myTableName = "Book";

    public BookCollection() {
        super(myTableName);
    }

    public void findDateOlderThan(String year) throws Exception {
        try {
            //need to set to connect to database??
            Statement stmt = connection to database
            String query = "SELECT * FROM " + myTableName + " WHERE (date published < '" + year + "')";
            QuerySet qs = stmt.executeQuery(query);
            while (qs.next())
             {
                int bookID = qs.getInt("Book ID: ");
                String title = qs.getString("Title: ");
                String author = qs.getString("Author: ");
                int pubYear = qs.getInt("Published year: ");
                System.out.format("%s, %s, %s, %s, %s, %s\n", bookID, title, author, pubYear);
            }
        }
        catch (Exception e)
        {
        System.err.println(e.getMessage());
        }//end try 
    }


    public void findDateYoungerThan(String year) throws Exception {
        try {
            //need to set to connect to database??
            Statement stmt = connection to database
            String query = "SELECT * FROM " + myTableName + " WHERE (date published > '" + year + "')";
            QuerySet qs = stmt.executeQuery(query);
            while (qs.next())
             {
                int bookID = qs.getInt("Book ID: ");
                String title = qs.getString("Title: ");
                String author = qs.getString("Author: ");
                int pubYear = qs.getInt("Published year: ");
                System.out.format("%s, %s, %s, %s, %s, %s\n", bookID, title, author, pubYear);
            }
        }
        catch (Exception e)
        {
        System.err.println(e.getMessage());
        }//end try 
    }

    public void findTitlewithNameLike(String name) throws Exception {
        try {
            //need to set to connect to database??
            Statement stmt = connection to database  
            String query = "SELECT * FROM " + myTableName + " WHERE (title name is like '" + name + "')";
            QuerySet qs = stmt.executeQuery(query);
            while (qs.next())
             {
                int bookID = qs.getInt("Book ID: ");
                String title = qs.getString("Title: ");
                String author = qs.getString("Author: ");
                int pubYear = qs.getInt("Published year: ");
                System.out.format("%s, %s, %s, %s, %s, %s\n", bookID, title, author, pubYear);
            }
        }
        catch (Exception e)
        {
        System.err.println(e.getMessage());
        }//end try 
    }

    public void findAuthorwithNameLike(String name) throws Exception {
        try {
            //need to set to connect to database??
            Statement stmt = connection to database
            String query = "SELECT * FROM " + myTableName + " WHERE (author name is like'" + name + "')";
            QuerySet qs = stmt.executeQuery(query);
            while (qs.next())
             {
                int bookID = qs.getInt("Book ID: ");
                String title = qs.getString("Title: ");
                String author = qs.getString("Author: ");
                int pubYear = qs.getInt("Published year: ");
                System.out.format("%s, %s, %s, %s, %s, %s\n", bookID, title, author, pubYear);
            }
        }
        catch (Exception e)
        {
        System.err.println(e.getMessage());
        }//end try 
    }




    
}