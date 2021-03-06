package uniLinkDatabase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQueryReply {
	public static void main(String[] args) {
		final String DB_NAME = "uniLinkDB";
		final String TABLE_NAME = "REPLYsale";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
					System.out.printf("Reply Id : %s | Value: %s | Responder: %s\n ",
							resultSet.getString(1),
							resultSet.getString(3), 
							resultSet.getString(4));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
