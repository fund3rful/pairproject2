
package model;

import exception.InvalidPrimaryKeyException;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author JJ
 */
public class BookCollection extends EntityBase {

    private Vector<Book> bookList;
    private static final String myTableName = "Book";

    public BookCollection() {
        super(myTableName);
    }

    public void findBooksOlderThan(String year) throws InvalidPrimaryKeyException {
        
        String sql = "SELECT * FROM " + myTableName + " WHERE (pubYear < '" + year + "')";
        executeQuery(sql);
    }


    public void findBooksYoungerThan(String year) throws InvalidPrimaryKeyException {
            executeQuery("SELECT * FROM " + myTableName + " WHERE (pubYear > '" + year + "')");      
    }

    public void findBooksWithTitleLike(String name) throws InvalidPrimaryKeyException {
            executeQuery("SELECT * FROM " + myTableName + " WHERE (title like '" + name + "')");      
    }

    public void findBooksWithAuthorLike(String name) throws InvalidPrimaryKeyException {
            executeQuery("SELECT * FROM " + myTableName + " WHERE (author like'" + name + "')");
    }

private void executeQuery(String query) throws InvalidPrimaryKeyException {
        Vector allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            bookList = new Vector<Book>();

            for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
                Properties nextBookData = (Properties) allDataRetrieved.elementAt(cnt);

                Book book = new Book(nextBookData);

                if (book != null) {
                    bookList.add(book);
                }
            }

        } else {
            throw new InvalidPrimaryKeyException("No books found");
        }
    }

    public Object getState(String key) {
        if (key.equals("books")) {
            return bookList;
        } else if (key.equals("bookList")) {
            return this;
        } else {
            return null;
        }
    }

    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }
    


    
}//end class