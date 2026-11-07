
package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditProfileServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Cookie arr[] = req.getCookies();
        String fname = null;

        if (arr == null) {

            req.setAttribute("msg", "Session expired!!");
            req.getRequestDispatcher("Register.jsp").forward(req, res);
            return;
        }

        for (Cookie ck : arr) {

            if ("fname".equals(ck.getName())) {
                fname = ck.getValue();
                break;
            }
        }

        if (fname == null) {

            req.setAttribute("msg", "Session expired!!");
            req.getRequestDispatcher("Register.jsp").forward(req, res);
            return;
        }

        req.setAttribute("fname", fname);
        req.getRequestDispatcher("EditProfile.jsp").forward(req, res);
    }
}
