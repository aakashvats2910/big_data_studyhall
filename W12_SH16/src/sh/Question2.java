package sh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Question2 {

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
		stmt.execute("SET hive.auto.convert.join=false");

		// Upload data in the table
		try {
			stmt.execute("load data local inpath '/home/cloudera/Downloads/olympic_data.csv' into table OLYMPIC_DATA");
		} catch (Exception e) {
			System.out.println("FATALII : " + e.getLocalizedMessage());
		}

		System.out.println("Data uploaded successfully");


		con.close();
	}
}
