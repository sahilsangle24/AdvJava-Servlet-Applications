package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcApp3 {

    private String driver = "oracle.jdbc.OracleDriver";
    private String dbURL = "jdbc:oracle:thin:@localhost:1521:free";
    private String dbUname = "system";
    private String dbPwd = "sahil";
    private String sqlquery = "select esal from employee order by esal desc ";

    void createConnection() {

        try {
            System.out.println("loading the driver");

            Class.forName(driver);

            Connection con = DriverManager.getConnection(dbURL, dbUname, dbPwd);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sqlquery);

            System.out.println("conn created");

            while (rs.next()) {
                System.out.println(
                   
                    rs.getString(1)
                );
            }

        } catch (Exception e) {
        	 System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        jdbcApp3 obj = new jdbcApp3();
        obj.createConnection();

    }
}