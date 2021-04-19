package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Reply;
import uniLinkDatabase.UniLinkDB;

public class ReplyToSaleController {
	
	/*Label elements on the scene - START*/
	@FXML
	private TextField valueField;
	
	@FXML
	private Button submitReply;
	
	@FXML
	private Text confirmation;
	
	/*Label elements on the scene - END*/
	
	//instance variables to capture user input
	String replyId;
	String postID;
	double replyValue;
	String responderID;
	
	//function to reply to a sale posts Button submitReply
	@FXML
	void replyToSale() {
		
		databaseInsert();
		
		confirmation.setText("Your reply was recorded!");
	}
	
	//this functions handles the insert query to the database
    @SuppressWarnings("unused")
	void databaseInsert() {
    	
    	Reply reply = new Reply("00");
    	try {
       	 
    		replyId = reply.getRPostID();
    		postID = "";
    		replyValue = Integer.parseInt(valueField.getText());
    		responderID = "In progress";
    		
       }catch(Exception e) {
       	confirmation.setText("Invalid Input, Please enter valid input");
       }
    	
    /*inserts reply information to the reply table
     * 
     * */
	    //Initialize database created
		final String DB_NAME = "uniLinkDB";
		//Initialize tables in database, one for each kind of posts and one for replies
		final String TABLE_NAME_Reply = "REPLYsale";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);){
			@SuppressWarnings("unused")
			Reply replyS = this.createReplyObj(replyId,postID,replyValue, responderID);
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
			confirmation.setText(e.getMessage());
	 }
   }
	
	
   //this function creates an event object using the model Job
   public Reply createReplyObj(String replyId ,String postID,double replyValue, String responderId){
    	
	    //create an object of reply
    	Reply reply = new Reply();
    	//get elements of the post by the model functions
    	reply.setReplyID(replyId);
    	reply.setReplyID(postID);
    	reply.setReplyValue(replyValue);
    	reply.setResponderID(responderId);
    	
    	return reply;
    }

}
