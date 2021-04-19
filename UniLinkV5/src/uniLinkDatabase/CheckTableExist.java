package uniLinkDatabase;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTableExist {
	public static void main(String[] args) throws SQLException {

		        //Initialize database created
				final String DB_NAME = "uniLinkDB";
				//Initialize tables in database, one for each kind of posts and one for replies
				//final String TABLE_NAME_EVENT = "EVENTS";
				//final String TABLE_NAME_SALE  = "SALES";
				//final String TABLE_NAME_JOB   = "JOBS";
				final String TABLE_NAME_REPLIESS = "REPLYjob";
		
		// use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME)) {

			DatabaseMetaData dbm = con.getMetaData();
			//ResultSet eventTable = dbm.getTables(null, null, TABLE_NAME_EVENT.toUpperCase(), null);
			//ResultSet saleTable = dbm.getTables(null, null, TABLE_NAME_SALE.toUpperCase(), null);
			//ResultSet jobTable = dbm.getTables(null, null, TABLE_NAME_JOB.toUpperCase(), null);
			ResultSet repliesTable = dbm.getTables(null, null, TABLE_NAME_REPLIESS.toUpperCase(), null);
			
			if(repliesTable != null) {
				if ( repliesTable.next()) {
					System.out.println("Table "+TABLE_NAME_REPLIESS+  " exists.");
				}
				else {
					System.out.println("Table "+TABLE_NAME_REPLIESS+  " does not exist.");
				}	
//				eventTable.close();
//				saleTable.close();
//				jobTable.close();
				repliesTable.close();
			} else {
				System.out.println(("Problem with retrieving database metadata"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
