package model;


public class Reply{
	
	//Instance variables of reply
	private String ReplyID;
	private double ReplyValue;
	private String ResponderID;
	
	//constructor
	public Reply(String ReplyID, double ReplyValue , String ResponderID) {
		
		this.ReplyID = ReplyID;
		this.ReplyValue = ReplyValue;
		this.ResponderID = ResponderID;
		
	}
	
	public Reply() {
		
	}
	
	//Instance variables to create unique post ID	
		private static int numberofposts = 00;
		private  String replyNum;
		private  int postnumber;
	
	
	public Reply(String replyNum) {
		super();
		this.replyNum = replyNum;
		numberofposts += 1;
		postnumber = numberofposts;	
	}
	
	public  String getReplyNum() {
		return replyNum;
	}
	
	public  int getEPostnumber() {
		return postnumber;
	}
	
      public String getRPostID() {
		
		String RPostID = getReplyNum() + getEPostnumber();
		
		return RPostID;	
	}
	public void setRPostID(String RpostID) {
		this.getRPostID();
	}
	
	//Accessor methods of reply
	public String getReplyID() {
		return ReplyID;
	}
	
	public void setReplyID(String replyID) {
		this.ReplyID = replyID;
	}
	
	public double getReplyValue() {
		return ReplyValue;
	}
	public void setReplyValue(double replyValue) {
		this.ReplyValue = replyValue;
	}
	public String getResponderID() {
		return ResponderID;
	}
	public void setResponderID(String responderID) {
		this.ResponderID = responderID;
	}

	
	
	
}
