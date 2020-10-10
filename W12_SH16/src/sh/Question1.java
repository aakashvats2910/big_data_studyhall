package sh;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Question1 {

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

		// Create table olympic

		stmt.execute("CREATE TABLE OLYMPIC_DATA (AthleteName String, Age int, Country String, Year int, Closing_date String, Sports String, Gold_medal int, Silver_medals int, Bronze_medals int, total_medals int) ROW FORMAT DELIMITED FIELDS TERMINATED BY ','");


		con.close();
	}

}
