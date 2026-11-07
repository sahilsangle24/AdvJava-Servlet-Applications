package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLIntegrityConstraintViolationException;

public class jdbcApp4 {

    private String driver = "oracle.jdbc.OracleDriver";

    private String dbUrl = "jdbc:oracle:thin:@localhost:1521:free";

    private String dbUname = "system";

    private String dbPwd = "sahil";

    private String sqlquery =
            "insert into employee values(107,'anil','kakad',90000,'bhagur')";

    Scanner scn = new Scanner(System.in);

    // Method for inserting employee data
    void updatedata() {

        try {

            System.out.println("Updating the data...");

            // 1. Loading JDBC Driver
            Class.forName(driver);

            // 2. Establishing connection
            Connection con =
                    DriverManager.getConnection(dbUrl, dbUname, dbPwd);

            // 3. Creating Statement
            Statement stmt = con.createStatement();

            // 4. Executing INSERT query
            int rowCount = stmt.executeUpdate(sqlquery);

            // 5. Checking whether data is inserted
            if (rowCount > 0) {

                System.out.println("Database updated");

                System.out.println(
                        "Do you want to see your updated database? (y/n)"
                );

                char choice = scn.nextLine().charAt(0);

                switch (choice) {

                    case 'y':
                    case 'Y':

                        getEmpData();

                        break;

                    case 'n':
                    case 'N':

                        System.out.println(
                                "Thanks for updating!!!"
                        );

                        System.exit(0);

                    default:

                        System.out.println("Invalid choice");
                }

            } else {

                System.out.println(
                        "There are no changes."
                );
            }

        }catch(SQLIntegrityConstraintViolationException e) {
        	System.out.println("duplicate eid not allowed ..plz insert new data");
        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }


    // Method for displaying employee data
    void getEmpData() {

        try {

            // 1. Loading JDBC Driver
            Class.forName(driver);

            // 2. Establishing connection
            Connection con =
                    DriverManager.getConnection(
                            dbUrl,
                            dbUname,
                            dbPwd
                    );

            // 3. Creating Statement
            Statement stmt = con.createStatement();

            // 4. Executing SELECT query
            ResultSet rs =
                    stmt.executeQuery("select * from employee");

            // 5. Reading ResultSet
            while (rs.next()) {

                System.out.println(
                        rs.getInt(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3) + " " +
                        rs.getInt(4) + " " +
                        rs.getString(5)
                );
            }

        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }


    public static void main(String[] args) {

        jdbcApp4 obj = new jdbcApp4();

        obj.updatedata();

    }
}