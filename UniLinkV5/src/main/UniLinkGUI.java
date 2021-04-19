package main;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class UniLinkGUI extends Application {
	
	//this function is main function of the whole program, its starts here!
	@Override
	public void start(Stage primaryStage) {
		try {
			//these lines references the FXML page and loads it into the stage
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/LogInWindow.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
			primaryStage.setTitle("UniLink");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


