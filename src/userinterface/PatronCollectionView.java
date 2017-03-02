/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import impresario.IModel;
import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;
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
        container.setPadding(new Insets(15, 5, 5, 5));

        container.getChildren().add(createFormContent());

        getChildren().add(container);

    }
    private TableView<PatronInsert> table = new TableView<PatronInsert>();
    private static final ObservableList<PatronInsert> data
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
                new PropertyValueFactory<PatronInsert, String>("name"));

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

    public static class PatronInsert {

        private final SimpleStringProperty name;
        private final SimpleStringProperty address;
        private final SimpleStringProperty city;
        private final SimpleStringProperty stateCode;
        private final SimpleStringProperty zip;
        private final SimpleStringProperty email;
        private final SimpleStringProperty dateOfBirth;

        private PatronInsert(String name, String address, String city, String stateCode,
                String zip, String email, String dateOfBirth) {
            this.address = new SimpleStringProperty(address);
            this.city = new SimpleStringProperty(city);
            this.name = new SimpleStringProperty(name);
            this.stateCode = new SimpleStringProperty(stateCode);
            this.zip = new SimpleStringProperty(zip);
            this.email = new SimpleStringProperty(email);
            this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        }

        public String getName() {
            return name.get();
        }

        public String getAddress() {
            return address.get();
        }

        public String getCity() {
            return city.get();
        }

        public String getStateCode() {
            return stateCode.get();
        }

        public String getZip() {
            return zip.get();
        }

        public String getEmail() {
            return email.get();
        }

        public String getDateOfBirth() {
            return dateOfBirth.get();
        }

        public void setName(String OtherName) {
            name.set(OtherName);
        }

        public void setAddress(String OtherName) {
            address.set(OtherName);
        }

        public void setCity(String OtherName) {
            city.set(OtherName);
        }

        public void setSateCode(String OtherName) {
            stateCode.set(OtherName);
        }

        public void setZip(String OtherName) {
            zip.set(OtherName);
        }

        public void setEmail(String OtherName) {
            email.set(OtherName);
        }

        public void setDateOfBirth(String OtherName) {
            dateOfBirth.set(OtherName);
        }
    }
}
