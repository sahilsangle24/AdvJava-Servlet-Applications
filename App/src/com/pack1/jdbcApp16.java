package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.Statement;
import java.util.Arrays;

public class jdbcApp16 {

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

            System.out.println("Connection created");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;
    }

    void meth1()
    {
        System.out.println("Implementing Batch Processing\n");

        try
        {
            Connection con = connect();
            Statement stmt = con.createStatement();

            System.out.println("How many queries you want to add to the batch");

            int no_ofQuaries = Integer.parseInt(sc.nextLine());

            for(int i=1; i<=no_ofQuaries; i++)
            {
                System.out.println("Enter your "+i+" query");

                stmt.addBatch(sc.nextLine());
            }

            int arr[] = stmt.executeBatch();

            System.out.println("===>"+Arrays.toString(arr));

            stmt.clearBatch();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new jdbcApp16().meth1();
    }
}