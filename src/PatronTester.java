
import event.Event;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.Patron;
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WindowPosition;

public class PatronTester extends Application {

    private Stage myStage;
    private Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Patron Version 3.00");
        System.out.println("Copyright No One?");

        // Create the top-level container (main frame) and add contents to it.
        MainStageContainer.setStage(primaryStage, "Brockport Patron Tester");
        mainStage = MainStageContainer.getInstance();

        // Finish setting up the stage (ENABLE THE GUI TO BE CLOSED USING THE TOP RIGHT
        // 'X' IN THE WINDOW), and show it.
        mainStage.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
            @Override
            public void handle(javafx.stage.WindowEvent event) {
                System.exit(0);
            }
        });

        try {
            View newView = ViewFactory.createView("PatronView", new Patron(1)); // USE VIEW FACTORY
            Scene currentScene = new Scene(newView);
            myStage.setScene(currentScene);
            myStage.sizeToScene();
            WindowPosition.placeCenter(myStage);

        } catch (Exception exc) {
            System.err.println("PatronTester.PatronTester - could not create PatronTester!");
            new Event(Event.getLeafLevelClassName(this), "PatronTester.<init>", "Unable to create PatronTester object", Event.ERROR);
            exc.printStackTrace();
        }

        WindowPosition.placeCenter(mainStage);

        mainStage.show();
    }


}
