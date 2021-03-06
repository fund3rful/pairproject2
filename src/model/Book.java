// specify the package
package model;

// system imports
import exception.InvalidPrimaryKeyException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class Book extends EntityBase {

    private static final String myTableName = "Book";
    protected Properties dependencies;
    private String updateStatusMessage = "";
    private static Properties persistantState;

    public Book(int bookID)
            throws InvalidPrimaryKeyException {
        super(myTableName);

        setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (bookID = " + bookID + ")";

        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        // You must get one account at least
        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();

            // There should be EXACTLY one account. More than that is an error
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple books matching id : "
                        + bookID + " found.");
            } else {
                // copy all the retrieved data into persistent state
                Properties retrievedAccountData = allDataRetrieved.elementAt(0);
                persistentState = new Properties();

                Enumeration allKeys = retrievedAccountData.propertyNames();
                while (allKeys.hasMoreElements() == true) {
                    String nextKey = (String) allKeys.nextElement();
                    String nextValue = retrievedAccountData.getProperty(nextKey);

                    if (nextValue != null) {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }

            }
        } // If no account found for this user name, throw an exception
        else {
            throw new InvalidPrimaryKeyException("No book matching id : "
                    + bookID + " found.");
        }
    }

    public Book(Properties props) {
        super(myTableName);

        setDependencies();
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements() == true) {
            String nextKey = (String) allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);

            if (nextValue != null) {
                persistentState.setProperty(nextKey, nextValue);
            }
        }
    }

    /**
     * for creation of the GUI "BookView"
     */
    public Book() {
        super(myTableName);
        
        persistentState = new Properties();
    }
    
    public void processNewBook(Properties b)
    {
        persistentState = b;
        
    }

    public void update() {
        updateStateInDatabase();
    }

    private void updateStateInDatabase() {
        try {
            if (persistentState.getProperty("bookID") != null) {
                Properties whereClause = new Properties();
                whereClause.setProperty("bookID",
                        persistentState.getProperty("bookID"));
                updatePersistentState(mySchema, persistentState, whereClause);
                updateStatusMessage = "Book data for book number : " + persistentState.getProperty("bookID") + " updated successfully in database!";
            } else {
                Integer accountNumber
                        = insertAutoIncrementalPersistentState(mySchema, persistentState);
                persistentState.setProperty("bookID", "" + accountNumber.intValue());
                updateStatusMessage = "Book data for new book : " + persistentState.getProperty("bookID")
                        + "installed successfully in database!";
            }
        } catch (SQLException ex) {
            updateStatusMessage = "Error in installing book data in database!";
        }
        //DEBUG System.out.println("updateStateInDatabase " + updateStatusMessage);
    }

    private void setDependencies() {
        dependencies = new Properties();
        myRegistry.setDependencies(dependencies);
    }

    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage") == true) {
            return updateStatusMessage;
        }
        return persistentState.getProperty(key);
    }

    public void stateChangeRequest(String key, Object value) {

        myRegistry.updateSubscribers(key, this);
    }

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }

    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
    }

}//END CLASS
