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
import javafx.event.EventType;
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
import model.Book;
import model.BookCollection;

/**
 *
 * @author Andrew
 */
public class SearchBooksView extends View {

    public SearchBooksView(IModel model) {
        super(model, "SearchBooksView");

        Librarian lib = (Librarian) model;
        //create a container 
        VBox container = new VBox(10);
        container.setPadding(new Insets(15, 5, 5, 5));

        //all GUI components
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
        Text scenetitle = new Text("Search Books By Title");
        scenetitle.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //Label
        Label booktitle = new Label("Book Title:");
        grid.add(booktitle, 0, 1);

        //text fields
        TextField bookTextField = new TextField();
        grid.add(bookTextField, 1, 1);

        //Button
        Button submitButton = new Button("Submit");
        grid.add(submitButton, 2, 2);

        submitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                BookCollection b = new BookCollection();
                String temp = bookTextField.getText();
                try {
                    b.findBooksWithTitleLike(temp);
                } catch (InvalidPrimaryKeyException ex) {
                    Logger.getLogger(SearchBooksView.class.getName()).log(Level.SEVERE, null, ex);
                    bookTextField.setText("No Books found!");
                }

                b.createAndShowView();

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
