package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;


public class MetaDataDemo {

    public static void main(String[] args) throws Exception {

        // 1. Establish connection
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:free",
                "system",
                "sahil");

        // 2. Get DatabaseMetaData object
        DatabaseMetaData dbmd = con.getMetaData();

        // 3. Get database information
        System.out.println("Database Name : "
                + dbmd.getDatabaseProductName());

        System.out.println("Database Version : "
                + dbmd.getDatabaseProductVersion());

        System.out.println("Driver Name : "
                + dbmd.getDriverName());

        System.out.println("Driver Version : "
                + dbmd.getDriverVersion());
        
        // PreparedStatement with one parameter
        PreparedStatement pstmt = con.prepareStatement(
                "SELECT * FROM EMP WHERE SAL > ?");

        // Get ParameterMetaData
        ParameterMetaData pmd = pstmt.getParameterMetaData();

        // Number of parameters
        System.out.println("Number of Parameters : "
                + pmd.getParameterCount());

        con.close();
    }
}