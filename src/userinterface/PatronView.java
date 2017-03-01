// specify the package
package userinterface;

// system imports
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Properties;

// project imports
import impresario.IModel;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javafx.scene.control.DatePicker;
import model.*;

/**
 * The class containing the Account View for the ATM application
 */
//==============================================================
public class PatronView extends View {

    // GUI components
    protected TextField patronId;
    protected TextField name;
    protected TextField address;
    protected TextField city;
    protected TextField state;
    protected TextField zip;
    protected TextField email;
    protected DatePicker datePicker;
    protected TextField status;

    protected Button cancelButton;
    protected Button submitButton;

    // For showing error message
    protected MessageView statusLog;


    // constructor for this class -- takes a model object
    //----------------------------------------------------------
    public PatronView(IModel model) {
        super(model, "PatronView");

        // create a container for showing the contents
        VBox container = new VBox(10);
        container.setPadding(new Insets(15, 5, 5, 5));

        // Add a title for this panel
        container.getChildren().add(createTitle());

        // create our GUI components, add them to this Container
        container.getChildren().add(createFormContent());

        container.getChildren().add(createStatusLog("             "));

        getChildren().add(container);

        populateFields();

//        myModel.subscribe("ServiceCharge", this);
//        myModel.subscribe("UpdateStatusMessage", this);
    }

    // Create the title container
    //-------------------------------------------------------------
    private Node createTitle() {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);

        Text titleText = new Text(" Brockport Library ");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setWrappingWidth(300);
        titleText.setTextAlignment(TextAlignment.CENTER);
        titleText.setFill(Color.DARKGREEN);
        container.getChildren().add(titleText);

        return container;
    }

    // Create the main form content
    //-------------------------------------------------------------
    private VBox createFormContent() {
        VBox vbox = new VBox(10);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text prompt = new Text("PATRON INFORMATION");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        Text idLabel = new Text(" PatronID : ");
        Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
        idLabel.setFont(myFont);
        idLabel.setWrappingWidth(150);
        idLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(idLabel, 0, 1);

        patronId = new TextField();
        patronId.setEditable(false);
        grid.add(patronId, 1, 1);

        Text nameLabel = new Text(" Patron Name : ");
        nameLabel.setFont(myFont);
        nameLabel.setWrappingWidth(150);
        nameLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(nameLabel, 0, 2);

        name = new TextField();
        name.setEditable(true);
        grid.add(name, 1, 2);

        Text addressLabel = new Text(" Address : ");
        addressLabel.setFont(myFont);
        addressLabel.setWrappingWidth(150);
        addressLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(addressLabel, 0, 3);

        address = new TextField();
        address.setEditable(true);
        grid.add(address, 1, 3);
        
        Text cityLabel = new Text(" City : ");
        cityLabel.setFont(myFont);
        cityLabel.setWrappingWidth(150);
        cityLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(cityLabel, 0, 4);

        city = new TextField();
        city.setEditable(true);
        grid.add(city, 1, 4);
        
        Text stateLabel = new Text(" State Code : ");
        stateLabel.setFont(myFont);
        stateLabel.setWrappingWidth(150);
        stateLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(stateLabel, 0, 5);

        state = new TextField();
        state.setEditable(true);
        grid.add(state, 1, 5);
        
        Text zipLabel = new Text(" Zip Code : ");
        zipLabel.setFont(myFont);
        zipLabel.setWrappingWidth(150);
        zipLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(zipLabel, 0, 6);

        zip = new TextField();
        zip.setEditable(true);
        grid.add(zip, 1, 6);
        
        Text emailLabel = new Text(" Email Address : ");
        emailLabel.setFont(myFont);
        emailLabel.setWrappingWidth(150);
        emailLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(emailLabel, 0, 7);

        email = new TextField();
        email.setEditable(true);
        grid.add(email, 1, 7);
        
        Text dobLabel = new Text(" Date Of Birth : ");
        dobLabel.setFont(myFont);
        dobLabel.setWrappingWidth(150);
        dobLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(dobLabel, 0, 8);      
        
        DatePicker datePicker = new DatePicker();
        grid.add(datePicker, 1, 8);
        
        datePicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) 
            {
                //LocalDate dateOfBirth = datePicker.getValue();
                LocalDate ld = datePicker.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
                String dateOfBirth = ld.format(formatter);
                System.out.println(dateOfBirth);
            }
        
            });
        
        MessageView messageView = createStatusLog("");
        messageView.setFont(myFont);
        messageView.setWrappingWidth(150);
        messageView.setTextAlignment(TextAlignment.RIGHT);
        grid.add(messageView, 0, 20);
        
        HBox doneCont = new HBox(10);
        doneCont.setAlignment(Pos.CENTER_RIGHT);
        cancelButton = new Button("Back");
        cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Librarian lib = new Librarian();
                lib.start();
                //messageView.displayMessage("Back Button Pressed");
            }
        });
        doneCont.getChildren().add(cancelButton);
        grid.add(doneCont, 1,10);
        
       
        
        HBox hbSubmit = new HBox(10);
        hbSubmit.setAlignment(Pos.CENTER_LEFT);
        submitButton = new Button("Submit");
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Properties prop = new Properties();
                
        /* Make sure all fields are not null */
        String patronName = name.getText();
        if (patronName.isEmpty()){
            messageView.displayMessage("You must input a Name");
            name.requestFocus();
            return;                  
                    
        }
        
        String patronAddress = address.getText();
        if (patronAddress.isEmpty()) {
            messageView.displayMessage("You must input a Address");
            return;
        }
        
        String patronCity = city.getText();
        if (patronCity.isEmpty()) {
            messageView.displayMessage("You must input a City");
            return;
        }
        
        String patronStateCode = state.getText();
        if (patronStateCode.isEmpty()) {
            messageView.displayMessage("You must input a State code");
            return;
        }
        
        String patronZip = zip.getText();
        if (patronZip.isEmpty()) {
            messageView.displayMessage("You must input a Zip code");
            return;
        }
        
        String patronEmail = email.getText();
        if (patronEmail.isEmpty()) {
            messageView.displayMessage("You must input a Email address");
            return;
        }
        LocalDate ld = datePicker.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String dateOfBirth = ld.format(formatter);
        String patronDOB = dateOfBirth;
        if (patronDOB.isEmpty()) {
            messageView.displayMessage("You must choose a Date of birth");
            return;
        }
       
        
        //if (patronDOB.startsWith()) {
        //    messageView.displayMessage("Patron is too old");
        //    return;
        //} else {
        //}
        
        if (patronDOB.startsWith("2000  2017")) {
            messageView.displayMessage("Patron must be 18 years or older");
            return;
        }
                
        /*get all the information via GUI and populate a properties object*/
        prop.setProperty("name", name.getText());
        prop.setProperty("address", address.getText());
        prop.setProperty("city", city.getText());
        /*make all inputs uppercase by default*/
        prop.setProperty("stateCode", state.getText().toUpperCase());
        prop.setProperty("zip", zip.getText());
        prop.setProperty("email", email.getText());
        prop.setProperty("dateOfBirth", dateOfBirth);
        
        
        
        /*use the information to create a new patron*/
        Patron patron = (Patron) myModel;
        patron.processNewPatron(prop);
        patron.update();
        messageView.displayMessage("Patron added successfully");
        
                
            }
        });
        
        hbSubmit.getChildren().add(submitButton);
        grid.add(hbSubmit,0,10);
       

        vbox.getChildren().add(grid);
        //vbox.getChildren().add(doneCont);

        return vbox;
    }

    // Create the status log field
    //-------------------------------------------------------------
    protected MessageView createStatusLog(String initialMessage) {
        statusLog = new MessageView(initialMessage);

        return statusLog;
    }

    /**
     * sets the information given by the model to the input boxes
     */
    public void populateFields() {
        
        patronId.setText((String) myModel.getState("patronId"));
        name.setText((String) myModel.getState("name"));
        address.setText((String) myModel.getState("address"));
        city.setText((String) myModel.getState("city"));
        
    }

    /**
     * Update method
     */
    public void updateState(String key, Object value) {
        clearErrorMessage();

//        if (key.equals("ServiceCharge") == true) {
//            String val = (String) value;
//            city.setText(val);
//            displayMessage("Service Charge Imposed: $ " + val);
//        }
    }

    /**
     * Display error message
     */
    public void displayErrorMessage(String message) {
        statusLog.displayErrorMessage(message);
    }

    /**
     * Display info message
     */
    public void displayMessage(String message) {
        statusLog.displayMessage(message);
    }

    /**
     * Clear error message
     */
    public void clearErrorMessage() {
        statusLog.clearErrorMessage();
    }

}
