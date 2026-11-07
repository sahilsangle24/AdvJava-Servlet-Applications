package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeRegistrationApp {

    private String driver = "oracle.jdbc.OracleDriver";
    private String dburl = "jdbc:oracle:thin:@localhost:1521:free";
    private String dbuname = "system";
    private String dbpwd = "sahil";

    Scanner scn = new Scanner(System.in);

    // =====================================================
    // DATABASE CONNECTION
    // =====================================================

    Connection getConnection() throws Exception {

        Class.forName(driver);

        Connection con = DriverManager.getConnection(
                dburl,
                dbuname,
                dbpwd
        );

        return con;
    }


    // =====================================================
    // EMPLOYEE REGISTRATION
    // =====================================================

    void registration() {

        System.out.println("\n========== EMPLOYEE REGISTRATION ==========");

        try {

            System.out.print("Enter Employee Name: ");
            String eName = scn.nextLine().trim();

            System.out.print("Enter Password: ");
            String pword = scn.nextLine().trim();

            System.out.print("Enter First Name: ");
            String fname = scn.nextLine().trim();

            System.out.print("Enter Last Name: ");
            String lname = scn.nextLine().trim();

            System.out.print("Enter Address: ");
            String addr = scn.nextLine().trim();

            System.out.print("Enter Middle Initial: ");
            String mid = scn.nextLine().trim();

            System.out.print("Enter Phone Number: ");
            String phN = scn.nextLine().trim();


            // =================================================
            // INPUT VALIDATION
            // =================================================

            if (eName.isEmpty() ||
                pword.isEmpty() ||
                fname.isEmpty() ||
                lname.isEmpty() ||
                addr.isEmpty() ||
                mid.isEmpty() ||
                phN.isEmpty()) {

                System.out.println(
                    "Error: All fields are required."
                );

                return;
            }


            if (phN.length() != 10) {

                System.out.println(
                    "Error: Phone number must contain 10 digits."
                );

                return;
            }


            if (!phN.matches("[0-9]+")) {

                System.out.println(
                    "Error: Phone number must contain digits only."
                );

                return;
            }


            // =================================================
            // INSERT DATA
            // =================================================

            Connection con = getConnection();

            String sql =
                "INSERT INTO EmployeeRegistration " +
                "(eName, pword, fname, lname, addr, mid, phN) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt =
                con.prepareStatement(sql);

            pstmt.setString(1, eName);
            pstmt.setString(2, pword);
            pstmt.setString(3, fname);
            pstmt.setString(4, lname);
            pstmt.setString(5, addr);
            pstmt.setString(6, mid);
            pstmt.setString(7, phN);

            int rowCount = pstmt.executeUpdate();

            if (rowCount > 0) {

                System.out.println(
                    "Employee registered successfully!"
                );
            }

            pstmt.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                "Registration failed."
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // EMPLOYEE LOGIN
    // =====================================================

    void login() {

        System.out.println("\n========== EMPLOYEE LOGIN ==========");

        try {

            System.out.print("Enter Employee Name: ");
            String eName = scn.nextLine().trim();

            System.out.print("Enter Password: ");
            String pword = scn.nextLine().trim();


            // Input validation

            if (eName.isEmpty() || pword.isEmpty()) {

                System.out.println(
                    "Error: Employee name and password are required."
                );

                return;
            }


            Connection con = getConnection();

            String sql =
                "SELECT * FROM EmployeeRegistration " +
                "WHERE eName = ? AND pword = ?";

            PreparedStatement pstmt =
                con.prepareStatement(sql);

            pstmt.setString(1, eName);
            pstmt.setString(2, pword);

            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {

                System.out.println(
                    "\nLogin successful!"
                );

                employeeMenu(eName);

            } else {

                System.out.println(
                    "Invalid employee name or password."
                );
            }


            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {

            System.out.println(
                "Login failed."
            );

            e.printStackTrace();
        }
    }


    // =====================================================
    // EMPLOYEE MENU
    // =====================================================

    void employeeMenu(String eName) {

        while (true) {

            System.out.println(
                "\n========== EMPLOYEE MENU =========="
            );

            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");

            String choice = scn.nextLine();

            switch (choice) {

                case "1":
                    viewProfile(eName);
                    break;

                case "2":
                    updateProfile(eName);
                    break;

                case "3":

                    System.out.println(
                        "Thank you. Application terminated."
                    );

                    return;

                default:

                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }
        }
    }


    // =====================================================
    // VIEW PROFILE
    // =====================================================

    void viewProfile(String eName) {

        System.out.println(
            "\n========== EMPLOYEE PROFILE =========="
        );

        try {

            Connection con = getConnection();

            String sql =
                "SELECT eName, fname, lname, addr, mid, phN " +
                "FROM EmployeeRegistration " +
                "WHERE eName = ?";

            PreparedStatement pstmt =
                con.prepareStatement(sql);

            pstmt.setString(1, eName);

            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {

                System.out.println(
                    "Employee Name : " +
                    rs.getString("eName")
                );

                System.out.println(
                    "First Name    : " +
                    rs.getString("fname")
                );

                System.out.println(
                    "Last Name     : " +
                    rs.getString("lname")
                );

                System.out.println(
                    "Address       : " +
                    rs.getString("addr")
                );

                System.out.println(
                    "Middle Initial: " +
                    rs.getString("mid")
                );

                System.out.println(
                    "Phone Number  : " +
                    rs.getString("phN")
                );

            } else {

                System.out.println(
                    "Employee profile not found."
                );
            }


            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // UPDATE PROFILE
    // =====================================================

    void updateProfile(String eName) {

        System.out.println(
            "\n========== UPDATE PROFILE =========="
        );

        try {

            System.out.print("Enter New Address: ");
            String addr = scn.nextLine().trim();

            System.out.print("Enter New Middle Initial: ");
            String mid = scn.nextLine().trim();

            System.out.print("Enter New Phone Number: ");
            String phN = scn.nextLine().trim();


            // Validation

            if (addr.isEmpty() ||
                mid.isEmpty() ||
                phN.isEmpty()) {

                System.out.println(
                    "Error: All fields are required."
                );

                return;
            }


            if (phN.length() != 10 ||
                !phN.matches("[0-9]+")) {

                System.out.println(
                    "Error: Enter a valid 10-digit phone number."
                );

                return;
            }


            Connection con = getConnection();

            String sql =
                "UPDATE EmployeeRegistration " +
                "SET addr = ?, mid = ?, phN = ? " +
                "WHERE eName = ?";

            PreparedStatement pstmt =
                con.prepareStatement(sql);

            pstmt.setString(1, addr);
            pstmt.setString(2, mid);
            pstmt.setString(3, phN);
            pstmt.setString(4, eName);

            int rowCount = pstmt.executeUpdate();


            if (rowCount > 0) {

                System.out.println(
                    "Profile updated successfully."
                );

            } else {

                System.out.println(
                    "Profile update failed."
                );
            }


            pstmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        EmployeeRegistrationApp obj =
            new EmployeeRegistrationApp();


        while (true) {

            System.out.println(
                "\n======================================"
            );

            System.out.println(
                "       EMPLOYEE REGISTRATION SYSTEM"
            );

            System.out.println(
                "======================================"
            );

            System.out.println("1. Employee Registration");
            System.out.println("2. Employee Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");

            String choice = obj.scn.nextLine();


            switch (choice) {

                case "1":

                    obj.registration();
                    break;

                case "2":

                    obj.login();
                    break;

                case "3":

                    System.out.println(
                        "Thank you for using Employee Registration System."
                    );

                    obj.scn.close();

                    return;

                default:

                    System.out.println(
                        "Invalid choice. Please enter 1, 2 or 3."
                    );
            }
        }
    }
}