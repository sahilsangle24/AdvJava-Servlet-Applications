package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcApp1 {

    private String driver = "oracle.jdbc.OracleDriver";
    private String dbURl = "jdbc:oracle:thin:@localhost:1521:free";
    private String dbUname = "system";
    private String dbPwd = "sahil";

    void createconnection() {

        try {

            System.out.println("loading the driver");

            Class.forName(driver);

            Connection con = DriverManager.getConnection(
                dbURl, dbUname, dbPwd
            );

            System.out.println("Connection Created");

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void main(String[] args) {

        jdbcApp1 obj = new jdbcApp1();

        obj.createconnection();
    }
}