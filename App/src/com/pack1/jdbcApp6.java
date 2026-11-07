package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class jdbcApp6 {

    private String driver = "oracle.jdbc.OracleDriver";
    private String dbURL = "jdbc:oracle:thin:@localhost:1521:free";
    private String dbUname = "system";
    private String dbPwd = "manager";

    Scanner sc = new Scanner(System.in);

    public Connection connect() {
        Connection con = null;

        try {
            Class.forName(driver);

            con = DriverManager.getConnection(
                    dbURL,
                    dbUname,
                    dbPwd
            );

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    public void operations() {

        Connection con = connect();

        try {

            // Prepared Statements
            PreparedStatement pstmt1 = con.prepareStatement(
                    "insert into patient values(?,?,?,?)"
            );

            PreparedStatement pstmt2 = con.prepareStatement(
                    "select * from patient"
            );

            PreparedStatement pstmt3 = con.prepareStatement(
                    "select * from patient where pid=?"
            );

            PreparedStatement pstmt4 = con.prepareStatement(
                    "update patient set age=? where pid=?"
            );

            PreparedStatement pstmt5 = con.prepareStatement(
                    "delete from patient where pid=?"
            );

            while (true) {

                System.out.println("\n----- Welcome to Patient Database -----");
                System.out.println("Choose Your Options");
                System.out.println("1) Add Patient Data");
                System.out.println("2) View Patient Data");
                System.out.println("3) Retrieve Patient Data");
                System.out.println("4) Update Patient Data");
                System.out.println("5) Delete Patient Data");
                System.out.println("6) Exit");

                System.out.print("Enter Your Choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                // --------------------------------
                // 1. ADD PATIENT
                // --------------------------------
                case 1:

                    System.out.println("\n----- Adding Patient Data -----");

                    System.out.print("Enter Patient ID: ");
                    String pid = sc.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String pname = sc.nextLine();

                    System.out.print("Enter Patient Age: ");
                    int age = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Patient Disease: ");
                    String disease = sc.nextLine();

                    pstmt1.setString(1, pid);
                    pstmt1.setString(2, pname);
                    pstmt1.setInt(3, age);
                    pstmt1.setString(4, disease);

                    int rowCount1 = pstmt1.executeUpdate();

                    if (rowCount1 > 0) {
                        System.out.println("Patient Data Added Successfully");
                    }

                    break;

                // --------------------------------
                // 2. VIEW ALL PATIENTS
                // --------------------------------
                case 2:

                    System.out.println("\n----- Patient Details -----");

                    ResultSet rs1 = pstmt2.executeQuery();

                    while (rs1.next()) {

                        System.out.println(
                                "Patient ID     : " + rs1.getString("pid")
                        );

                        System.out.println(
                                "Patient Name   : " + rs1.getString("pname")
                        );

                        System.out.println(
                                "Patient Age    : " + rs1.getInt("age")
                        );

                        System.out.println(
                                "Disease        : " + rs1.getString("disease")
                        );

                        System.out.println("-----------------------------");
                    }

                    rs1.close();

                    break;

                // --------------------------------
                // 3. RETRIEVE PATIENT
                // --------------------------------
                case 3:

                    System.out.println("\n----- Retrieve Patient Data -----");

                    System.out.print("Enter Patient ID: ");
                    String rpid = sc.nextLine();

                    pstmt3.setString(1, rpid);

                    ResultSet rs2 = pstmt3.executeQuery();

                    if (rs2.next()) {

                        System.out.println(
                                "Patient ID     : " + rs2.getString("pid")
                        );

                        System.out.println(
                                "Patient Name   : " + rs2.getString("pname")
                        );

                        System.out.println(
                                "Patient Age    : " + rs2.getInt("age")
                        );

                        System.out.println(
                                "Disease        : " + rs2.getString("disease")
                        );

                    } else {

                        System.out.println("Patient Not Found");
                    }

                    rs2.close();

                    break;

                // --------------------------------
                // 4. UPDATE PATIENT
                // --------------------------------
                case 4:

                    System.out.println("\n----- Update Patient Data -----");

                    System.out.print("Enter Patient ID: ");
                    String upid = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int newAge = Integer.parseInt(sc.nextLine());

                    pstmt4.setInt(1, newAge);
                    pstmt4.setString(2, upid);

                    int rowCount2 = pstmt4.executeUpdate();

                    if (rowCount2 > 0) {

                        System.out.println(
                                "Patient Age Updated Successfully"
                        );

                    } else {

                        System.out.println("Patient ID Not Found");
                    }

                    break;

                // --------------------------------
                // 5. DELETE PATIENT
                // --------------------------------
                case 5:

                    System.out.println("\n----- Delete Patient Data -----");

                    System.out.print("Enter Patient ID: ");
                    String dpid = sc.nextLine();

                    pstmt5.setString(1, dpid);

                    int rowCount3 = pstmt5.executeUpdate();

                    if (rowCount3 > 0) {

                        System.out.println(
                                "Patient Deleted Successfully"
                        );

                    } else {

                        System.out.println("Patient ID Not Found");
                    }

                    break;

                // --------------------------------
                // 6. EXIT
                // --------------------------------
                case 6:

                    System.out.println("Thank You!");
                    System.out.println("Exiting Patient Database...");

                    con.close();
                    sc.close();

                    return;

                default:

                    System.out.println(
                            "Invalid Choice! Please Enter 1 to 6."
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    	jdbcApp6 obj = new jdbcApp6();

        obj.operations();
    }
}