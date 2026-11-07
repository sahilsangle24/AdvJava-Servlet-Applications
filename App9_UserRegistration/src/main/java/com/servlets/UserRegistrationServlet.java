package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.UserBean;
import com.DAO.UserRegisterDAO;

@WebServlet("/reg")
public class UserRegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        UserBean ub = new UserBean();

        ub.setUSERNAME(req.getParameter("uname"));
        ub.setPASSWORD(req.getParameter("upwd"));
        ub.setFIRSTNAME(req.getParameter("ufname"));
        ub.setLASTNAME(req.getParameter("ulname"));
        ub.setMAILID(req.getParameter("umail"));
        ub.setPHONE(req.getParameter("uphone"));

        int rowCount = new UserRegisterDAO().insertUserData(ub);

        if (rowCount == 0) {

            throw new RuntimeException("User data insertion failed");

        } else {

            req.setAttribute("msg", "User Registration Successful");

            req.getRequestDispatcher("Register.jsp").forward(req, res);
        }
    }
}