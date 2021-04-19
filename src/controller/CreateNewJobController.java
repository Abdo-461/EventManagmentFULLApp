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
import model.Job;
import model.Status;
import uniLinkDatabase.UniLinkDB;

public class CreateNewJobController implements Initializable  {
	
	/*FXML components on the scene - START
	 * 
	 * */
	@FXML
    private Button createJobBtn;

	@FXML
    private Button gobackBtn;
	
	@FXML
    private TextField jobTitle;

    @FXML
    private TextField jobDescription;

    @FXML
    private TextField jobProposedPrice;
    
    @FXML
    private Label invalidInput;
    
    @FXML
    private ImageView jobImage;
    
    /*FXML components on the scene - END
	 * 
	 * */
    
    //instance variables to capture user elements
    String id;
    String title;
    String description;
    String creatorid;
    String status;
    double proposedprice;

    //function to let user upload image
    @FXML
    void uploadImage() {
    	
    	FileChooser Image = new FileChooser();
    	File selectedImage = Image.showOpenDialog(null);
    	Image image = new Image(selectedImage.toURI().toString());
    	
    	if(selectedImage !=null) {
    		jobImage.setImage(image);
    	}
    }
	
	//this function creates a job post
    @FXML
    void createJob(ActionEvent event) {
    	
    	inserIntoDatabase();
    }

    
    //this function inserts user input into the database
    @FXML
    void inserIntoDatabase() {
    	//this functions injects user input into the database
    	databaseInsert();	
    }
    
    //this functions handles the insert query to the database
    @SuppressWarnings("unused")
	void databaseInsert() {
    	
    	//to create new id starting from JOB00 and increments as a user creates a post
    	Job jobs = new Job("JOB00");
    	
	    try {	
	    	 id = jobs.getJPostID();
	    	 title = jobTitle.getText();
	    	 description = jobDescription.getText();
	    	 creatorid = jobs.getCreatorID();
	    	 status = Status.OPEN.toString();
	    	 proposedprice = Integer.parseInt(jobProposedPrice.getText());
	    	
	    }catch(Exception c) {
	    	invalidInput.setText("Invalid Input, Please enter valid input!");
	    }
    
    	//Initialize database created
		final String DB_NAME = "uniLinkDB";
		//Initialize tables in database, one for each kind of posts and one for replies
		final String TABLE_NAME_JOB = "JOBS";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);){
			@SuppressWarnings("unused")
			Job job = this.createJobObj(id,title, description,creatorid,status,proposedprice);
			String query = "INSERT INTO " + TABLE_NAME_JOB +
					" VALUES (?,?,?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, id);
			stmt.setString(2, title);
			stmt.setString(3, description);
			stmt.setString(4, "In progress");
			stmt.setString(5, status);
			stmt.setDouble(6, proposedprice);
			
			int rs = stmt.executeUpdate();
			con.commit();
			con.close();
			
			//message to show when the record is created
			invalidInput.setText("Event recorded in database succesfully!");
			
			}catch(Exception e) {
			    invalidInput.setText(e.getMessage());
		}
    }
    
   //this function creates an event object using the model Job
   public Job createJobObj(String jobID ,String jobTitle, String jobDescription ,String creatorID, String status,double proposedprice){
    	
	    //create an object of job
    	Job job = new Job();
    	//get elements of the post by the model functions
    	job.setJPostID(jobID);
    	job.setPostTitle(jobTitle);
    	job.setPostDescription(jobDescription);
    	job.setCreatorID(creatorID);
    	job.setProposedPrice(proposedprice);
    	
    	return job;
    }
   
   
    //a function for the back button to return to the main menu
    @FXML
    void revert(ActionEvent event) throws IOException {
    	
    	//create a reference to the FXML page
    	FXMLLoader mainWindowRevert = new FXMLLoader(getClass().getResource("/view/MainWindowView.fxml"));
    	//load the FXML page
    	Parent root = (Parent) mainWindowRevert.load();
    	//load the existing stage
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	//create new scene and load FXML page onto scene
    	Scene mainwindowscene = new Scene(root);
    	//put the scene onto the existing
    	window.setScene(mainwindowscene);
    	//show the scene on the stage
    	window.show();
    } 
    
    
    //this function opens a stage to confirm posts inserted into database
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
