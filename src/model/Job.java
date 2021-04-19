package model;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Job extends Post {
	
	//instance variables to create an Job post
	private double ProposedPrice;
	private double LowestOffer;
	private String status;
	        	
	//Instance variables to get unique post id
	private static int numberofposts = 00;
	private  String JpType;
	private  int postnumber;
	
	//Job Constructor
	public Job(String id, String title, String description, String creatorID, String Status,double ProposedPrice ,double LowestOffer){
		// TODO Auto-generated constructor stub
		super(id,title,description,creatorID,Status);
		this.ProposedPrice = ProposedPrice;
		this.LowestOffer = LowestOffer;
	}
	
	//special constructor to capture details for the MainWindow
	public Job(String id, String title, String description, String creatorID, String Status,double ProposedPrice ) {
		super(id,title,description,creatorID,Status);
	}
	
	//special constructor to capture details for the Post Detail window
	public Job(double ProposedPrice) {
		this.ProposedPrice = ProposedPrice;
	}
	
	//Empty constructor
	public Job() {
		
	}
	
	//constructor for unique post id
	public Job(String JpType) {
		super();
		this.JpType = JpType;
		numberofposts += 1;
		postnumber = numberofposts;
	}
	
	//methods for creating and getting unique ID - START
	public  String getJPType() {
		return JpType;
	}
	public  int getJPostnumber() {
		return postnumber;
	}
	public String getJPostID() {
		
		String JPostID = getJPType() + getJPostnumber();
		return JPostID;	
	}
	
	public void setJPostID(String JpostID) {
		this.getJPostID();
	}
	//methods for creating and getting unique ID - END
	
	//methods to get post details
	public double getProposedPrice() {
		return ProposedPrice;
	}
	
	public void setProposedPrice(double proposedprice) {
		this.ProposedPrice = proposedprice;
	}
	
	public double getLowestoffer() {
		return LowestOffer;
	}
	
	public void setLowestoffer(double lowestoffer) {
		this.LowestOffer = lowestoffer;
	}
	
	//get unique post details
	public String getPostDetails() {
		return JpType;
		
	}
	
	//methods to capture reply details - START
	public String getreplyid() {
		
		String replyid = getJPostID();
	
		return replyid;
	}

	public String getStatus() {
		return status;
	}
	public String setStatus(Status status) {
		return this.status = status.toString();
	}
	//methods to capture reply details - END
	
	
    //Overridden methods to handle and get reply
	@Override
	public boolean handleReply() {
		// TODO Auto-generated method stub
		
		//this function loads the reply window for a job post
		replyWindow();
		
		return true;	
	}
	
	//this function loads the reply window to reply to a job post
	void replyWindow() {
		//reference to the developer-info.fxml page
    	FXMLLoader confirmView = new FXMLLoader(getClass().getResource("../view/JobReply.FXML"));
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
	public boolean getReplyDetails() {
		// TODO Auto-generated method stub
		return false;
	}
	

	

	
	

}
