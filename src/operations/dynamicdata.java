package operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class dynamicdata {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String username = "root";
	private static final String password = "root"; 
	private static Connection con;
	private static PreparedStatement pmst;
	public static void main(String[] args) {
		int choice;
		do {
			Scanner src = new Scanner(System.in); 
			System.out.println("choose your choice");
			displayMenu();
			choice= Integer.parseInt(src.next());
			switch (choice) {
			case 1: 
				createdatabase();
				break;
			case 2:
				dropdatabase();
				break;
			case 3:
				datainsertion();
				break;
			case 4:
				deletebyemail();
				break ;
			case 5:
				updatedata();
				break;
			case 6:
				getbyemail();
				break;		
			case 7:
				getall();
				break;
			case 8:
				exit();
				break;
			case 9:
				login();
				break;
			}
		}
		while (choice>0);
	}

	private static void login() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter data base to view: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			con =DriverManager.getConnection(url, username, password);
			System.out.println("Enter the table name: ");
			String sql = "select *from orders "+src.next()+ " where order_id=? and order_name=?";
			pmst = con.prepareStatement(sql);
			System.out.println("Enter order id: ");
			pmst.setLong(1, src.nextLong());
			System.out.println("Enter order name: ");
			pmst.setString(2, src.next());
			ResultSet rs = pmst.executeQuery();
			if(rs.next())
			{
				System.out.println("login done");
			}
			else {
				System.out.println("login not done");
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void exit() {
		// TODO Auto-generated method stub
		
	}

	private static void getall() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter data base name : ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Enter data base table name: ");
			String sql="select* from orders "+src.next();
			pmst= con.prepareStatement(sql);
			ResultSet rs= pmst.executeQuery();// data should not be integer the data type is not known so we are using Result set
			while (rs.next()) {
				System.out.println("order_id :"+ rs.getLong("order_id"));
			    System.out.println("order_name :"+rs.getString("order_name"));
			    System.out.println("order_pincode :"+rs.getInt("order_pincode"));
	            System.out.println("order_address :"+rs.getString("order_address"));
			}
			pmst.close();
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
}
	private static void getbyemail() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter the data base name: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			con =  DriverManager.getConnection(url, username, password);
			System.out.println("Enter the table name: ");
			String sql="select* from "+src.next()+ " where order_id=?";
			pmst= con.prepareStatement(sql);
			System.out.println("Enter order id: ");
			pmst.setLong(1, src.nextLong());
			
			ResultSet rs = pmst.executeQuery();
			while(rs.next())
			{
				System.out.println("order_id :"+ rs.getLong("order_id"));
			    System.out.println("order_name :"+rs.getString("order_name"));
			    System.out.println("order_pincode :"+rs.getInt("order_pincode"));
	            System.out.println("order_address :"+rs.getString("order_address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void updatedata() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter data base name to update data: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			con = DriverManager.getConnection(url, username, password);
			System.out.println(" Enter data base table name: ");
			String sql="update "+src.next()+ " set order_name=?, order_pincode=?, order_address=? where order_id =?";
			pmst= con.prepareStatement(sql);
			System.out.println("Enter order name:");
			pmst.setString(1, src.next());
			System.out.println("Enter order pincode:");
			pmst.setInt(2, src.nextInt());
			System.out.println("Enter order address:");
			pmst.setString(3,src.next());
			System.out.println("Enter order id: ");
			pmst.setLong(4,src.nextLong());
			int i= pmst.executeUpdate();
			if(i>0)
			{
				System.out.println("updated");
			}
			else {
				System.out.println("not updated");
			}
			pmst.close();
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
}
	private static void deletebyemail() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter data base name to delete: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			con = DriverManager.getConnection(url, username, password);
			System.out.println(" Enter data base table name: ");
			String sql="delete from "+src.next()+ " where order_id =?";
			pmst= con.prepareStatement(sql);
			System.out.println("Enter order id: ");
			pmst.setLong(1,src.nextLong());
			int i= pmst.executeUpdate();
			if(i>0)
			{
				
				System.out.println("deleted");
			}
			else {
				System.out.println("not deleted");
			}
			pmst.close();
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
}
	private static void datainsertion() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			System.out.println("Enter data base name to insert data: ");
			String url = "jdbc:mysql://localhost:3306/"+src.next();
			con = DriverManager.getConnection(url, username, password);
			System.out.println(" Enter data base table name: ");
			String sql="insert into "+src.next()+ "(order_id,order_name,order_pincode,order_address) values(?,?,?,?)";
			pmst= con.prepareStatement(sql);
			System.out.println("Enter order id: ");
			pmst.setLong(1,src.nextLong());
			System.out.println("Enter order name:");
			pmst.setString(2, src.next());
			System.out.println("Enter order pincode:");
			pmst.setInt(3, src.nextInt());
			System.out.println("Enter order address:");
			pmst.setString(4,src.next());
			int i= pmst.executeUpdate();
			if(i>0)
			{
				System.out.println("inserted");
			}
			else {
				System.out.println("not inserted");
			}
			pmst.close();
			con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
}
	private static void dropdatabase() {
     
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(driver); // data base name type
			String url = "jdbc:mysql://localhost:3306/";
			con = DriverManager.getConnection(url,username,password);
			System.out.println("Enter data base name: ");
			String sql= "drop database "+src.nextLine();
		     pmst = con.prepareStatement(sql);
			int i = pmst.executeUpdate(); 
			if( i == 0)
			{
				System.out.println("Data base is dropped");
			}
			else
			{
				System.out.println(" Data base is not dropped");
			}
			pmst.close();
			con.close();
	}
		catch (Exception e) {
			e.printStackTrace();
		}
}

	private static void createdatabase() {
		try {

			Scanner src = new Scanner(System.in);
			Class.forName(driver);
			String url = "jdbc:mysql://localhost:3306/";
			con = DriverManager.getConnection(url, username, password);
			System.out.println(" Enter data base name: ");
			String sql="Create database "+src.next();
			pmst= con.prepareStatement(sql);
			int i= pmst.executeUpdate();
			if(i > 0)
			{
				System.out.println("created");
			}
			else {
				System.out.println("not created");
			}
			pmst.close();
			con.close();
		}
			catch (Exception e) {
				e.printStackTrace();
			}
	}

	private static void displayMenu() {
		// TODO Auto-generated method stub
		System.out.println("\t1.create database");
        System.out.println("\t2.drop database");	
        System.out.println("\t3.data insertion");
        System.out.println("\t4.delete by email");
        System.out.println("\t5.update data");
        System.out.println("\t6.get by email");
        System.out.println("\t7.get all");
        System.out.println("\t8.exit");
        System.out.println("\t9.login");
        }

}
