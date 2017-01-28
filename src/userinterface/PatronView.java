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
    protected TextField dateOfBirth;
    protected TextField status;

    protected Button cancelButton;

    // For showing error message
    protected MessageView statusLog;

    // constructor for this class -- takes a model object
    //----------------------------------------------------------
    public PatronView(IModel account) {
        super(account, "PatronView");

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

        Text prompt = new Text("ACCOUNT INFORMATION");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        Text accNumLabel = new Text(" Account Number : ");
        Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
        accNumLabel.setFont(myFont);
        accNumLabel.setWrappingWidth(150);
        accNumLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(accNumLabel, 0, 1);

        patronId = new TextField();
        patronId.setEditable(false);
        grid.add(patronId, 1, 1);

        Text acctTypeLabel = new Text(" Account Type : ");
        acctTypeLabel.setFont(myFont);
        acctTypeLabel.setWrappingWidth(150);
        acctTypeLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(acctTypeLabel, 0, 2);

        name = new TextField();
        name.setEditable(false);
        grid.add(name, 1, 2);

        Text balLabel = new Text(" Account Balance : ");
        balLabel.setFont(myFont);
        balLabel.setWrappingWidth(150);
        balLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(balLabel, 0, 3);

        address = new TextField();
        address.setEditable(false);
        grid.add(address, 1, 3);
        
        Text cityLabel = new Text(" City : ");
        cityLabel.setFont(myFont);
        cityLabel.setWrappingWidth(150);
        cityLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(cityLabel, 0, 4);

        city = new TextField();
        city.setEditable(false);
        grid.add(city, 1, 4);

        HBox doneCont = new HBox(10);
        doneCont.setAlignment(Pos.CENTER);
        cancelButton = new Button("Back");
        cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                clearErrorMessage();
                myModel.stateChangeRequest("AccountCancelled", null);
            }
        });
        doneCont.getChildren().add(cancelButton);

        vbox.getChildren().add(grid);
        vbox.getChildren().add(doneCont);

        return vbox;
    }

    // Create the status log field
    //-------------------------------------------------------------
    protected MessageView createStatusLog(String initialMessage) {
        statusLog = new MessageView(initialMessage);

        return statusLog;
    }

    /**
     * sets the information given by the modal to the input boxes
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
