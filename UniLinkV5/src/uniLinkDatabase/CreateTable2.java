package uniLinkDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable2 {
	public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "uniLinkDB";
		//final String TABLE_EVENT = "EVENTS";
		//final String TABLE_SALE = "SALES";
		//final String TABLE_JOB = "JOBS";
		final String TABLE_REPLY = "REPLYjob";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
//			int result = stmt.executeUpdate("CREATE TABLE events ("
//										+ "eventid VARCHAR(255) NOT NULL,"
//										+ "title VARCHAR(255) NOT NULL," 
//										+ "description VARCHAR(255) NOT NULL,"
//										+ "creatorid VARCHAR(255) NOT NULL,"
//										+ "status VARCHAR(255) NOT NULL,"
//										+ "venue VARCHAR(255) NOT NULL,"
//										+ "date DATE NOT NULL,"
//										+ "capacity INT NOT NULL,"
//										+ "PRIMARY KEY (eventid))");
//			int result2 = stmt.executeUpdate("CREATE TABLE sales ("
//										+ "saleid VARCHAR(255) NOT NULL,"
//										+ "title VARCHAR(255) NOT NULL," 
//										+ "description VARCHAR(255) NOT NULL,"
//										+ "creatorid VARCHAR(255) NOT NULL,"
//										+ "status VARCHAR(255) NOT NULL,"
//										+ "askingprice INT NOT NULL,"
//										+ "minimumraise INT NOT NULL,"
//										+ "PRIMARY KEY (saleid))");
//			int result3 = stmt.executeUpdate("CREATE TABLE jobs ("
//										+ "jobid VARCHAR(255) NOT NULL,"
//										+ "title VARCHAR(255) NOT NULL," 
//										+ "description VARCHAR(255) NOT NULL,"
//										+ "creatorid VARCHAR(255) NOT NULL,"
//										+ "status VARCHAR(255) NOT NULL,"
//										+ "proposedprice INT NOT NULL,"
//										+ "PRIMARY KEY (jobid))");
			int result4 = stmt.executeUpdate("CREATE TABLE REPLYjob ("
										+ "replyid VARCHAR(255) NOT NULL,"
										+ "postid VARCHAR(255) NOT NULL,"
										+ "replyvalue INT NOT NULL," 
										+ "responderid VARCHAR(255) NOT NULL,"
										+ "PRIMARY KEY (replyid))");
			
			//storage-&& result2 == 0 && result3 == 0 && result4 == 0 -//storage
			if(result4 == 0 ) {
				//System.out.println("Table " + TABLE_EVENT + " has been created successfully");
				//System.out.println("Table " + TABLE_SALE + " has been created successfully");
				//System.out.println("Table " + TABLE_JOB + " has been created successfully");
				System.out.println("Table " + TABLE_REPLY + " has been created successfully");
			} else {
				//System.out.println("Table " + TABLE_EVENT + " is not created");
				//System.out.println("Table " + TABLE_SALE + " is not created");
				//System.out.println("Table " + TABLE_JOB + " is not created");
				System.out.println("Table " + TABLE_REPLY + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}