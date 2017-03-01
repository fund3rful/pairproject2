/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import impresario.IModel;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Andrew
 */
public class SearchPatronView {
    
    public SearchPatronView(){
           
        
    }

    

    public Scene start(Stage stage) throws Exception {
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
                
                String temp;
                temp = patronTextField.getText();
                int tempInt = Integer.parseInt(temp);
            }
       
    });
       Scene scene = new Scene(grid, 400, 400);
       return scene;
    
    }
}