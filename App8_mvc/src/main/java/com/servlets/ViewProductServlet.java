package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.viewProductDAO;
import com.bean.ProductBean;

@WebServlet("/view")
public class ViewProductServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		ArrayList<ProductBean> al= new viewProductDAO().getProductData();
		
		req.setAttribute("data", al);
		req.getRequestDispatcher("ViewProduct.jsp").forward(req, res);

}
}