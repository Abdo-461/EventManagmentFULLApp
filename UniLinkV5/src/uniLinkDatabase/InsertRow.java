package uniLinkDatabase;
import java.sql.Connection;
import java.sql.Statement;

public class InsertRow {
	public static void main(String[] args) {
		final String DB_NAME = "uniLinkDB";
		final String TABLE_EVENT = "EVENTS";
		//final String TABLE_SALE = "SALES";
		//final String TABLE_JOB = "JOBS";
		//final String TABLE_REPLY = "REPLIES";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "INSERT INTO events "+
					"VALUES ('EVE012', 'Air trip', 'Travel to Thailand','S3', 'OPEN','Bangkok','2020-06-01',400)";
					
//			
//		String query2 = "INSERT INTO sales " + 
//					"VALUES ('SAL013','iPad','New iPad mini','S3','OPEN',300.0,400.0)";
//			
//		//String query3 = "INSERT INTO jobs " + 
//					"VALUES ('JOB012','Build Furniture','Help build a couch','S2','OPEN',2000)";
//			
//			String query4 = "INSERT INTO replies " + 
//					"VALUES ('JOB006','123','S45')";
		
			int result  = stmt.executeUpdate(query);
		//	int result2 = stmt.executeUpdate(query2);
		//	int result3 = stmt.executeUpdate(query3);
			//int result4 = stmt.executeUpdate(query4);
			
			con.commit();
			
			System.out.println("Insert into table " + TABLE_EVENT + " executed successfully");
		//	System.out.println("Insert into table " + TABLE_SALE + " executed successfully");
		//	System.out.println("Insert into table " + TABLE_JOB + " executed successfully");
			//System.out.println("Insert into table " + TABLE_REPLY + " executed successfully");
			//System.out.println(result + result2 + result3+result4+ " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
