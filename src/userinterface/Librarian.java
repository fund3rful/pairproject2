package userinterface;



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
 
public class Librarian extends Application {
    public Librarian(){
        
    }
    public static void run(String[] args) {
        launch(args);
    }
    
    
    //Librarian view
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pair Project 2");
        //grids
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5,5,5,5));
        
        Scene librarianScene = new Scene(grid, 300,275);
        
        //Title
        Text scenetitle = new Text("Library System");
        HBox hbSceneTitle = new HBox(10);
        hbSceneTitle.getChildren().add(scenetitle);
        scenetitle.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
        hbSceneTitle.setAlignment(Pos.BOTTOM_CENTER);
        grid.add(hbSceneTitle, 0,0);
        
        //insert new book
        Button newBookBtn = new Button("Insert New Book");
        HBox hbNewBookBtn = new HBox(10); 
        hbNewBookBtn.setAlignment(Pos.CENTER);
        hbNewBookBtn.getChildren().add(newBookBtn);
        grid.add(hbNewBookBtn, 0, 1);
        
        newBookBtn.setOnAction(new EventHandler<ActionEvent>(){
        
            
            @Override
            public void handle(ActionEvent e){
                Book book = new Book();
                BookView bv = new BookView(book);
                Scene bvScene = new Scene(bv);
                
                primaryStage.setScene(bvScene);
            }

            
            
        });
        
        
        //insert new patron
        Button newPatronBtn = new Button("Insert New Patron");
        HBox hbNewPatronBtn = new HBox(10); 
        hbNewPatronBtn.setAlignment(Pos.CENTER);
        hbNewPatronBtn.getChildren().add(newPatronBtn);
        grid.add(hbNewPatronBtn, 0, 2);
        
        newPatronBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                Patron patron = new Patron();
                PatronView pv = new PatronView(patron);
                Scene pvScene = new Scene(pv);
                
                primaryStage.setScene(pvScene);
                
            }
        });
        
        //search books
        Button searchBookBtn = new Button("Search Books");
        HBox hbsearchBookBtn = new HBox(10); 
        hbsearchBookBtn.setAlignment(Pos.CENTER);
        hbsearchBookBtn.getChildren().add(searchBookBtn);
        grid.add(hbsearchBookBtn, 0, 3);
        
        searchBookBtn.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle (ActionEvent e){
                SearchBooksView SBV;
                SBV = new SearchBooksView();
                try {
                Scene sbvScene;
                    sbvScene = SBV.start(primaryStage);
                
                
                primaryStage.setScene(sbvScene);
                
                } catch (Exception ex) {
                    Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        }
    
        });
        
        //search patrons button
        Button searchPatronBtn = new Button("Search Patron");
        HBox hbsearchPatronBtn = new HBox(10); 
        hbsearchPatronBtn.setAlignment(Pos.CENTER);
        hbsearchPatronBtn.getChildren().add(searchPatronBtn);
        grid.add(hbsearchPatronBtn, 0, 4);
        
        searchPatronBtn.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle (ActionEvent e){
                SearchPatronView SPV;
                SPV = new SearchPatronView();
                try {
                Scene spvScene;
                    spvScene = SPV.start(primaryStage);
                
                
                primaryStage.setScene(spvScene);
                
                } catch (Exception ex) {
                    Logger.getLogger(Librarian.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        }
    
        });
        
        //Done Button
        Button doneBtn = new Button("DONE");
        HBox hbdoneBtn = new HBox(10); 
        hbdoneBtn.setAlignment(Pos.CENTER);
        hbdoneBtn.getChildren().add(doneBtn);
        grid.add(hbdoneBtn, 0, 10);
        
        
        primaryStage.setScene(librarianScene);
        primaryStage.show();
    }
}