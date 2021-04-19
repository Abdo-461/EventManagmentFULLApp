package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmController implements Initializable {

	 	@FXML
	    private Button closeWindow;

	    @FXML
	    void closeWindow(ActionEvent event) {
	    	
	    	//derive the stage to the event handler
	    	Node  source = (Node)  event.getSource();
	    	//load the stage to use the event on
	        Stage stage  = (Stage) source.getScene().getWindow();
	        //close the stage
	        stage.close();

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
}
