package uniLinkDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQueryJob {
	public static void main(String[] args) {
		final String DB_NAME = "uniLinkDB";
		final String TABLE_NAME = "JOBS";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
					System.out.printf("Post Id: %s  | Title: %s | Description: %s | CreatorID: %s | Status: %s "
							+ "| Proposed Price: %d\n ",
							resultSet.getString("jobid"), resultSet.getString("title"), 
							resultSet.getString("description"), resultSet.getString("creatorid"), resultSet.getString("status"), 
							resultSet.getInt("proposedprice"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}