package com.pack1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/es")
public class EvenServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        int number = Integer.parseInt(req.getParameter("num"));

        res.setContentType("text/html");

        PrintWriter pw = res.getWriter();

        pw.println("<center>");
        pw.println("<h1>");

        if (number % 2 == 0) {
            pw.println(number + " Number is Even");
        } 
        else {
            pw.println(number + " Number is Odd");
        }

        pw.println("</h1>");
        pw.println("</center>");

        // Display input.html below the result
        RequestDispatcher rd = req.getRequestDispatcher("input.html");
        rd.include(req, res);
    }
}