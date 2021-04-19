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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Job;
import model.Status;
import uniLinkDatabase.UniLinkDB;

public class JobDetailsController implements Initializable  {

	/*FXML components on the scene - START
	 * 
	 * */
    @FXML
    private Button goBackBtn;
  
    @FXML
    private Label proposedPrice;
    
    @FXML
    private Button closePost;

    @FXML
    private Button deletePost;

    @FXML
    private Button savePost;
    
    @FXML
    private Label errorLabel;
    /*FXML components on the scene - END
	 * 
	 * */
    
    //Instance variable for table name and database name
    final String DB_NAME = "uniLinkDB";
    final String TABLE_NAME_Job = "JOBS";
    
    //variable to catch current post ID
    String jobPostId;
    
    
      //function to close post by id
      @SuppressWarnings("unused")
	void closeJobPost(String id) {
    	
        //use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = " UPDATE " +TABLE_NAME_Job+  " SET STATUS = '"+ Status.CLOSE.toString() +"' " +
					" WHERE jobid LIKE '"+id+"' ";
			
			int result = stmt.executeUpdate(query);
			
			errorLabel.setText("Post Closed!");
		
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}
    	
    }
    
    
    //this function closes the post
    @FXML
    void closePost(ActionEvent event) {
    	//close post by id
    	closeJobPost(jobPostId);
    	//take ther user back to the main window
    	revert(event);
    }

    
     //function to delete job by id 
     @SuppressWarnings("unused")
	void deleteJobPostById(String id) {
    	
    	//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = " DELETE FROM " + TABLE_NAME_Job + 
					" WHERE jobid LIKE '"+id+"' " ;
			
			int result = stmt.executeUpdate(query);
			
			errorLabel.setText("Post Deleted!");
		
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}
    	
    }
    
    //this function deletes the post on GUI
    @FXML
    void deletePost(ActionEvent event) {
    	
    	//deletes post by id
    	deleteJobPostById(jobPostId);	
    	//goes back to the main menu after action is done
    	revert(event);

    }

    //this function takes the user back to the main window
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

    ////this functions enables the editable form for the post
    @FXML
    void savePost(ActionEvent event) {

    	//unimplemented
    }

    //capture details of job
    int jpropsedPrice;
    int lowestOffer;
    
    //Observable list to capture details of sale
    ObservableList<Job> posts = FXCollections.observableArrayList();
    
    //list view to populate the list with observable list components
    @FXML
    ListView<Job> postsList = new ListView<Job>();
    
  //functions to populate data from database into event observable list
    public void populateJobDetails() {
    		
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME_Job;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
				
					//capture current post id
					jobPostId = resultSet.getString(1);
					//capture extra details from sale table
					jpropsedPrice = resultSet.getInt(6);
					 
					//constructor for one job element
					Job jobPost = new Job(jpropsedPrice);
					
				  for(@SuppressWarnings("unused") Job j: posts)	
					posts.add(jobPost);						
				}
				
			} catch (SQLException e) {
				errorLabel.setText(e.getMessage());
			}
	
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}	
    }
    
    //function to post data from event observable list to list view to show on screen
    public void jobPostsDetails() {
    
    	//get Job posts from database
    	populateJobDetails();
    	//populate list view with observable list items
    	postsList.setItems(posts);
    
    	//show extra details on label
    	proposedPrice.setText(""+jpropsedPrice);
    	
    }
    
    //when the page open, the info will be loaded on the page
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		jobPostsDetails();
	}

}
