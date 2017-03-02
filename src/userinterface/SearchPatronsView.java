/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import exception.InvalidPrimaryKeyException;
import impresario.IModel;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Patron;
import model.PatronCollection;

/**
 *
 * @author Andrew
 */
public class SearchPatronsView extends View {
    protected MessageView statusLog;

    public SearchPatronsView(IModel model) {
        super(model, "SearchPatronsView");
        Librarian lib = (Librarian) model;

        //create a container for showing the contents
        VBox container = new VBox(10);
        container.setPadding(new Insets(15, 5, 5, 5));

        // create our GUI components, add them to this Container
        container.getChildren().add(createFormContent());

        getChildren().add(container);

    }

    public VBox createFormContent() {
        VBox vbox = new VBox(10);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //title
        Text scenetitle = new Text("Search Patrons By Zip");
        scenetitle.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //Label
        Label patrontitle = new Label("Zip Code:");
        grid.add(patrontitle, 0, 1);

        //text fields
        TextField patronTextField = new TextField();
        grid.add(patronTextField, 1, 1);

        //MessageField
        MessageView messageView = createStatusLog("");
        messageView.setWrappingWidth(150);
        messageView.setTextAlignment(TextAlignment.LEFT);
        grid.add(messageView, 0, 2);

        //Button
        Button submitButton = new Button("Submit");
        grid.add(submitButton, 2, 2);

        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                String temp = patronTextField.getText();
                if(temp == null || temp.isEmpty()){
                    messageView.displayMessage("You must enter a 5-digit ZIP code");
                    return;
                }
                PatronCollection p = new PatronCollection();
                try {
                    p.findPatronsAtZipCode(temp);
                } catch (InvalidPrimaryKeyException ex) {
                    // debug:Logger.getLogger(SearchPatronsView.class.getName()).log(Level.SEVERE, null, ex);
                   
                    messageView.displayMessage("No patrons with ZIP: "+temp+" Found!");
                }
                 Vector<Patron> patronList = (Vector<Patron>) p.getState("patrons");
                 if(patronList.isEmpty()){
                     messageView.displayMessage("No patrons with ZIP: "+temp+" Found!");
                     return;
                 }
                 
                 
                p.createAndShowView();

            }

        });
        vbox.getChildren().add(grid);
        return vbox;
    }

    @Override
    public void updateState(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayMessage(String message) {
        statusLog.displayMessage(message);
    }
    protected MessageView createStatusLog(String initialMessage) {
        statusLog = new MessageView(initialMessage);

        return statusLog;
    }
}
