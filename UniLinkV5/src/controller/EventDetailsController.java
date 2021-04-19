package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Event;
import model.Status;
import uniLinkDatabase.UniLinkDB;

public class EventDetailsController implements Initializable  {

	/*FXML components on the scene - START
	 * 
	 * */
    @FXML
    private Button goBackBtn;

    @FXML
    private ImageView eventImage;

    @FXML
    private Label venue;

    @FXML
    private Label date;

    @FXML
    private Label capacity;

    @FXML
    private Label attendee;
    
    @FXML
    private Button closePost;

    @FXML
    private Button deletePost;

    @FXML
    private Button savePost;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private Label respondersID;
    
    /*FXML components on the scene - END
	 * 
	 * */
    
    //to capture post ID of the current posts
    //grabbed from the select * function
    String eventPostId;
    
    //Instance variable for table name and database name
    final String DB_NAME = "uniLinkDB";
    final String TABLE_NAME_Events = "EVENTS";
    
    //this function closes a certain post and take the user back to the MainWindow Button  closePost
    @FXML
    void closePost(ActionEvent event) {
    	//close post by id
    	closeEventPost(eventPostId);
    	//takes the user back to the main window 
    	revert(event);
    }
    
    @SuppressWarnings("unused")
	//this function is the algorithm to the close the posts 
    void closeEventPost(String id) {
    	
        //use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = " UPDATE " + TABLE_NAME_Events +  " SET STATUS = '"+ Status.CLOSE.toString() +"'" +
					" WHERE eventid LIKE '"+id+"' "  ;
			
			int result = stmt.executeUpdate(query);
			
			errorLabel.setText("Post Closed!");
		
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}
    	
    }
       
    //this function deletes a certain post and take the user back to the MainWindow Button deletePost
    @FXML
    void deletePost(ActionEvent event) {
    	
    	//deletes post by id
    	deleteEventPostById(eventPostId);	
    	//goes back to the main menu after action is done
    	revert(event);
    }
    

    //function to delete the post opened by the user 
    @SuppressWarnings("unused")
	void deleteEventPostById(String id) {
    	
    	//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = " DELETE FROM " + TABLE_NAME_Events + 
					" WHERE eventid LIKE  '"+id+"' ";
			
			int result = stmt.executeUpdate(query);
			
			errorLabel.setText("Post Deleted!");
		
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}
    	
    }

    //this functions take the user back to the main window, Button goBackBtn
    @FXML
    void revert(ActionEvent event) {
    	
    	//reference the log in window with FXML loader
    	FXMLLoader mainWindowRevert = new FXMLLoader(getClass().getResource("/view/MainWindowView.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) mainWindowRevert.load();
			//load the existing stage
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//create scene and load FXML page to scene
	    	Scene finalscene = new Scene(root);
	    	//load the scene onto the stage
	    	window.setScene(finalscene);
	    	//show the stage
	    	window.show();
	    	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    //this functions enables the editable form for the post
    @FXML
    void savePost(ActionEvent event) {
      
    	//unimplemented
    }

    //to capture more details of the posts  
    String eVenue;
    String eDate;
    int eCapacity;
    int eAttendee;
    
    //observable list of type event to capture more details of posts
    ObservableList<Event> posts = FXCollections.observableArrayList();
    
    //list view to take in posts from observable list to display on GUI
    @FXML
    ListView<Event> postsList = new ListView<Event>();
    
    //functions to populate data from database into event observable list
    public void populateEventDetails() {
    		
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME_Events;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
				
					 //eventId is retrieved here in result set for delete and close
					 //to capture post ID for delete and close functions above
					 eventPostId = resultSet.getString(1);
					 
					 //capture the specific details of the event post
					 eVenue = resultSet.getString(6);
					 eDate = resultSet.getString(7);
					 eCapacity = resultSet.getInt(8);
					 
					//constructor for extra event details
					Event eventPost = new Event(eVenue,eDate,eCapacity,eAttendee);
				
					//add posts to the observable list
					posts.add(eventPost);						
				}
				
			} catch (SQLException e) {
				errorLabel.setText(e.getMessage());
			}
	
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}	
    }
    
    //function to post data from event observable list to list view to show on screen
    public void eventPostsDetails() {
    
    	//get sale posts from database
    	populateEventDetails();
    	//populate list view with observable list items
    	postsList.setItems(posts);
    	//show extra details on label
    	venue.setText(eVenue);
    	date.setText(eDate);
    	capacity.setText(""+eCapacity);
    }
    
    //when the page open, the info will be loaded on the page
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		eventPostsDetails();
		
	}

}