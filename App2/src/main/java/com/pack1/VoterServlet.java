package com.pack1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/vs")
public class VoterServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("VNAME");
        name = name.toUpperCase();

        int age = Integer.parseInt(req.getParameter("vAge"));

        String city = req.getParameter("vCity");

        res.setContentType("text/html");

        PrintWriter pw = res.getWriter();

        if(age >= 18)
        {
        	pw.println("<center>");
            pw.println("<h1>");
            pw.println("<h1>" + name + " you can Vote</h1>");
            pw.println("<br><br>");
            pw.println("</h1>");
            pw.println("</center>");
            
        }
        else
        {
        	pw.println("<center>");
            pw.println("<h1>");
            pw.println("<h1>" + name + " you are NOT Eligible to vote</h1>");
            pw.println("<br><br>");
            pw.println("</h1>");
            pw.println("</center>");
         
        }
        
        RequestDispatcher rd = req.getRequestDispatcher("voter.html");
        rd.include(req, res);
    }
}