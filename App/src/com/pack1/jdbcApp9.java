package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class jdbcApp9 {

    private String driver = "oracle.jdbc.OracleDriver";
    private String dburl = "jdbc:oracle:thin:@localhost:1521:free";
    private String dbuname = "system";
    private String dbpwd = "sahil";

    Scanner scn = new Scanner(System.in);

    // =====================================================
    // JdbcRowSet
    // =====================================================

    void meth1() {

        System.out.println("Implementing JdbcRowSet");

        try {

            // Create RowSetFactory
            RowSetFactory rsf = RowSetProvider.newFactory();

            // Create JdbcRowSet
            JdbcRowSet jrs = rsf.createJdbcRowSet();

            // Set database details
            jrs.setUrl(dburl);
            jrs.setUsername(dbuname);
            jrs.setPassword(dbpwd);

            // Set SQL query
            jrs.setCommand("select * from employee");

            // Execute query
            jrs.execute();

            // Move cursor to last record
            jrs.last();

            System.out.println("Last Employee:");

            System.out.println(
                    jrs.getString(1) + " " +
                    jrs.getString(2) + " " +
                    jrs.getString(3) + " " +
                    jrs.getString(4) + " " +
                    jrs.getString(5)
            );

            System.out.println("----------------");

            // Move cursor to first record
            jrs.first();

            System.out.println("First Employee:");

            System.out.println(
                    jrs.getString(1) + " " +
                    jrs.getString(2) + " " +
                    jrs.getString(3) + " " +
                    jrs.getString(4) + " " +
                    jrs.getString(5)
            );

            System.out.println("----------------");

            // Close JdbcRowSet
            jrs.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // CachedRowSet
    // =====================================================

    void meth2() {

        System.out.println("Implementing CachedRowSet");

        try {

            // Create RowSetFactory
            RowSetFactory rsf = RowSetProvider.newFactory();

            // Create CachedRowSet
            CachedRowSet crs = rsf.createCachedRowSet();

            // Set database details
            crs.setUrl(dburl);
            crs.setUsername(dbuname);
            crs.setPassword(dbpwd);

            // Set SQL query
            crs.setCommand(
                    "select eid, efname, esal from employee"
            );

            // Execute query
            crs.execute();

            // Take employee ID from user
            System.out.println("Enter emp id:");
            int eid = scn.nextInt();

            // Take new salary from user
            System.out.println("Enter emp salary:");
            int esal = scn.nextInt();

            // Traverse CachedRowSet
            while (crs.next()) {

                // Check employee ID
                if (crs.getInt("eid") == eid) {

                    System.out.println(
                            "Updating the salary for employee: "
                            + crs.getString("efname")
                    );

                    // Update salary in CachedRowSet
                    crs.updateInt("esal", esal);

                    // Update current row
                    crs.updateRow();
                }
            }

            // Create database connection
            Connection con = DriverManager.getConnection(
                    dburl,
                    dbuname,
                    dbpwd
            );

            // Disable auto-commit
            con.setAutoCommit(false);

            // Send changes from CachedRowSet to database
            crs.acceptChanges(con);

            // Commit changes
            con.commit();

            System.out.println(
                    "Salary updated successfully."
            );

            // Close connection
            con.close();

            // Close CachedRowSet
            crs.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // Main Method
    // =====================================================

    public static void main(String[] args) {

        jdbcApp9 obj = new jdbcApp9();

        // Calling JdbcRowSet
        // obj.meth1();

        // Calling CachedRowSet
        obj.meth2();
    }
}