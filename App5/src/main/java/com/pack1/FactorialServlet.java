package com.pack1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/fs")
public class FactorialServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        int number = Integer.parseInt(req.getParameter("num"));

        int fact = 1;

        for (int i = 1; i <= number; i++) {
            fact = fact * i;
        }

        res.setContentType("text/html");

        PrintWriter pw = res.getWriter();

        pw.println("<center>");
        pw.println("<h1>");
        pw.println("The factorial is: " + fact);
        pw.println("<br><br>");
        pw.println("</h1>");
        pw.println("</center>");

        RequestDispatcher rd = req.getRequestDispatcher("input.html");
        rd.include(req, res);
    }
}