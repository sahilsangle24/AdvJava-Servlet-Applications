package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcApp2 {

    private String driver = "oracle.jdbc.OracleDriver";
    private String dbURL = "jdbc:oracle:thin:@localhost:1521:free";
    private String dbUname = "system";
    private String dbPwd = "sahil";
    private String sqlquery = "select * from employee ";

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
                    rs.getInt(1) + " " +
                    rs.getString(2) + " " +
                    rs.getString(3) + " " +
                    rs.getInt(4) + " " +
                    rs.getString(5)
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        jdbcApp2 obj = new jdbcApp2();
        obj.createConnection();

    }
}