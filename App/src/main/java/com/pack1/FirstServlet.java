package com.pack1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/fs")

public class FirstServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {

        PrintWriter pw = res.getWriter();

        res.setContentType("text/html");

        String u_name = req.getParameter("uname");
        String u_pwd = req.getParameter("upwd");

        System.out.println("username :" + u_name);
        System.out.println("password :" + u_pwd);

        pw.println("<center><h1>");

        pw.println("****** user data ********<br><br>");

        pw.println("username :" + u_name + "<br><br>");

        pw.println("userpassword :" 
                + u_pwd.substring(0, 1) 
                + "*****" 
                + u_pwd.substring(u_pwd.length() - 1) 
                + "<br><br>");
    }
}