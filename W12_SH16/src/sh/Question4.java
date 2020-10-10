package sh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Question4 {
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
	      
	       
		    // create bucket table by year
	       stmt.execute("CREATE TABLE"
	  	         +" bucketing_by_country (AthleteName String, Age int, Country String,Year int,  Closing_Date String,"
	  	         +" Sport String, Gold_Medals int, Silver_Medals int, Bronze_Medals int, Total_Medals int)"
	  	         +" clustered by (Country) into 5 buckets row format delimited fields terminated by '\t' "); 
	  	      System.out.println("Bucket Table created");


	  	   
	  	   //load data 
	  	   stmt.execute("from olympic insert overwrite table bucketing_by_country select * ");
	  	 System.out.println("Successfully Loaded the Data");  
	  
	      
	      con.close();
	}

}
