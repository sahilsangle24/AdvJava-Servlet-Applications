package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

public class jdbcApp11 {

    private String driver = "oracle.jdbc.OracleDriver";

    private String DBurl = "jdbc:oracle:thin:@localhost:1521:free";

    private String dbuname = "system";

    private String dbpwd = "sahil";

    String sqlquery1 = "update trainseatavailability set available_seats=available_seats-1 where "
            + "train_id=? and journey_date=? and class=?";

    String sqlquery2 = "insert into bookingdetails values (?,?,?,?,?)";

    public Connection connect() {

        Connection con = null;

        try {

            Class.forName(driver);

            con = DriverManager.getConnection(
                    DBurl,
                    dbuname,
                    dbpwd);

            System.out.println("connection created ..");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return con;
    }

    void meth1() {

        System.out.println("implementing transaction management");

        try {

            Connection con = connect();

            System.out.println("getAutoCommit() :" + con.getAutoCommit());

            con.setAutoCommit(false);

            System.out.println("getAutoCommit() :" + con.getAutoCommit());

            PreparedStatement pstmt1 = con.prepareStatement(sqlquery1);

            pstmt1.setString(1, "12345");

            pstmt1.setString(2, "2024-10-10");

            pstmt1.setString(3, "sleeper");

            int rowcount1 = pstmt1.executeUpdate();

            if (rowcount1 == 0) {

                throw new RuntimeException("seats are not available");

            } else {

                System.out.println("seat is locked");

                Savepoint sp = con.setSavepoint();

                PreparedStatement pstmt2 = con.prepareStatement(sqlquery2);

                pstmt2.setString(1, "B101");

                pstmt2.setString(2, "12345");

                pstmt2.setString(3, "C123");

                pstmt2.setInt(4, 1);

                pstmt2.setString(5, "payment pending");

                int rowCount2 = pstmt2.executeUpdate();

                if (rowCount2 == 0)

                    throw new RuntimeException("illegal argument");

                else

                    System.out.println("booking record created");

                System.out.println("waiting for payment confirmation");

               con.commit();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static void main(String[] args) {

        jdbcApp11 obj = new jdbcApp11();

        obj.meth1();

    }

}