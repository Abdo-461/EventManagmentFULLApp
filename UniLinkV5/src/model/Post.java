package model;

public abstract class Post{

	//Instance variables	
	private String id;
	private String title;
	private String description;
	private String creatorID;
	private String status;
	
	//Constructor	
	public Post(String id, String title, String description , String creatorID , String status ) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.creatorID = creatorID;
		this.setStatus(Status.OPEN);
			
	}

	//Empty Constructor to call the super() constructor in sub classes
	public Post() {
		// TODO Auto-generated constructor stub
	}

	//method to get event details
	public String getPostID() {
		return id;
	}	
	
	public void setPostID(String id) {
		this.id = id;
	}
	
	public String getCreatorID() {
		return creatorID;
	}
	
	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}
	
	public String getPostStatus() {
		return getStatus();
	}
	
	public void setPostStatus(Status status) {
		this.setStatus(status);
	}
	
	public String getStatus() {
		return status;
	}

	public String setStatus(Status status) {
		return this.status = status.toString();
	}
	
	public String getPostTitle() {
		return title;
	}
	
	public void setPostTitle(String title) {
		this.title = title;
	}
	
	public String getPostDescription() {
		return description;
		
	}
	public void setPostDescription(String description) {
		this.description = description;
		
	}

	//Abstract methods to handle reply on posts
	//Implemented differently in each subclass	
	public abstract boolean handleReply();
	
	public abstract boolean getReplyDetails();
	
	
	
}
