package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import uniLinkDatabase.UniLinkDB;

public class Event extends Post{
	
	//instance variables to create an event post
	private String status;
	private String venue;
	private String date;
	private int capacity;
	private int attendees;
	private double attendeeCount;
	
	//Instance variables to create unique post ID	
	private static int numberofposts = 00;
	private  String EpType;
	private  int postnumber;
	
	//Event constructor
	public Event(String id,String title,String description,String creatorID,String status,String venue,String date ,int capacity ,int attendees){
		super(id, title,description,creatorID,status);
		// TODO Auto-generated constructor stub
		this.venue = venue;
		this.date = date;
		this.capacity = capacity;
		this.attendees = attendees;	
	}
	
	//Empty constructor
	public Event() {	
	  }
	
	//special constructor to capture details for the MainWindow
	public Event(String id,String title,String description,String creatorID,String status) {
		super(id, title,description,creatorID,status);
	  }
	
	//special constructor to capture details for the Post Detail window
	public Event(String venue,String date ,int capacity ,int attendees) {
		this.venue = venue;
		this.date = date;
		this.capacity = capacity;
		this.attendees = attendees;
	  }
	
	
	//special constructor to create unique post ID
	public Event(String EpType) {
		super();
		this.EpType = EpType;
		numberofposts += 1;
		postnumber = numberofposts;	
	 }
	
	//methods for creating and getting unique ID - START
	public  String getEPType() {
		return EpType;
	}
	public  int getEPostnumber() {
		return postnumber;
	}
	public String getEPostID() {
		
		String EPostID = getEPType() + getEPostnumber();
		
		return EPostID;	
	}
	public void setEPostID(String EpostID) {
		this.getEPostID();
	}
	
	//methods for creating and getting unique ID - END
	
	
	//methods get event details
	public String getEventVenue() {
		return venue;
	}
	public void setEventVenue(String venue) {
		this.venue = venue;
	}
	public String getEventDate() {
		return date;	
	}
	public void setEventDate(String date) {
		this.date = date;	
	}
	
	public int getAttendees() {
		return attendees;
	}
	
	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}
	
	public int getEventCapacity() {
		return capacity;
	}

	public void setEventCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public double getAttendeeCount() {	
		return attendeeCount;
		
	}
	public String getStatus() {
		return status;
	}
	public String setStatus(Status status) {
		return this.status = status.toString();
	}
	
	//instance variables to capture the reply to an Event posts
	String replyID;
	String postID;
	double replyValue;
	String responderID;
	
	//Overridden methods to handle and get reply
	@Override
	public boolean handleReply() {
		// TODO Auto-generated method stub	
		
		
		
		databaseInsert();
		
		return true;	
	}
	//this functions handles the insert query to the database
    @SuppressWarnings("unused")
	void databaseInsert() {
    	
    	//create reply object to create new reply id
		Reply reply = new Reply("00");
		//create an event object to get post id
		Event event = new Event();
		
		try {
			 replyID = reply.getRPostID();
	    	 postID = event.getEPostID();
	    	 replyValue = reply.getReplyValue();
	    	 responderID = " ";
	    	
	    }catch(Exception e) {
	    	System.out.print("wait a minute");	
	    }	
    			
	    //Initialize database created
		final String DB_NAME = "uniLinkDB";
		//Initialize tables in database, one for each kind of posts and one for replies
		final String TABLE_NAME_Reply = "REPLYevent";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);){
			@SuppressWarnings("unused")
			Reply replyObj = this.createReplyObj(replyID,postID,replyValue, responderID);
			String query = "INSERT INTO " + TABLE_NAME_Reply +
					" VALUES (?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, replyID);
			stmt.setString(2, postID);
			stmt.setDouble(3, 1);
			stmt.setString(4, responderID);
			
			int rs = stmt.executeUpdate();
	
			con.commit();
			con.close();
			
		} catch (Exception e) {
			e.getMessage();
		}
    }
	
	   //this function creates an event object using the model Job
	   public Reply createReplyObj(String replyId, String postId ,double replyValue, String responderId){
	    	
		    //create reply object
	    	Reply reply = new Reply();
	    	////get elements of the post by the model functions
	    	reply.setReplyID(replyId);
	    	reply.setReplyID(postId);
	    	reply.setReplyValue(replyValue);
	    	reply.setResponderID(responderId);
	    	
	    	return reply;
	    }

	   
	//an observable list to add reply
	ObservableList<Reply> replies = FXCollections.observableArrayList();
	//a list view to display posts in the GUI added in the Observable List
	@FXML
	ListView<Reply> eventReplyList = new ListView<Reply>();
	 //Instance variable for table name and database name
    final String DB_NAME = "uniLinkDB";
    final String TABLE_NAME_Replies = "REPLYevent";
	   
    //this function gets reply info from table
	 void populateReplyTable() {
		 try (Connection con = UniLinkDB.getConnection(DB_NAME);
					Statement stmt = con.createStatement();
			) {
				String query = "SELECT * FROM " + TABLE_NAME_Replies;
				
				try (ResultSet resultSet = stmt.executeQuery(query)) {
					while(resultSet.next()) {
					
						 replyID = resultSet.getString(1);
						 replyValue = resultSet.getInt(3);
						 responderID = resultSet.getString(4);
						
						Reply eventReply = new Reply(replyID,replyValue,responderID);
						
						replies.add(eventReply);							
					}
					
				} catch (SQLException e) {
					e.getMessage();
				}
		
			} catch (Exception e) {
				e.getMessage();
			}	
			
			return;
		}	
	 	
	//Overridden methods to get the reply details - INCOMPLETE 
	@Override
	public boolean getReplyDetails() {
		// TODO Auto-generated method stub
		
		populateReplyTable();
		
		eventReplyList.setItems(replies);
		
		//unimplemented 
		return true;
	}
}
