package uniLinkDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTable {
	public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "uniLinkDB";
		//final String TABLE_NAME_EVENT = "EVENT";
		//final String TABLE_NAME_SALE  = "SALES";
		//final String TABLE_NAME_JOB   = "JOB";
		final String TABLE_NAME_REPLIESS = "REPLYEVENT";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			//int result1 = stmt.executeUpdate("DROP TABLE event");
			//int result2 = stmt.executeUpdate("DROP TABLE sales");
			//int result3 = stmt.executeUpdate("DROP TABLE job");
			int result4 = stmt.executeUpdate("DROP TABLE REPLYEVENT");
			
			
		//-- storage--  && result2 == 0 && result3 ==0 && result4 ==0  --//
			
			if(result4 == 0 ) {
				//System.out.println("Table " + TABLE_NAME_EVENT + " has been deleted successfully");
				System.out.println("Table " + TABLE_NAME_REPLIESS + " has been deleted successfully");
				//System.out.println("Table " + TABLE_NAME_JOB + " has been deleted successfully");
				//System.out.println("Table " + TABLE_NAME_REPLIESS + " has been deleted successfully");
			} else {
				System.out.println("Table " + TABLE_NAME_REPLIESS + " was not deleted");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
