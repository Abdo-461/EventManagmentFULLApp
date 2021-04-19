package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInWindowController<T> implements Initializable {

    @FXML
    public TextField studentUsername;

    @FXML
    private Button logInBtn;
    
    @FXML
    private Label usernameError;
    
    
    
    //this function loads main window and displays user name on window
    @FXML
    void loadMainWindow(ActionEvent event) throws IOException {
    	//create a reference to the FXML page
    	FXMLLoader mainwindow = new FXMLLoader(getClass().getResource("../view/MainWindowView.fxml"));
    	//load the FXML page
    	Parent root = (Parent) mainwindow.load();
    	//load the existing stage
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	//create new scene and load FXML page onto scene
    	Scene mainwindowscene = new Scene(root);
    	//put the scene onto the existing
    	window.setScene(mainwindowscene);
    	//show the scene on the stage
    	window.show();
    	
    	//this line loads the controller
    	MainWindowControllerr<T> mainWindowController = mainwindow.getController();
    	//this line called the function from the loaded controller
    	mainWindowController.displayStudentId(studentUsername.getText());
    	
    }
     
    /*Function when called opens main window on the same stage*/
    @FXML
    void openMainWindow(ActionEvent event)  {
    	
    	//REGEX for setting user name format
    	String standardnameUppercase = "S\\d{1}";
    	String standardnameLowercase = "s\\d{1}";
    	
    //this if statement when run , checks their user input if it matches what the REGEX set above	
    if(studentUsername.getText().matches(standardnameUppercase) || studentUsername.getText().matches(standardnameLowercase)){	
    	try {
			loadMainWindow(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			usernameError.setText(e.getMessage());
			System.out.print(e.getMessage());
		}
    	
    }	
    else {
    	usernameError.setText("Please enter valid username");
      
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}  
}

