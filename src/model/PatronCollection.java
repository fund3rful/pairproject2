package model;

import exception.InvalidPrimaryKeyException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userinterface.MainStageContainer;
import userinterface.PatronCollectionView;

/**
 *
 * @author Neoaptt
 */
public class PatronCollection extends EntityBase {

    private Vector<Patron> patronList;
    private static final String myTableName = "Patron";

    public PatronCollection() {
        super(myTableName);
    }

    public void findPatronsOlderThan(String date) throws InvalidPrimaryKeyException {
        String sql = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth < '" + date + "')";
        executeQuery(sql);
    }

    public void findPatronsYoungerThan(String date) throws InvalidPrimaryKeyException {
        executeQuery("SELECT * FROM " + myTableName + " WHERE (dateOfBirth > '" + date + "')");
    }

    public void findPatronsAtZipCode(String zip) throws InvalidPrimaryKeyException {
        executeQuery("SELECT * FROM " + myTableName + " WHERE (zip = " + zip + ") ORDER BY NAME");
    }

    public void findPatronsWithNameLike(String name) throws InvalidPrimaryKeyException {
        executeQuery("SELECT * FROM " + myTableName + " WHERE (name like '%" + name + "%')");
    }

    private void executeQuery(String query) throws InvalidPrimaryKeyException {
        Vector allDataRetrieved = getSelectQueryResult(query);

        if (allDataRetrieved != null) {
            patronList = new Vector<Patron>();

            for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++) {
                Properties nextPatronData = (Properties) allDataRetrieved.elementAt(cnt);

                Patron patron = new Patron(nextPatronData);

                if (patron != null) {
                    patronList.add(patron);
                }
            }

        } else {
            throw new InvalidPrimaryKeyException("No patrons found");
        }
    }

    public Object getState(String key) {
        if (key.equals("patrons")) {
            return patronList;
        } else if (key.equals("patronsList")) {
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
    public void createAndShowView(){
        Stage stage = MainStageContainer.getInstance();
        PatronCollectionView pcv = new PatronCollectionView(this);
        Scene scene = new Scene(pcv,1000,1000);
        stage.setScene(scene);
    }
    
}
