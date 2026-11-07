package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/view")
public class ViewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Cookie arr[] = req.getCookies();
        String fname = null;

        if (arr == null) {
            req.setAttribute("msg", "Session expired, please login again");
            req.getRequestDispatcher("login.jsp").forward(req, res);
        } 
        else {
            for (Cookie ck : arr) {

                if ("fname".equals(ck.getName())) {
                    fname = ck.getValue();
                }
            }

            req.setAttribute("fname", fname);
            req.getRequestDispatcher("viewprofile.jsp").forward(req, res);
        }
    }
}