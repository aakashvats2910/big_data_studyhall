package sh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question6 {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	   private static Connection con = null;
	   private static Statement stmt = null;
	   private static ResultSet rs = null;

	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
	      // Register driver and create driver instance
		   
	      Class.forName(driverName);
	      // get connection
	      
	       con = DriverManager.getConnection("jdbc:hive2://localhost:10000/sh", "", "");
	       stmt = con.createStatement();
	       
	       stmt.execute("use sh");
	      
	       
		      rs = stmt.executeQuery("select year, sum(total_medals) from olympic where country ='India' group by year");
		       int columns = rs.getMetaData().getColumnCount();
		       while (rs.next()) {
		         for ( int i = 0 ; i < columns; ++i) {
		            System.out.print(rs.getString(i + 1) + " " );
		            
		         }
		         System.out.println();
		       }
	       
		      
	   
	      
	      con.close();
	}

}
