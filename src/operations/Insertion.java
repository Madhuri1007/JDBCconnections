package operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Insertion {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = " jdbc:mysql://localhost:3306/student";
	private static final String username = "root";
	private static final String password = "root"; 
	private static Connection con;
	private static PreparedStatement pmst;
public static void main(String[] args) {
	try {
		Scanner src = new Scanner(System.in);
		Class.forName(driver);
		con = DriverManager.getConnection(url, username, password);
		String sql = "insert into login(loginid,loginemail,loginpassword)values (?,?,?)";
	    pmst = con.prepareStatement(sql);
	    System.out.println("Enter login id: ");
	    pmst.setString(1, src.nextLine());
	    System.out.println("Enter login Email: ");
	    pmst.setString(2, src.nextLine());
	    System.out.println("Enter login password: ");
	    pmst.setString(3, src.nextLine());
		int i = pmst.executeUpdate();
		if (i<0)
		{
			System.out.println("Data Inserted");
		}
		else {
			System.out.println("Data not inserted");
		}
		pmst.close();
		con.close();
		src.close();
		} catch (Exception e) {
		e.printStackTrace();
	}
}
}
