package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcApp12 {
	
	private String driver = "oracle.jdbc.OracleDriver";
	private String DBurl ="jdbc:oracle:thin:@localhost:1521:free";
	
	
	private String dbuname ="system";
	private String dbpwd ="sahil";
	
	
	
	void meth1() 
	{
		System.out.println("loading the driver");
		
		try {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(DBurl,dbuname,dbpwd);
		
	    PreparedStatement pstmt = con.prepareStatement("select * from employee");
	    ResultSet rs = pstmt.executeQuery();
		
	    System.out.println("connection created");
	    
	    while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " " +
                rs.getString(2) + " " +
                rs.getString(3) + " " +
                rs.getInt(4) + " " +
                rs.getString(5)
            );
           
        }
	    con.close();
	    
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		jdbcApp12 obj = new jdbcApp12();
		obj.meth1();
	}

}
