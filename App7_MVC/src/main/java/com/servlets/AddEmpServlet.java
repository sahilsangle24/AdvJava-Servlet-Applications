package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.EmpBean;
import com.DAO.AddEmpDAO;

@WebServlet("/aes")
public class AddEmpServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        EmpBean eb = new EmpBean();

        eb.setEmpId(req.getParameter("eId"));
        eb.setEmpFname(req.getParameter("eFname"));
        eb.setEmpLname(req.getParameter("eLname"));
        eb.setEmpSal(Integer.parseInt(req.getParameter("eSal")));
        eb.setEmpAddr(req.getParameter("eAddr"));

        int rowCount = new AddEmpDAO().insertEmpData(eb);

        if(rowCount == 0)
        {
            req.setAttribute("msg", "Employee data not inserted");
        }
        else
        {
            req.setAttribute("msg", "Employee data inserted successfully");
        }

        RequestDispatcher rd = req.getRequestDispatcher("AddEmployee.jsp");
        rd.forward(req, res);
    }
}