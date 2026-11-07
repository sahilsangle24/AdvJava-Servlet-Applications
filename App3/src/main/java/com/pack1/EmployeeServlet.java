package com.pack1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/es")
public class EmployeeServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        PrintWriter pw = res.getWriter();

        res.setContentType("text/html");

        String name = req.getParameter("eName");
        String id = req.getParameter("eId");

        int sal = Integer.parseInt(req.getParameter("eSal"));
        int exp = Integer.parseInt(req.getParameter("eExp"));

        if (exp > 5) {
            sal += sal * 0.1;
        }

        pw.println("<center><h1>");

        pw.println("Employee name : " + name + "<br><br>");
        pw.println("Employee Id : " + id + "<br><br>");
        pw.println("Employee salary : " + sal + "<br><br>");
        pw.println("Employee experience : " + exp + "<br><br>");

        pw.println("</h1></center>");
    }
}