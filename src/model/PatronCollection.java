package model;

import exception.InvalidPrimaryKeyException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author Neoaptt
 */
public class PatronCollection extends EntityBase {

    private String updateStatusMessage = "";
    private Vector<Patron> patrons;
    private static final String myTableName = "Patron";

    public PatronCollection() {
        super("Patron");
    }

    public void findPatronsOlderThan(String date) throws Exception {
        executeQuery("SELECT * FROM " + myTableName + " WHERE (dateOfBirth < " + date + ")");
    }

    public void findPatronsYoungerThan(String date) throws Exception {
        executeQuery("SELECT * FROM " + myTableName + " WHERE (dateOfBirth > " + date + ")");
    }

    public void findPatronsAtZipCode(String zip) throws Exception {
        executeQuery("SELECT * FROM " + myTableName + " WHERE (zip = " + zip + ")");
    }

    public void findPatronsWithNameLike(String name) throws Exception {
        executeQuery("SELECT * FROM " + myTableName + " WHERE (name like '" + name + "')");
    }

    private void executeQuery(String query) throws Exception {
        Vector allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            patrons = new Vector<Patron>();

            for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
                Properties nextPatronData = (Properties) allDataRetrieved.elementAt(cnt);

                Patron patron = new Patron(nextPatronData);

                if (patron != null) {
                    patrons.add(patron);
                }
            }

        } else {
            throw new InvalidPrimaryKeyException("No patrons found");
        }
    }

    @Override
    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage") == true) {
            return updateStatusMessage;
        } else {
            return persistentState.getProperty(key);
        }

    }

    @Override
    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    @Override
    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }

}
