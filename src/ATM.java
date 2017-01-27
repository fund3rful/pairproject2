
// specify the package

// system imports

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;

// project imports

import userinterface.MainStageContainer;
import userinterface.WindowPosition;


/** The class containing the main program  for the ATM application */
//==============================================================
public class ATM extends Application
{


	/** Main frame of the application */
	private Stage mainStage;


	// start method for this class, the main application object
	//----------------------------------------------------------
	public void start(Stage primaryStage)
	{
	   System.out.println("ATM Version 3.00");
	   System.out.println("Copyright 2004/2015 Sandeep Mitra and T M Rao");

	}


	/** 
	 * The "main" entry point for the application. Carries out actions to
	 * set up the application
	 */
	//----------------------------------------------------------
    	public static void main(String[] args)
	{

		launch(args);
	}

}
