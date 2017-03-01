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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * The class containing the Account View for the ATM application
 */
//==============================================================
public class BookView extends View {

    // GUI components
    protected TextField bookID;
    protected TextField author;
    protected TextField title;
    protected TextField pubYear;
    protected ComboBox status;

    protected Button cancelButton;
    protected Button submitButton;

    // For showing error message
    protected MessageView statusLog;

    // constructor for this class -- takes a model object
    //----------------------------------------------------------
    public BookView(IModel account) {
        super(account, "BookView");

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

        //grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //ALL TEXT FIELD LABELS
        Text prompt = new Text("BOOK INFORMATION");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

        Text bookNumLabel = new Text(" Book Number : ");
        Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
        bookNumLabel.setFont(myFont);
        bookNumLabel.setWrappingWidth(150);
        bookNumLabel.setTextAlignment(TextAlignment.RIGHT);
        //grid.add(bookNumLabel, 0, 1);

        bookID = new TextField();
        bookID.setEditable(true);
        //grid.add(bookID, 1, 1);

        Text authorLabel = new Text(" Author : ");
        authorLabel.setFont(myFont);
        authorLabel.setWrappingWidth(150);
        authorLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(authorLabel, 0, 2);

        author = new TextField();
        author.setEditable(true);
        grid.add(author, 1, 2);
        
        Text titleTypeLabel = new Text(" Title : ");
        titleTypeLabel.setFont(myFont);
        titleTypeLabel.setWrappingWidth(150);
        titleTypeLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(titleTypeLabel, 0, 3);

        title = new TextField();
        title.setEditable(true);
        grid.add(title, 1, 3);

        Text pubYearLabel = new Text(" Pub Year : ");
        pubYearLabel.setFont(myFont);
        pubYearLabel.setWrappingWidth(150);
        pubYearLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(pubYearLabel, 0, 4);

        pubYear = new TextField();
        pubYear.setEditable(true);
        grid.add(pubYear, 1, 4);
        
        Text statusLabel = new Text(" Status : ");
        statusLabel.setFont(myFont);
        statusLabel.setWrappingWidth(150);
        statusLabel.setTextAlignment(TextAlignment.RIGHT);
        grid.add(statusLabel, 0, 5);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                "Active",
                "Inactive"
                );
        status = new ComboBox(options);
       
        //status = new TextField();
       // status.setEditable(true);
        grid.add(status, 1, 5);
        
        MessageView messageView = createStatusLog("");
        messageView.setFont(myFont);
        messageView.setWrappingWidth(150);
        messageView.setTextAlignment(TextAlignment.RIGHT);
        grid.add(messageView,0,7);
        
        //INB4 BUTTON
        HBox doneCont = new HBox(10);
        doneCont.setAlignment(Pos.CENTER_RIGHT);
        cancelButton = new Button("Done");
        cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                
                
                Librarian lib = new Librarian();
                lib.start();
                
                //clearErrorMessage();
                 messageView.displayMessage("Done button pressed");
                //myModel.stateChangeRequest("AccountCancelled", null);
            }
        });
        
        doneCont.getChildren().add(cancelButton);
        grid.add(doneCont, 1,6);
       
        
        HBox hbSubmit = new HBox(10);
        hbSubmit.setAlignment(Pos.CENTER_LEFT);
        submitButton = new Button("Submit");
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                messageView.displayMessage("Submit button pressed");
                
            }
        });
        
        hbSubmit.getChildren().add(submitButton);
        grid.add(hbSubmit,0,6);
        
        
        
        vbox.getChildren().add(grid);
        //vbox.getChildren().add(doneCont);
       // vbox.getChildren().add(hbSubmit);

        return vbox;
    }

    // Create the status log field
    //-------------------------------------------------------------
    protected MessageView createStatusLog(String initialMessage) {
        statusLog = new MessageView(initialMessage);

        return statusLog;
    }

    //-------------------------------------------------------------
    public void populateFields() {
        bookID.setText((String) myModel.getState("Book ID"));
        title.setText((String) myModel.getState("Title"));
        author.setText((String) myModel.getState("Author"));
        pubYear.setText((String) myModel.getState("Pub Year"));
        //status.setText((String) myModel.getState("Status"));
    }

    /**
     * Update method
     */
    //---------------------------------------------------------
    public void updateState(String key, Object value) {
        clearErrorMessage();

    }

    /**
     * Display error message
     */
    //----------------------------------------------------------
    public void displayErrorMessage(String message) {
        statusLog.displayErrorMessage(message);
    }

    /**
     * Display info message
     */
    //----------------------------------------------------------
    public void displayMessage(String message) {
        statusLog.displayMessage(message);
    }

    /**
     * Clear error message
     */
    //----------------------------------------------------------
    public void clearErrorMessage() {
        statusLog.clearErrorMessage();
    }

}
