package userinterface;

import impresario.IModel;
import impresario.IView;
import impresario.ModelRegistry;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import model.*;
import userinterface.*;

public class Librarian implements IModel, IView {

    private Properties dependencies;
    private ModelRegistry myRegistry;

    Hashtable<String, Scene> myViews;
    private Stage myStage = MainStageContainer.getInstance();

    public Librarian() {

    }

    public void start() {
        myStage = MainStageContainer.getInstance();
        
        LibrarianView lv;
        lv = new LibrarianView(this);
        Scene scene = new Scene(lv);       
        myStage.setScene(scene);
        WindowPosition.placeCenter(myStage);
        myStage.show();

    }

    public void createNewBook() {
        myStage = MainStageContainer.getInstance();

        Book book = new Book();
        BookView bv = new BookView(book);
        Scene scene = new Scene(bv);
        myStage.setScene(scene);

    }

    public void createNewPatron() {
        myStage = MainStageContainer.getInstance();

        Patron patron = new Patron();
        PatronView pv = new PatronView(patron);
        Scene scene = new Scene(pv);
        myStage.setScene(scene);

    }

    public void searchBooks() {
        myStage = MainStageContainer.getInstance();

        SearchBooksView sbv = new SearchBooksView(this);
        Scene scene = new Scene(sbv);
        myStage.setScene(scene);
    }
    
    public void searchPatrons(){
        myStage = MainStageContainer.getInstance();

        SearchPatronsView spv = new SearchPatronsView(this);
        Scene scene = new Scene(spv);
        myStage.setScene(scene);
    }

    @Override
    public Object getState(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void subscribe(String key, IView subscriber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unSubscribe(String key, IView subscriber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stateChangeRequest(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateState(String key, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class LibrarianView extends View {

        static Librarian lib;

        /**
         *
         * @param model
         */
        public LibrarianView(IModel model) {
            super(model, "LibrarianView");
            lib = (Librarian) model;
            // create a container for showing the contents
            VBox container = new VBox(10);
            container.setPadding(new Insets(50, 50, 50, 50));

            // create our GUI components, add them to this Container
            container.getChildren().add(createFormContent());

            getChildren().add(container);

        }

        public VBox createFormContent() {

            //grids
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(5);
            grid.setVgap(5);
            grid.setPadding(new Insets(15, 5, 5, 5));

            //Title
            Text scenetitle = new Text("Library System");
            HBox hbSceneTitle = new HBox(10);
            hbSceneTitle.getChildren().add(scenetitle);
            scenetitle.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
            hbSceneTitle.setAlignment(Pos.BOTTOM_CENTER);
            grid.add(hbSceneTitle, 0, 0);

            //insert new book
            Button newBookBtn = new Button("Insert New Book");
            HBox hbNewBookBtn = new HBox(10);
            hbNewBookBtn.setAlignment(Pos.CENTER);
            hbNewBookBtn.getChildren().add(newBookBtn);
            grid.add(hbNewBookBtn, 0, 1);

            //Button command
            newBookBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    lib.createNewBook();
                }

            });

            //insert new patron
            Button newPatronBtn = new Button("Insert New Patron");
            HBox hbNewPatronBtn = new HBox(10);
            hbNewPatronBtn.setAlignment(Pos.CENTER);
            hbNewPatronBtn.getChildren().add(newPatronBtn);
            grid.add(hbNewPatronBtn, 0, 2);

            //Button command
            newPatronBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    lib.createNewPatron();
                }
            });

            //search books
            Button searchBookBtn = new Button("Search Books");
            HBox hbsearchBookBtn = new HBox(10);
            hbsearchBookBtn.setAlignment(Pos.CENTER);
            hbsearchBookBtn.getChildren().add(searchBookBtn);
            grid.add(hbsearchBookBtn, 0, 3);

            searchBookBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                    lib.searchBooks();
                }

            });

            //search patrons button
            Button searchPatronBtn = new Button("Search Patron");
            HBox hbsearchPatronBtn = new HBox(10);
            hbsearchPatronBtn.setAlignment(Pos.CENTER);
            hbsearchPatronBtn.getChildren().add(searchPatronBtn);
            grid.add(hbsearchPatronBtn, 0, 4);

            searchPatronBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent e) {
                   lib.searchPatrons();
                }

            });

            //Done Button
            Button doneBtn = new Button("DONE");
            HBox hbdoneBtn = new HBox(10);
            hbdoneBtn.setAlignment(Pos.CENTER);
            hbdoneBtn.getChildren().add(doneBtn);
            
            doneBtn.setOnAction(new EventHandler<ActionEvent>(){
                
                @Override
                public void handle(ActionEvent e){
                    System.exit(0);
                }
            });
            
            
            grid.add(hbdoneBtn, 0, 10);

            VBox vb = new VBox(10);
            vb.getChildren().add(grid);
            return vb;

        }

        @Override
        public void updateState(String key, Object value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
