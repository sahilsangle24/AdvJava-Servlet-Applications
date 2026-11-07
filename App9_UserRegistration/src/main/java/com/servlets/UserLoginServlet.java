package com.servlets;

import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import com.Bean.UserBean;
import com.DAO.UserLoginDAO;


@WebServlet("/login")
public class UserLoginServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		UserBean ub=new UserLoginDAO().checkLogin(req.getParameter("uname"), req.getParameter("upwd"));
	    if(ub==null)
	    {

	    	req.setAttribute("msg", "Invalid Credential");
	    	req.getRequestDispatcher("Register.jsp").forward(req, res);
	    }
	    else
	    {
	    	ServletContext context =req.getServletContext();
	    	context.setAttribute("ubean", ub);
	    	Cookie ck=new Cookie("fname",ub.getUSERNAME());
	    	res.addCookie(ck);
	    	req.getRequestDispatcher("Home.jsp").forward(req, res);



	    }


	}


}