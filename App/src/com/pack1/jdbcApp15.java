package com.pack1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcApp15 {

    private String driver = "oracle.jdbc.OracleDriver";

    private String dbURL = "jdbc:oracle:thin:@localhost:1521:free";

    private String dbUname = "system";

    private String dbPwd = "sahil";

    String sqlQuery1 = "insert into mydata values(?,?)";

    String sqlQuery2 = "select pic_data from mydata where id=?";

    String sqlQuery3 = "insert into mydata2 values(?,?)";

    String sqlQuery4="select file_data from mydata2 where id=?";
    
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


    void m1() {

        System.out.println("passing image into database");

        try {

            Connection con = connect();

            if (con == null) {

                System.out.println("Connection failed");

                return;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sqlQuery1);

            pstmt.setString(1, "101");

            FileInputStream fis =
                    new FileInputStream(
                            "C:\\Users\\sahil\\Downloads\\photo_sahil.jpeg"
                    );

            pstmt.setBlob(2, fis, fis.available());

            int rowCount = pstmt.executeUpdate();

            if (rowCount == 0) {

                System.out.println("image not inserted");

            } else {

                System.out.println("image stored in the database");

            }

            fis.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    void m2() {

        System.out.println("retrieving the image from the database");

        try {

            Connection con = connect();

            if (con == null) {

                System.out.println("Connection failed");

                return;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sqlQuery2);

            pstmt.setString(1, "101");

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Blob b = rs.getBlob(1);

                byte arr[] =
                        b.getBytes(1, (int) b.length());

                FileOutputStream fos =
                        new FileOutputStream(
                                "C:\\Users\\sahil\\Downloads\\photo_sahil2.jpeg"
                        );

                fos.write(arr);

                System.out.println("image retrieved");

                fos.close();

            } else {

                System.out.println("Image not found");

            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    void m3() {

        System.out.println("passing a file into the database");

        try {

            Connection con = connect();

            if (con == null) {

                System.out.println("Connection failed");

                return;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sqlQuery3);

            pstmt.setString(1, "101");

            FileReader fr =
                    new FileReader(
                            "C:\\Users\\sahil\\Downloads\\file.txt"
                    );

            pstmt.setClob(2, fr);

            int rowCount = pstmt.executeUpdate();

            if (rowCount == 0)

                throw new SQLException("file not inserted");

            else

                System.out.println("file inserted");

            fr.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    void m4()
    {
        System.out.println("retriving the file from the database");
        try
        {
            Connection con=connect();
            PreparedStatement pstmt=con.prepareStatement(sqlQuery4);
            pstmt.setString(1, "101");
            ResultSet rs =pstmt.executeQuery();
            if(rs.next())
            {
                Clob b=rs.getClob(1);
                Reader data =b.getCharacterStream();
                BufferedReader br=new BufferedReader(data);
                FileWriter fw=new FileWriter("C:\\Users\\sahil\\Downloads\\file2.txt");
                String line;
                while((line=br.readLine())!=null)
                {
                    fw.write(line);
                    
                }
                br.close();
                fw.close();
                System.out.println("clob data retrived");
            }
            else
                throw new SQLException("invalid id");
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        jdbcApp15 obj = new jdbcApp15();

       // obj.m1();

       // obj.m2();

        obj.m3();
        
        obj.m4();

    }

}