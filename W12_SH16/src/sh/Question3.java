package sh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Question3 {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	   private static Connection con = null;
	   private static Statement stmt = null;
	   

	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
	      // Register driver and create driver instance
		   
	      Class.forName(driverName);
	      // get connection
	      
	       con = DriverManager.getConnection("jdbc:hive2://localhost:10000/sh", "", "");
	       stmt = con.createStatement();
	       
	       stmt.execute("use sh");
	      
	       
		    // create partition table by year
	       stmt.execute("CREATE TABLE"
	  	         +" partition_by_year (AthleteName String, Age int, Country String,  Closing_Date String,"
	  	         +" Sport String, Gold_Medals int, Silver_Medals int, Bronze_Medals int, Total_Medals int)"
	  	         +" partitioned by (year int)"); 
	  	      System.out.println("Partition Table created");
	  	      
	  	    //make the property non-strict  
	  	   stmt.execute("set hive.exec.dynamic.partition.mode = nonstrict");
	  	   
	  	   //load data into partition table from olympic table
	  	   stmt.execute("insert overwrite table partition_by_year partition (year) select * from olympic");
	  	 System.out.println("Successfully Loaded the Data from Olympic table to Partition_by_Year table");  
	   
	      
	      con.close();
	}

}
