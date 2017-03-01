/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import impresario.IModel;
import java.util.Vector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Patron;
import model.PatronCollection;
import model.PatronInsert;

/**
 *
 * @author Andrew
 */
public class PatronCollectionView extends View {

    static PatronCollection p;

    public PatronCollectionView(IModel model) {
        super(model, "PatronCollectionView");
        p = (PatronCollection) model;
        
        addData();
        
        VBox container = new VBox(10);
        container.setPadding(new Insets(15,5,5,5));
        
        container.getChildren().add(createFormContent());
        
        getChildren().add(container);

    }
    private TableView<PatronInsert> table = new TableView<PatronInsert>();
    private final ObservableList<PatronInsert> data
            = FXCollections.observableArrayList();

    public void addData() {
        Vector<Patron> patronList = (Vector<Patron>) p.getState("patrons");
        for (Patron person : patronList) {
            data.add(new PatronInsert((String) person.getState("name"), (String) person.getState("address"), (String) person.getState("city"), (String) person.getState("stateCode"), (String) person.getState("zip"),
                    (String) person.getState("email"), (String) person.getState("dateOfBirth")));
        }
    }

    public VBox createFormContent() {

        //box
        VBox vbox = new VBox(10);
        
        //Label
        final Label label = new Label("Patrons");
        label.setFont(new Font("Arial", 20));
        
        //create columns
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<PatronInsert,String>("name"));
        
        TableColumn addressCol = new TableColumn("Address");
        addressCol.setMinWidth(100);
        addressCol.setCellValueFactory(
                new PropertyValueFactory<PatronInsert, String>("address"));
 
        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<PatronInsert, String>("city"));
        
        TableColumn stateCodeCol = new TableColumn("State Code");
        stateCodeCol.setMinWidth(100);
        stateCodeCol.setCellValueFactory(
                new PropertyValueFactory<PatronInsert, String>("stateCode"));
        
        TableColumn zipCol = new TableColumn("Zip Code");
        zipCol.setMinWidth(100);
        zipCol.setCellValueFactory(
                new PropertyValueFactory<PatronInsert, String>("zip"));
        
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<PatronInsert, String>("email"));
        
        TableColumn dobNameCol = new TableColumn("Date of Birth");
        dobNameCol.setMinWidth(100);
        dobNameCol.setCellValueFactory(
                new PropertyValueFactory<PatronInsert, String>("dateOfBirth"));
        
        table.setItems(data);
        table.getColumns().addAll(nameCol, cityCol, stateCodeCol, zipCol, emailCol, dobNameCol);
        
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        
        vbox.getChildren().addAll(label, table);
        
        return vbox;
    }

    @Override
    public void updateState(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
