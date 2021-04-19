package controller;

/**
 * Assignment 2 , UniLinkGui 
 * Author : Abdulrahman Ali
 * Student ID : s3763122
 * 
 * All copy rights reserved */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Event;
import model.Job;
import model.Post;
import model.Sale;
import model.Status;
import uniLinkDatabase.UniLinkDB;
import javafx.scene.Node;
import javafx.scene.Scene;

 public class MainWindowControllerr<T> implements Initializable  {

	//FXML elements present in the GUI declared in the controller
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button newEventButton;

    @FXML
    private Button newSaleButton;

    @FXML
    private Button newJobButton;

    @FXML
    private Button logOutBtn;
    
    @FXML
    private Label displayStudentId;
    
    @FXML
    private Label eventId;
    
    @FXML
    private Label eventTitle;
    
    @FXML
    private Label eventDescription;
    
    @FXML
    private Label eventCreatorid;
    
    @FXML
    private Label eventStatus;
    
    @FXML
    private Label venue;

    @FXML
    private Label date;

    @FXML
    private Label capacity;
    
    @FXML
    private Label saleId;
    
    @FXML
    private Label saleTitle;
    
    @FXML
    private Label saleDescription;
    
    @FXML
    private Label saleCreatorId;
    
    @FXML
    private Label saleStatus;
    
    @FXML
    private Label highestOffer;

    @FXML
    private Label minimumRaise;
    
    @FXML
    private Label jobId;
    
    @FXML 
    private Label jobTitle;
    
    @FXML
    private Label jobDescription;
    
    @FXML
    private Label jobCreatorId;
    
    @FXML
    private Label jobStatus;
    
    @FXML
    private Label proposedPrice;
    
    @FXML
    private ScrollPane postPane;
    
    @FXML
    private Button moreEventDetails;
    
    @FXML
    private Button moreSaleDetails;
    
    @FXML
    private Button moreJobDetails;
    
    @FXML
    private MenuItem developerInfo;
    
    @FXML
    private MenuItem exportData;
    
    @FXML
    private MenuItem importData;
    
    @FXML
    private HBox eventBox;
    
    @FXML
    private HBox saleBox;
    
    @FXML
    private HBox jobBox;
    
    @FXML
    private Button eventReply;
    
    @FXML
    private Button saleReply;
    
    @FXML
    private Button jobReply;
    
    @FXML
    private MenuItem eventPosts;
    
    @FXML
    private MenuItem salePosts;
    
    @FXML
    private MenuItem jobPosts;
    
    @FXML
    private MenuItem allPosts;
    
    @FXML
    private VBox eventDetailsP;
    
    @FXML
    private VBox eventDetailsS;
    
    @FXML
    private ImageView eventImage;
    
    @FXML
    private HBox eventButtons;
    
    @FXML
    private Label alertsP;
    
    @FXML
    private MenuItem openPosts;
    
    @FXML
    private MenuItem closePosts;
    
    @FXML
    private MenuItem allPostStatus;
    
     
    //instance variables for elements to capture user input - EVENT
    String eId;
    String eTitle;
    String eDescription;
    String eCreatorid;
    String eStatus;
    String eVenue;
    String eDate;
    int eCapacity;
    int eAttendee;
    
  //instance variables for elements to capture user input - SALE
    String sId;
    String sTitle;
    String sDescription;
    String sCreatorid;
    String sStatus;
    double minimumraise;
    
  //instance variables for elements to capture user input - JOB
    String jId;
    String jTitle;
    String jDescription;
    String jCreatorid;
    String jStatus;
    int jpropsedPrice;
    double lowestPrice;
    
    //this function shows all the open posts
    @FXML
    void showOpenPosts(ActionEvent event) {
    	
    	openEventPosts();
    	openSalePosts();
    	openJobPosts();		
    }
    
    //this function shows all the close posts
    @FXML
    void showClosePosts(ActionEvent event) {
    	
    	closeEventPosts();
    	closeSalePosts();
    	closeJobPosts();	
    }
    
    //this function to show all 2 status of posts
    @FXML
    void showAllPostStatus(ActionEvent event) {
    	
    	eventBox.setVisible(true);
    	saleBox.setVisible(true);
    	jobBox.setVisible(true);
    }
    
    /*3 functions to show the 3 types of posts OPEN
     * logic does not make sense , couldnt understand what is the problem*/
   public void openEventPosts() {
    	
    	if(eStatus == Status.OPEN.toString()) {
    		
    		eventBox.setVisible(false);
    	}
    	else {
    		eventBox.setVisible(true);
    	}
    }
    
    public void openSalePosts() {
    	
    	if(sStatus == Status.OPEN.toString()) {
    		
    		saleBox.setVisible(false);
    	}
    	else {
    		saleBox.setVisible(true);
    	}
    }

	public void openJobPosts() {
		
		if(jStatus == Status.OPEN.toString()) {
			
			jobBox.setVisible(true);
		}
		else {
			jobBox.setVisible(false);
		}
	}
	
	/*3 functions to show the 3 types of posts CLOSE
     * logic does not make sense , couldnt understand what is the problem*/
	public void closeEventPosts() {
	    	
	   if(eStatus == Status.CLOSE.toString()) {
	    		
	    		eventBox.setVisible(true);
	    	}
	    	else {
	    		eventBox.setVisible(false);
	    	}
	    }
	    
	public void closeSalePosts() {
	    	
	  if(sStatus == Status.CLOSE.toString()) {
	    		
	       saleBox.setVisible(true);
	    }
	  else {
	       saleBox.setVisible(false);
	    	}
	    }

    public void closeJobPosts() {
		
    	if(jStatus == Status.CLOSE.toString()) {
			
			jobBox.setVisible(false);
		}
		else {
			jobBox.setVisible(true);
		}
	}
	    
  
    //an observable list to take new 3 different kinds of posts
    ObservableList<Post> posts = FXCollections.observableArrayList();
   
    //a list view to display posts in the GUI added in the Observable List
    @FXML
    ListView<Post> postsList = new ListView<Post>();
    
    
    //this function exports all data into myFiles
    @FXML
    void exportAllData() throws IOException {
    	
    	File posts = new File("myFiles/export_data.txt");
    	PrintWriter output = new PrintWriter(posts);
    	
    	try {
			
			//write event data to posts.txt under myFiles
			output.write(eId);		
			output.write(eTitle);		    
			output.write(eDescription);			
    		output.write(eStatus);			
			output.write(eVenue);			
			output.write(eDate);
			output.write(eCapacity);
			
			//write sale data to posts.txt under myFiles
			output.write(sId);
			output.write(sTitle);
			output.write(sDescription);
			output.write(sStatus);
			output.write((int) minimumraise);
			
			//write sale data to posts.txt under myFiles
			output.write(jId);
			output.write(jTitle);
			output.write(jDescription);
			output.write(jStatus);
			output.write((int) jpropsedPrice);
			
			alertsP.setText("Data Exported succesfully");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	output.close();
    }
    
    //this function imports all data from export_data.txt
    @FXML
    void importAllData() throws IOException {
    	
    	 File posts = new File("myFiles/export_data.txt");
    	 BufferedReader readFile = new BufferedReader(new FileReader(posts));
     	
     	try {
 				readFile.read();		
 				alertsP.setText("Data imported succesfully");
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	readFile.close();
     	
    }
    
    /*sorting out posts on the Main Window
     * 
     * For Event*/
    @FXML
    void showEventPosts(ActionEvent event) {
    	
	    //function to populate window with event ONLY information from database
		//eventPostsList();  
		eventBox.setVisible(true);
		//hides the other 2 types of posts
		saleBox.setVisible(false);
		jobBox.setVisible(false);
    }
      
    /*
     * For Sale*/
   @FXML
   void showSalePosts(ActionEvent event) {
	   
	   //function to populate window with sale information ONLY from database
	   //salePostsList();
	   saleBox.setVisible(true);
	   //hides the other 2 types of posts
	   eventBox.setVisible(false);
	   jobBox.setVisible(false);
   }
   
   /*
    * For Job*/
   @FXML
   void showJobPosts(ActionEvent event){
	   
	 //function to populate window with job information ONLY from database
	   // jobPostsList();
	 jobBox.setVisible(true);
	 //hides the other 2 types of posts
	 eventBox.setVisible(false);
	 saleBox.setVisible(false);
   }
   
   /*
    * For All posts*/
   @FXML
   void showAllPosts(ActionEvent event) {
	   
	   eventBox.setVisible(true);
	   saleBox.setVisible(true);
	   jobBox.setVisible(true);
		 
   }
   //this function logs the user out and saves information into database - user returns back to LogInWindow
    @FXML
    void logOut(ActionEvent event)  {
    	
    	//reference the log in window with FXML loader
    	FXMLLoader logout = new FXMLLoader(getClass().getResource("../view/LoginWindow.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) logout.load();
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
    //this functions open the scene to create new event
    @FXML
    void openCreateEventWindow(ActionEvent event)  {
    	
    	//reference the log in window with FXML loader
    	FXMLLoader createNewEvent = new FXMLLoader(getClass().getResource("../view/CreateNewEvent.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) createNewEvent.load();
			//load the existing stage
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//create scene and load FXML page to scene
	    	Scene eventscene = new Scene(root);
	    	//load the scene onto the stage
	    	window.setScene(eventscene);
	    	//show the stage
	    	window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //this functions open the scene to create new job
    @FXML
    void openCreateJobWindow(ActionEvent event)  {

    	//reference the log in window with FXML loader
    	FXMLLoader createNewJob = new FXMLLoader(getClass().getResource("../view/CreateNewJob.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) createNewJob.load();
			//load the existing stage
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//create scene and load FXML page to scene
	    	Scene eventscene = new Scene(root);
	    	//load the scene onto the stage
	    	window.setScene(eventscene);
	    	//show the stage
	    	window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    //this functions open the scene to create new sale
    @FXML
    void openCreateSaleWindow(ActionEvent event)  {
    	
    	//reference the log in window with FXML loader
    	FXMLLoader createNewSale = new FXMLLoader(getClass().getResource("../view/CreateNewSale.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) createNewSale.load();
			//load the existing stage
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//create scene and load FXML page to scene
	    	Scene eventscene = new Scene(root);
	    	//load the scene onto the stage
	    	window.setScene(eventscene);
	    	//show the stage
	    	window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //this function opens more details for the event post
    @FXML
    void showEventDetails(ActionEvent event) {
    	
    	//reference the log in window with FXML loader
    	FXMLLoader eventDetails = new FXMLLoader(getClass().getResource("../view/EventDetails.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) eventDetails.load();
			//load the existing stage
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//create scene and load FXML page to scene
	    	Scene eventscene = new Scene(root);
	    	//load the scene onto the stage
	    	window.setScene(eventscene);
	    	//show the stage
	    	window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    //this function opens more details for the sale post
    @FXML
    void showSaleDetails(ActionEvent event) {
    	
    	//reference the log in window with FXML loader
    	FXMLLoader saleDetails = new FXMLLoader(getClass().getResource("../view/SaleDetails.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) saleDetails.load();
			//load the existing stage
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//create scene and load FXML page to scene
	    	Scene eventscene = new Scene(root);
	    	//load the scene onto the stage
	    	window.setScene(eventscene);
	    	//show the stage
	    	window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    //this function opens more details for the job post
    @FXML
    void showJobDetails(ActionEvent event) {
    	
    	//reference the JobDetails.fxml
    	FXMLLoader jobDetails = new FXMLLoader(getClass().getResource("../view/JobDetails.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) jobDetails.load();
			//load the existing stage
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//create scene and load FXML page to scene
	    	Scene eventscene = new Scene(root);
	    	//load the scene onto the stage
	    	window.setScene(eventscene);
	    	//show the stage
	    	window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    //this functions opens a stage to show the developer-info
    @FXML
    void showDeveloperInfo(ActionEvent event) {
    	
    	//reference to the developer-info.fxml page
    	FXMLLoader developerinfo = new FXMLLoader(getClass().getResource("../view/Developer-info.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) developerinfo.load();
			//create a new stage for Developer-info.fxml
		    Stage developerinfoStage = new Stage();
			//set the scene in the new stage
		    developerinfoStage.setScene(new Scene(root));
		    //show stage
		    developerinfoStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    //this function replies to an EVENTS post
    @FXML
    void replyToEvent(ActionEvent event)  {
    	
    	Post eventReply = new Event();
		eventReply.handleReply();
    	confirm(event);
    }
    
    //this function replies to SALES post
    @FXML
    void replyToSale(ActionEvent event) {
    	
    	Post sale = new Sale();
    	sale.handleReply();
    	
    }
    
    //this function replies to a JOB post
    @FXML
    void replyToJob(ActionEvent event) {
    	
    	Post job = new Job();
    	job.handleReply();
    }
    
    
    //this function open a small stage to confirm that a user's reply has been recorded successfully into the database
    @FXML
    void confirm(ActionEvent event) {
    	
    	//reference to the developer-info.fxml page
    	FXMLLoader confirmView = new FXMLLoader(getClass().getResource("../view/EventReply.FXML"));
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
   
    /*Setting special background layouts for the HBoxs on the MainWindow
     * 
     * */
    //sets background color  to --LightCyan--  for the HBox that contains event posts
    public void eventHboxColor() {
    	BackgroundFill background_fill = new BackgroundFill(Color.LIGHTCYAN,  
                CornerRadii.EMPTY, Insets.EMPTY); 

    		// create Background 
    		Background background = new Background(background_fill);
		
    		eventBox.setBackground(background);
    	}
    
    //sets background color to --LightPink-- for the HBOX that contains sale posts
    public void saleHboxColor() {
    	BackgroundFill background_fill = new BackgroundFill(Color.LIGHTPINK,  
                CornerRadii.EMPTY, Insets.EMPTY); 

    		// create Background 
    		Background background = new Background(background_fill);
		
    		saleBox.setBackground(background);	
    	}
    
    //sets background color to --LightYellow-- for the HBOX that contains job posts
    public void jobHboxColor() {
    	BackgroundFill background_fill = new BackgroundFill(Color.LIGHTYELLOW,  
                CornerRadii.EMPTY, Insets.EMPTY); 

    		// create Background 
    		Background background = new Background(background_fill);
		
    		jobBox.setBackground(background);
    	}
    
    
  /*
   * Populating List view with events ---- START*/  
    //Instance variable for table name and database name
    final String DB_NAME = "uniLinkDB";
    final String TABLE_NAME_Events = "EVENTS";
    
    //functions to populate data from database into  observable list
	public void populateEventList() {
    		
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME_Events;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
				
					//capturing the data from the database into string variables
					 eId = resultSet.getString(1);
					 eTitle = resultSet.getString(2);
					 eDescription = resultSet.getString(3);
					 eCreatorid = resultSet.getString(4);
					 eStatus = resultSet.getString(5);
					 eVenue = resultSet.getString(6);
					 eDate = resultSet.getString(7);
					 eCapacity = resultSet.getInt(8);
					 
					//new objects to create event posts 
					Post eventPost = new Event(eId,eTitle, eDescription,eCreatorid,eStatus,eVenue,eDate,eCapacity,eAttendee);
					//add several objects to an observable lists	
	  				posts.add(eventPost);	
				}
				
			} catch (SQLException e) {
				alertsP.setText(e.getMessage());
				
			}
	
		} catch (Exception e) {
			alertsP.setText(e.getMessage());
		}	
    }
    
    //function to post data from event observable list to list view to show on screen
    public void eventPostsList() {
    
    	//running the function to fetch the data and insert it into an observable list
    	populateEventList();
    	//setting the observable list into the list view - postsList
    	postsList.setItems(posts);
  
    	//setting the string variables into Labels
    	eventId.setText(eId);
    	eventTitle.setText(eTitle);
    	eventDescription.setText(eDescription);
    	eventCreatorid.setText(eCreatorid);
    	eventStatus.setText(eStatus);
    	venue.setText(eVenue);
    	date.setText(eDate);
    	capacity.setText(""+eCapacity);
    		
    }   
 /*
  * Populating List view with event post ---- END*/
    
    
  /*
   * Populating List view with sale post ---- START*/
    
    final String TABLE_NAME_Sale = "SALES";
    
    //functions to populate data from database into observable list
  	public void populateSaleList() {
      		
  		try (Connection con = UniLinkDB.getConnection(DB_NAME);
  				Statement stmt = con.createStatement();
  		) {
  			String query = "SELECT * FROM " + TABLE_NAME_Sale;
  			
  			try (ResultSet resultSet = stmt.executeQuery(query)) {
  				while(resultSet.next()) {
  				
  				     //capturing the data from the database into string variables
  					 sId = resultSet.getString(1);
  					 sTitle = resultSet.getString(2);
  					 sDescription = resultSet.getString(3);
  					 sCreatorid = resultSet.getString(4);
  					 sStatus = resultSet.getString(5);
  					 minimumraise = resultSet.getInt(7);

  					//new objects to create event posts  
  					Post salePost = new Sale(sId,sTitle, sDescription,sCreatorid,sStatus,1.0,1.0,minimumraise);
  				    //add several objects to an observable lists
  					posts.add(salePost);		
  				}
  			} catch (SQLException e) {
  				alertsP.setText(e.getMessage());
  			}
  	
  		} catch (Exception e) {
  			alertsP.setText(e.getMessage());
  		}	
      }
    
    //function to post data from event observable list to list view to show on screen
      public void salePostsList() {
      
    	//running the function to fetch the data and insert it into an observable list  
      	populateSaleList();
        //setting the observable list into the list view - postsList
      	postsList.setItems(posts);
        //setting the string variables into Labels
      	saleId.setText(sId);
      	saleTitle.setText(sTitle);
      	saleDescription.setText(sDescription);
      	saleCreatorId.setText(sCreatorid);
      	saleStatus.setText(sStatus);
    	minimumRaise.setText(""+minimumraise);
      }
    
      /*
       * Populating List View with sale post ---- END*/
     
    /*
     * Populating List View with job post ---- START*/
      
    final String TABLE_NAME_Job = "Jobs";
    
    //functions to populate data from database into observable list
  	public void populateJobList() {
      		
  		try (Connection con = UniLinkDB.getConnection(DB_NAME);
  				Statement stmt = con.createStatement();
  		) {
  			String query = "SELECT * FROM " + TABLE_NAME_Job;
  			
  			try (ResultSet resultSet = stmt.executeQuery(query)) {
  				while(resultSet.next()) {
  					
  				     //capturing the data from the database into string variables
  					 jId = resultSet.getString(1);
  					 jTitle = resultSet.getString(2);
  					 jDescription = resultSet.getString(3);
  					 jCreatorid = resultSet.getString(4);
  					 jStatus = resultSet.getString(5);
  					 jpropsedPrice = resultSet.getInt(6);

  					//new objects to create event posts  
  					Post jobPost = new Job(jId,jTitle,jDescription,jCreatorid,jStatus,jpropsedPrice);
  				    //add several objects to an observable lists
  					posts.add(jobPost);		
  				}
  				
  			} catch (SQLException e) {
  				alertsP.setText(e.getMessage());
  			}
  	
  		} catch (Exception e) {
  			alertsP.setText(e.getMessage());
  		}	
      }
    
      //function to post data from event observable list to list view to show on screen
      public void jobPostsList() {
      
    	//running the function to fetch the data and insert it into an observable list  
      	populateJobList();
        //setting the observable list into the list view - postsList
      	postsList.setItems(posts);
        //setting the string variables into Labels
      	jobId.setText(jId);
      	jobTitle.setText(jTitle);
      	jobDescription.setText(jDescription);
      	jobCreatorId.setText(jCreatorid);
      	jobStatus.setText(jStatus);
      	proposedPrice.setText(""+jpropsedPrice);
      }
      /*
       * Populating List View with event post ---- END*/
    
      //this functions displays the id of the student logged in
      public void displayStudentId(String studentId) {
      	//sets the student id entered
      	displayStudentId.setText(studentId);
      	}
      
    @Override  
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
		//function to fill HBox color for event
		eventHboxColor();
		//function to fill HBox color for sale
		saleHboxColor();
		//function to fill HBox color for job
		jobHboxColor();
		
		//function to populate window with event information from database
		eventPostsList();
		
		//function to populate window with sale information from database
		salePostsList();
		
		//function to populate window with sale information from database
		jobPostsList();
			
		// TODO Auto-generated method stub
		assert newEventButton != null : "fx:id=\"newEventButton\" was not injected: check your FXML file 'MainWindowView.fxml'.";
        assert newSaleButton != null : "fx:id=\"newSaleButton\" was not injected: check your FXML file 'MainWindowView.fxml'.";
        assert newJobButton != null : "fx:id=\"newJobButton\" was not injected: check your FXML file 'MainWindowView.fxml'.";
        assert logOutBtn != null : "fx:id=\"logOutBtn\" was not injected: check your FXML file 'MainWindowView.fxml'.";
		
		}
 

    }
 
	
		