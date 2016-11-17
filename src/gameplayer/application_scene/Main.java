package gameplayer.application_scene;

import gameplayer.application_controller.ApplicationController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ApplicationController appControl = new ApplicationController(primaryStage);
		appControl.displayLogin();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}