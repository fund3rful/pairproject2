
import userinterface.Librarian;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import userinterface.MainStageContainer;
import userinterface.WindowPosition;

/**
 * Test the functionality of patron and book console commands.
 */
public class main extends Application {

    private Stage mainStage;
    private static final int EXIT = 0;
    private static final int PATRON = 1;
    private static final int BOOK = 2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        //Create top-level container and add contents to it.
        MainStageContainer.setStage(primaryStage, "Pair Project 2");
        mainStage = MainStageContainer.getInstance();

        mainStage.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
            @Override
            public void handle(javafx.stage.WindowEvent event) {
                System.exit(0);
            }
        });

        WindowPosition.placeCenter(mainStage);
        Librarian lib = new Librarian();
        lib.start();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
//end class
