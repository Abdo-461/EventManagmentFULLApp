package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uniLinkDatabase.UniLinkDB;

public class Sale extends Post {
	
	//instance variables to create an sale post	
	private double askingPrice;
	private double highestOffer;
	private double minimumRaise;
	private String status;

	//instance variables to get unique post id
	private static int numberofposts = 00;
	private  String SpType;
	private  int postnumber;

		
	//Sale constructor	
	public Sale(String id, String title, String description, String creatorID, String status,double askingPrice ,double highestOffer ,double minimumRaise){
		super(id,title,description,creatorID,status);
		// TODO Auto-generated constructor stub
		this.askingPrice = askingPrice;
		this.highestOffer = highestOffer;
		this.minimumRaise = minimumRaise;
	}

	//special constructor to capture details for the MainWindow
	public Sale(String id, String title, String description, String creatorID, String status) {
		super(id,title,description,creatorID,status);
	}
	
	//special constructor to capture details for the PostDetailWindow
	public Sale(double askingPrice  ,double minimumRaise) {
		
		this.askingPrice = askingPrice;
		this.minimumRaise = minimumRaise;
	}
	
	//special constructor to create unique post ID
	public Sale(String SpType) {
		super();
		this.SpType = SpType;
		numberofposts += 1;
		postnumber = numberofposts;
	}
	
	//Empty constructor
	public Sale(){
		
	}
	
	//methods for creating and getting unique ID - START
	public  String getSPType() {
	return SpType;
	}
	public  int getSPostnumber() {
		return postnumber;
	}
	public String getSPostID() {
		
		String SPostID = getSPType() + getSPostnumber();
		return SPostID;
		
	}
	
	public void setSPostID(String SpostID) {
		this.getSPostID();
	}
	
	//methods for creating and getting unique ID - END
	
	//methods to get sale details
	public double getAskingPrice() {
		return askingPrice;
	}
	
	public void setAskingPrice(double askingprice) {
		this.askingPrice = askingprice;
	}
	
	public double getMinimum() {
		return minimumRaise;
	}
	
	public void setMinimum(double minimum) {
		this.minimumRaise = minimum;
	}
	
	public double getHighestoffer() {
		return highestOffer;
	}
	
	public void setHighestoffer(double highestoffer) {
		this.highestOffer = highestoffer;
	}
	
	public String getStatus() {
		return status;
	}
	public String setStatus(Status status) {
		return this.status = status.toString();
	}
	
	
	//get unique post details
	public String getPostDetails() {
		
		return null;
		
	}
	
	//Overridden methods to handle and get reply
	@Override
	public boolean handleReply() {
	// TODO Auto-generated method stub	
	
		replyWindow();
	
	
		
	return true;
	}
	
	//this function loads the reply window for sale
	void replyWindow() {
		//reference to the developer-info.fxml page
    	FXMLLoader confirmView = new FXMLLoader(getClass().getResource("../view/SaleReply.FXML"));
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
	
	
	//instance variables to capture reply details
	String replyId;
	String postID;
	double replyValue;
	String responderID;
	
	//this functions handles the insert query to the database
    @SuppressWarnings("unused")
	void databaseInsert() {
    	//Initialize database created
		final String DB_NAME = "uniLinkDB";
		//Initialize tables in database, one for each kind of posts and one for replies
		final String TABLE_NAME_Reply = "REPLYsale";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);){
			@SuppressWarnings("unused")
			Reply reply = this.createReplyObj(replyId,postID,replyValue, responderID);
			String query = "INSERT INTO " + TABLE_NAME_Reply +
					" VALUES (?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, replyId);
			stmt.setString(2, postID);
			stmt.setDouble(3,replyValue);
			stmt.setString(4, responderID);
			
			int rs = stmt.executeUpdate();
	
			con.commit();
			con.close();
			
			} catch (Exception e) {
				e.getMessage();
		}
    }
	
	
	//this function creates an event object using the model Job
    public Reply createReplyObj(String replyID ,String postID ,double replyValue, String responderId){
	    	
    	//create a reply obj to get reply id starting from 00
		Reply reply = new Reply("00");
		//capture reply details by methods in Reply class
		reply.setRPostID(replyID);
		reply.setReplyID(postID);
		reply.setReplyValue(replyValue);
		reply.setResponderID(responderId);
		
		return reply;
	}


	@Override
	public boolean getReplyDetails() {
		// TODO Auto-generated method stub
		return false;
	}

}
