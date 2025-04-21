package operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class createdb {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/";
	private static final String username = "root";
	private static final String password = "root";
	private static PreparedStatement pmst;
	private static Connection conn;
public static void main(String[] args) {
	try {
		Scanner src = new Scanner(System.in);
		Class.forName(driver); // data base name type
		conn = DriverManager.getConnection(url,username,password);// running on port 3306 
		System.out.println("Enter data base name: ");
		String sql= "create database "+src.nextLine();// writing query <data base name>
	     pmst = conn.prepareStatement(sql); //execute query 
		int i = pmst.executeUpdate(); // provides the output
		if( i > 0)
		{
			System.out.println("Data base is created");
		}
		else
		{
			System.out.println(" Data base is not created");
		}
		pmst.close();
		conn.close();
		src.close();
	} catch (Exception e) // compile time error
	{
	     e.printStackTrace();
	}finally {
		System.out.println("");
	}
}

}
