package uniLinkDatabase;
import java.sql.Connection;
import java.sql.Statement;

public class DeleteRow {
	public static void main(String[] args) {
		final String DB_NAME = "uniLinkDB";
		final String TABLE_NAME = "REPLYevent";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "DELETE FROM " + TABLE_NAME + 
					" WHERE replyid LIKE '001'";
			
			int result = stmt.executeUpdate(query);
			
			System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
