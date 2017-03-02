/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import exception.InvalidPrimaryKeyException;
import impresario.IModel;
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
import javafx.stage.Stage;
import model.PatronCollection;

/**
 *
 * @author Andrew
 */
public class SearchPatronsView extends View{
    
    public SearchPatronsView(IModel model){
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
        grid.setPadding(new Insets(25,25,25,25));
        
        //title
       Text scenetitle = new Text("Search Patrons By Zip");
       scenetitle.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
       grid.add(scenetitle, 0,0,2,1);
              
       //Label
       Label patrontitle = new Label("Zip Code:");
       grid.add(patrontitle, 0, 1);
       
       //text fields
       TextField patronTextField = new TextField();
       grid.add(patronTextField,1,1);
       
       //Button
       Button submitButton = new Button("Submit");
       grid.add(submitButton, 2, 2);
       
       submitButton.setOnAction(new EventHandler<ActionEvent>(){
        
            
            @Override
            public void handle(ActionEvent e){
                
                String temp = patronTextField.getText();
                PatronCollection p = new PatronCollection();
                try {
                    p.findPatronsAtZipCode(temp);
                } catch (InvalidPrimaryKeyException ex) {
                    Logger.getLogger(SearchPatronsView.class.getName()).log(Level.SEVERE, null, ex);
                    patronTextField.setText("No Patrons Found!");
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
}