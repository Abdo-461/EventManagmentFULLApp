package controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Event;
import model.Status;
import uniLinkDatabase.UniLinkDB;

public class CreateNewEventController implements Initializable  {

	/*FXML components on the scene - START
	 * 
	 * */
	@FXML
    private Button createEventBtn;
	
	@FXML
	private Button goBackBtn;
	
	@FXML
	private TextField eventTitle;
	
	@FXML
	private TextField eventDescription;
	
	@FXML
	private TextField eventVenue;
	
	@FXML
	private TextField eventCapacity;
	
	@FXML
	private TextField eventDate;
	
	@FXML
	private Label invalidInput;
	
	@FXML
	private ImageView eventImage;
	 
	 /*FXML components on the scene - END
	  * 
	  * */
	 
	//instance variables to capture user input 
 	String id;
    String title;
    String description;
    String creatorid;
    String status;
    String venue;
    String date;
    int capacity;
	    

	//this function creates an event post - Button createEventBtn
    @FXML
    void createEvent(ActionEvent event) {
    	//this function inserts user input into database
    	insertIntoDatabase();
    }
    
    //function to let user upload an image
    @FXML
    void uploadImage() {
    	
    	FileChooser Image = new FileChooser();
    	File selectedImage = Image.showOpenDialog(null);
    	Image image = new Image(selectedImage.toURI().toString());

    	if(selectedImage !=null) {
    		eventImage.setImage(image);	
    	}	
    }
    
    //this function gives the user a button to go back to the main window - Button goBackBtn
    @FXML
    void revert(ActionEvent event) {
    	//create a reference to the FXML page
    	FXMLLoader mainWindowRevert = new FXMLLoader(getClass().getResource("/view/MainWindowView.fxml"));
    	//load the FXML page
    	Parent root;
		try {
			root = (Parent) mainWindowRevert.load();
			//load the existing stage
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//create new scene and load FXML page onto scene
	    	Scene mainwindowscene = new Scene(root);
	    	//put the scene onto the existing
	    	window.setScene(mainwindowscene);
	    	//show the scene on the stage
	    	window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
        
    //this function inserts user input into the database
    @SuppressWarnings("unused")
	@FXML
    void insertIntoDatabase() {
    	
    	//to create new id starting from EVE00 and increments as a user creates a post
    	Event events = new Event("EVE00");
        	
    try {
    	 id = events.getEPostID();
    	 title = eventTitle.getText();
    	 description = eventDescription.getText();
    	 creatorid = " ";
    	 status = Status.OPEN.toString();
    	 venue = eventVenue.getText();
    	 date = eventDate.getText();
    	 capacity = Integer.parseInt(eventCapacity.getText());
    	
    }catch(Exception e) {
    	invalidInput.setText("Invalid Input,Invalid Date format, Please enter valid input");
    	}
    	
	//Initialize database created
	final String DB_NAME = "uniLinkDB";
	//Initialize tables in database, one for each kind of posts and one for replies
	final String TABLE_NAME_EVENT = "EVENTS";
	
	//use try-with-resources Statement
	try (Connection con = UniLinkDB.getConnection(DB_NAME);
			)  
		{
		Event event = this.createEventObj(id,title, description,creatorid,status,venue,date,capacity,0);
		String query = "INSERT INTO " + TABLE_NAME_EVENT +
				" VALUES (?,?,?,?,?,?,?,?)";
		
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, id);
		stmt.setString(2, title);
		stmt.setString(3, description);
		stmt.setString(4, creatorid);
		stmt.setString(5, status);
		stmt.setString(6, venue);
		stmt.setString(7,date);
		stmt.setInt(8, capacity);
	
		int rs = stmt.executeUpdate();
		con.commit();
		con.close();
		
		//message to show when the record is created
		invalidInput.setText("Event recorded in database succesfully!");
	
	} catch (Exception e) {
		invalidInput.setText(e.getMessage());
		}
  }
    
    //this function creates an event object using the model Event
    public Event createEventObj(String eventID ,String eventTitle, String eventDescription ,String creatorID, String status,String eventVenue ,String eventDate, int eventCapacity,int attendee){
    	
    	//create an object of event
    	Event event = new Event();
    	//get elements of the post by the model functions
    	event.setEPostID(eventID);
    	event.setPostTitle(eventTitle);
    	event.setPostDescription(eventDescription);
    	event.setCreatorID(creatorID);
    	event.setEventVenue(eventVenue);
    	event.setEventDate(eventDate);
    	event.setEventCapacity(eventCapacity);
    	event.setAttendees(attendee);
    	
    	return event;
    }
    
    //this function opens a stage to confirm to the user that the posts has been created
    @FXML
    void confirm(ActionEvent event) {
    	
    	//reference to the developer-info.fxml page
    	FXMLLoader confirmView = new FXMLLoader(getClass().getResource("../view/ConfirmCreate.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) confirmView.load();
			//create a new stage for Developer-info.fxml
		    Stage ConfirmStage = new Stage();
			//set the scene in the new stage
		    ConfirmStage.setScene(new Scene(root));
		    //show stage
		    ConfirmStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
}
