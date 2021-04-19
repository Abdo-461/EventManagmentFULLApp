package uniLinkDatabase;
import java.sql.Connection;
import java.sql.Statement;

public class UpdateQuery {
	public static void main(String[] args) {
		final String DB_NAME = "uniLinkDB";
		final String TABLE_NAME = "EVENTS";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "UPDATE " + TABLE_NAME +
					" SET status = 'OPEN'" +
					" WHERE eventid LIKE 'EVE013'";
			
			int result = stmt.executeUpdate(query);
			
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
