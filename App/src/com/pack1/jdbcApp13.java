package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

public class jdbcApp13 {

    // =========================================================
    // DATABASE CONNECTION DETAILS
    // =========================================================

    private String driver = "oracle.jdbc.OracleDriver";

    private String DBurl =
            "jdbc:oracle:thin:@localhost:1521:free";

    private String DBuname = "system";

    private String DBpwd = "sahil";


    // =========================================================
    // TASK 1 : BOOK TICKET
    // =========================================================

    // Deduct one seat
    String sqlquery1 =
            "UPDATE movieseatavailability "
            + "SET available_seats = available_seats - 1 "
            + "WHERE movie_id = ? "
            + "AND movie_name = ? "
            + "AND available_seats > 0";


    // Insert booking details
    String sqlquery2 =
            "INSERT INTO moviebookingdetails VALUES (?,?,?,?,?)";


    // =========================================================
    // TASK 2 : CHECK AVAILABILITY
    // =========================================================

    String sqlquery3 =
            "SELECT available_seats "
            + "FROM movieseatavailability "
            + "WHERE movie_id = ?";


    // =========================================================
    // TASK 3 : HANDLE PAYMENT STATUS
    // =========================================================

    // Check payment status
    String sqlquery4 =
            "SELECT payment_status "
            + "FROM customerpayment "
            + "WHERE customer_id = ?";


    // Confirm booking
    String sqlquery5 =
            "UPDATE moviebookingdetails "
            + "SET status = 'Confirmed' "
            + "WHERE booking_id = ?";


    // Release seat
    String sqlquery6 =
            "UPDATE movieseatavailability "
            + "SET available_seats = available_seats + 1 "
            + "WHERE movie_id = ?";


    // =========================================================
    // TASK 4 : CANCEL TICKET
    // =========================================================

    // Check booking
    String sqlquery7 =
            "SELECT movie_id, status "
            + "FROM moviebookingdetails "
            + "WHERE booking_id = ? "
            + "AND customer_id = ?";


    // Delete booking if NOT confirmed
    String sqlquery8 =
            "DELETE FROM moviebookingdetails "
            + "WHERE booking_id = ? "
            + "AND customer_id = ?";


    // Change Confirmed → Cancelled
    String sqlquery9 =
            "UPDATE moviebookingdetails "
            + "SET status = 'Cancelled' "
            + "WHERE booking_id = ? "
            + "AND customer_id = ?";


    // Increase available seats
    String sqlquery10 =
            "UPDATE movieseatavailability "
            + "SET available_seats = available_seats + 1 "
            + "WHERE movie_id = ?";


    // =========================================================
    // DATABASE CONNECTION
    // =========================================================

    public Connection connect() {

        System.out.println("Loading a driver");

        Connection con = null;

        try {

            Class.forName(driver);

            con = DriverManager.getConnection(
                    DBurl,
                    DBuname,
                    DBpwd
            );

            System.out.println("Connection created");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return con;
    }


    // =========================================================
    // TASK 1 : BOOK TICKET
    // =========================================================

    void bookticket() {

        System.out.println(
                "\n========== TASK 1 : BOOK TICKET ==========");

        try {

            Connection con = connect();

            // Disable auto commit
            con.setAutoCommit(false);


            // -------------------------------------------------
            // STEP 1 : LOCK / DEDUCT SEAT
            // -------------------------------------------------

            PreparedStatement pstmt1 =
                    con.prepareStatement(sqlquery1);

            pstmt1.setString(1, "m101");
            pstmt1.setString(2, "king");

            int rowCount =
                    pstmt1.executeUpdate();


            // If no seat is available
            if (rowCount == 0) {

                throw new RuntimeException(
                        "Seats are not available");

            } else {

                System.out.println(
                        "Seat is locked");


                // -------------------------------------------------
                // STEP 2 : CREATE SAVEPOINT
                // -------------------------------------------------

                Savepoint sp =
                        con.setSavepoint();

                System.out.println(
                        "Savepoint created");


                // -------------------------------------------------
                // STEP 3 : INSERT BOOKING
                // -------------------------------------------------

                PreparedStatement pstmt2 =
                        con.prepareStatement(sqlquery2);

                pstmt2.setString(1, "12345");
                pstmt2.setString(2, "m101");
                pstmt2.setString(3, "c123");
                pstmt2.setInt(4, 15);
                pstmt2.setString(5, "pending");


                int rowCount2 =
                        pstmt2.executeUpdate();


                if (rowCount2 == 0) {

                    throw new RuntimeException(
                            "Booking record not created");

                } else {

                    System.out.println(
                            "Booking record created");
                }


                // =================================================
                // TASK 3 : PAYMENT STATUS
                // =================================================

                System.out.println(
                        "Waiting for payment confirmation");


                // -------------------------------------------------
                // STEP 4 : CHECK PAYMENT
                // -------------------------------------------------

                PreparedStatement pstmt3 =
                        con.prepareStatement(sqlquery4);

                pstmt3.setString(1, "c123");

                ResultSet rs =
                        pstmt3.executeQuery();


                if (rs.next()) {

                    String paymentStatus =
                            rs.getString("payment_status");


                    System.out.println(
                            "Payment Status : "
                            + paymentStatus);


                    // =============================================
                    // PAYMENT SUCCESS
                    // =============================================

                    if (paymentStatus.equalsIgnoreCase(
                            "Success")) {


                        PreparedStatement pstmt4 =
                                con.prepareStatement(
                                        sqlquery5);


                        pstmt4.setString(
                                1,
                                "12345");


                        pstmt4.executeUpdate();


                        System.out.println(
                                "Booking status updated to Confirmed");


                        // Commit entire transaction
                        con.commit();


                        System.out.println(
                                "Transaction committed");


                        pstmt4.close();


                    }


                    // =============================================
                    // PAYMENT FAILED
                    // =============================================

                    else {

                        System.out.println(
                                "Payment failed");


                        // Rollback to savepoint
                        con.rollback(sp);


                        System.out.println(
                                "Rolled back to savepoint");


                        // -----------------------------------------
                        // Release the seat
                        // -----------------------------------------

                        PreparedStatement pstmt5 =
                                con.prepareStatement(
                                        sqlquery6);


                        pstmt5.setString(
                                1,
                                "m101");


                        pstmt5.executeUpdate();


                        System.out.println(
                                "Seat released");


                        // Commit seat release
                        con.commit();


                        System.out.println(
                                "Seat release committed");


                        pstmt5.close();
                    }


                } else {

                    // =============================================
                    // PAYMENT RECORD NOT FOUND
                    // =============================================

                    System.out.println(
                            "Payment record not found");


                    con.rollback(sp);


                    System.out.println(
                            "Rolled back to savepoint");


                    // Release seat
                    PreparedStatement pstmt5 =
                            con.prepareStatement(
                                    sqlquery6);


                    pstmt5.setString(
                            1,
                            "m101");


                    pstmt5.executeUpdate();


                    System.out.println(
                            "Seat released");


                    con.commit();


                    System.out.println(
                            "Seat release committed");


                    pstmt5.close();
                }


                rs.close();
                pstmt3.close();
                pstmt2.close();
            }


            pstmt1.close();
            con.close();


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // TASK 2 : CHECK AVAILABILITY
    // =========================================================

    void checkAvailability(String movieId) {

        System.out.println(
                "\n========== TASK 2 : CHECK AVAILABILITY ==========");


        try {

            Connection con = connect();


            PreparedStatement pstmt =
                    con.prepareStatement(sqlquery3);


            // First ? = movieId
            pstmt.setString(1, movieId);


            ResultSet rs =
                    pstmt.executeQuery();


            if (rs.next()) {

                int availableSeats =
                        rs.getInt("available_seats");


                System.out.println(
                        "Movie ID : " + movieId);


                System.out.println(
                        "Available Seats : "
                        + availableSeats);


            } else {

                System.out.println(
                        "Movie not found");
            }


            rs.close();
            pstmt.close();
            con.close();


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // TASK 4 : CANCEL TICKET
    // =========================================================

    void cancelTicket(
            String bookingId,
            String customerId) {


        System.out.println(
                "\n========== TASK 4 : CANCEL TICKET ==========");


        try {

            Connection con = connect();


            con.setAutoCommit(false);


            // -------------------------------------------------
            // STEP 1 : CHECK BOOKING
            // -------------------------------------------------

            PreparedStatement pstmt1 =
                    con.prepareStatement(sqlquery7);


            // First ? = bookingId
            pstmt1.setString(1, bookingId);


            // Second ? = customerId
            pstmt1.setString(2, customerId);


            ResultSet rs =
                    pstmt1.executeQuery();


            if (rs.next()) {


                String movieId =
                        rs.getString("movie_id");


                String status =
                        rs.getString("status");


                System.out.println(
                        "Booking ID : "
                        + bookingId);


                System.out.println(
                        "Status : "
                        + status);


                // =================================================
                // CASE 1 : BOOKING IS NOT CONFIRMED
                // =================================================

                if (!status.equalsIgnoreCase(
                        "Confirmed")) {


                    PreparedStatement pstmt2 =
                            con.prepareStatement(
                                    sqlquery8);


                    pstmt2.setString(
                            1,
                            bookingId);


                    pstmt2.setString(
                            2,
                            customerId);


                    pstmt2.executeUpdate();


                    System.out.println(
                            "Booking record deleted");


                    con.commit();


                    System.out.println(
                            "Cancellation committed");


                    pstmt2.close();
                }


                // =================================================
                // CASE 2 : BOOKING IS CONFIRMED
                // =================================================

                else {


                    // ---------------------------------------------
                    // STEP 2 : CHANGE STATUS TO CANCELLED
                    // ---------------------------------------------

                    PreparedStatement pstmt3 =
                            con.prepareStatement(
                                    sqlquery9);


                    pstmt3.setString(
                            1,
                            bookingId);


                    pstmt3.setString(
                            2,
                            customerId);


                    pstmt3.executeUpdate();


                    System.out.println(
                            "Booking status changed to Cancelled");


                    // ---------------------------------------------
                    // STEP 3 : RELEASE SEAT
                    // ---------------------------------------------

                    PreparedStatement pstmt4 =
                            con.prepareStatement(
                                    sqlquery10);


                    pstmt4.setString(
                            1,
                            movieId);


                    pstmt4.executeUpdate();


                    System.out.println(
                            "Seat released");


                    // ---------------------------------------------
                    // STEP 4 : COMMIT
                    // ---------------------------------------------

                    con.commit();


                    System.out.println(
                            "Cancellation committed");


                    pstmt3.close();
                    pstmt4.close();
                }


            } else {


                // =================================================
                // BOOKING NOT FOUND
                // =================================================

                System.out.println(
                        "Booking not found");


                con.rollback();
            }


            rs.close();
            pstmt1.close();
            con.close();


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {


        jdbcApp13 obj =
                new jdbcApp13();


        // =====================================================
        // TASK 1 : BOOK TICKET
        // =====================================================

        obj.bookticket();


        // =====================================================
        // TASK 2 : CHECK AVAILABILITY
        // =====================================================

        obj.checkAvailability("m101");


        // =====================================================
        // TASK 4 : CANCEL TICKET
        // =====================================================

        obj.cancelTicket(
                "12345",
                "c123");
    }
}