```java
package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcApp5 {

    private String driver = "oracle.jdbc.OracleDriver";
    private String dbURL = "jdbc:oracle:thin:@localhost:1521:free";
    private String dbUname = "system";
    private String dbPwd = "sahil";

    // INSERT
    void insertEmpData() {
        try {
            Class.forName(driver);

            Connection con = DriverManager.getConnection(
                    dbURL, dbUname, dbPwd);

            Statement stmt = con.createStatement();

            String sql = "insert into employee values" +
                    "(108, 'Athena', 'John', 50000, 'mum')";

            int rowCount = stmt.executeUpdate(sql);

            if (rowCount > 0) {
                System.out.println("Employee data inserted successfully");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    void deleteEmpData() {
        try {
            Class.forName(driver);

            Connection con = DriverManager.getConnection(
                    dbURL, dbUname, dbPwd);

            Statement stmt = con.createStatement();

            String sql = "delete from employee where eid = 108";

            int rowCount = stmt.executeUpdate(sql);

            if (rowCount > 0) {
                System.out.println("Employee data deleted successfully");
            } else {
                System.out.println("Employee ID not found");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    void updateEmpData() {
        try {
            Class.forName(driver);

            Connection con = DriverManager.getConnection(
                    dbURL, dbUname, dbPwd);

            Statement stmt = con.createStatement();

            String sql =
                    "update employee set esal = 55000 where eid = 108";

            int rowCount = stmt.executeUpdate(sql);

            if (rowCount > 0) {
                System.out.println("Employee data updated successfully");
            } else {
                System.out.println("Employee ID not found");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // RETRIEVE
    void retrieveEmpData() {
        try {
            Class.forName(driver);

            Connection con = DriverManager.getConnection(
                    dbURL, dbUname, dbPwd);

            Statement stmt = con.createStatement();

            String sql =
                    "select * from employee where eid = 108";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                System.out.println("Employee ID : "
                        + rs.getInt("eid"));

                System.out.println("Name       : "
                        + rs.getString("ename"));

                System.out.println("Job        : "
                        + rs.getString("ejob"));

                System.out.println("Salary     : "
                        + rs.getDouble("esal"));

                System.out.println("Location   : "
                        + rs.getString("eloc"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MAIN
    public static void main(String[] args) {

        jdbcApp5 obj = new jdbcApp5();

        obj.insertEmpData();

        obj.updateEmpData();

        obj.retrieveEmpData();

        // obj.deleteEmpData();
    }
}

