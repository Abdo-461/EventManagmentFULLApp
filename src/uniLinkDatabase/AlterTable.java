package uniLinkDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterTable {
public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "uniLinkDB";
	
		final String TABLE_sale = "SALES";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("ALTER TABLE sales"
											+ "ADD minimumraise VARCHAR(255) NOT NULL");
			
			if(result == 0 ) {
				System.out.println("Table " + TABLE_sale + " has been altered successfully");
				
			} else {
				System.out.println("Table " + TABLE_sale + " is not altered");
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
