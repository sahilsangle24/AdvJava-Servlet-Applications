package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcApp8 {

    private String driver = "oracle.jdbc.OracleDriver";
    private String dbURL = "jdbc:oracle:thin:@localhost:1521:free";
    private String dbUname = "system";
    private String dbPwd = "sahil";

    Scanner sc = new Scanner(System.in);

    Connection connect() {

        Connection con = null;

        try {
            Class.forName(driver);

            con = DriverManager.getConnection(
                    dbURL,
                    dbUname,
                    dbPwd
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    void updateData() {

        try {

            Connection con = connect();

            /*
             * 1004 = TYPE_SCROLL_INSENSITIVE
             * 1008 = CONCUR_UPDATABLE
             */
            Statement stmt = con.createStatement(1004, 1008);

            String sqlQuery2 = "SELECT * FROM employee";

            ResultSet rs = stmt.executeQuery(sqlQuery2);

            int rowNo = 0;

            while (rs.next()) {

                String empId = rs.getString(1);

                if ("106".equals(empId)) {

                    System.out.println(
                            "Updating the SALARY for Employee : "
                            + rs.getString(2)
                    );

                    rs.updateInt("esal", 60000);

                    rs.updateRow();

                    // Automatically store the current row number
                    rowNo = rs.getRow();
                }
            }

            System.out.println(
                    "Data Updated!!! Do you want to view (Y/N) ?"
            );

            char choice = sc.nextLine().charAt(0);

            switch (choice) {

                case 'Y':
                case 'y':

                    /*
                     * Move to the row where Employee 106 was found.
                     * No need to manually enter rs.absolute(6)
                     */
                    if (rowNo > 0) {

                        rs.absolute(rowNo);

                        System.out.println(
                                rs.getString(1) + " "
                                + rs.getString(2) + " "
                                + rs.getInt(3)
                        );

                    } else {

                        System.out.println("Employee 106 not found.");

                    }

                    break;

                case 'N':
                case 'n':

                    System.out.println("See you soon!!!");

                    System.exit(0);

                    break;

                default:

                    System.out.println("Invalid Data");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void main(String[] args) {

        jdbcApp8 obj = new jdbcApp8();

        obj.updateData();
    }
}