package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.AddProductDAO;
import com.bean.ProductBean;

@WebServlet("/pes")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        ProductBean pb = new ProductBean();

        pb.setProductCode(req.getParameter("pcode"));
        pb.setProductName(req.getParameter("pName"));
        pb.setProductCompany(req.getParameter("Pcompany"));
        pb.setProductPrice(req.getParameter("pPrice"));
        pb.setProductQuantity(req.getParameter("pQty"));

        int rowCount =
                new AddProductDAO().insertProductData(pb);

        if (rowCount == 0) {
            req.setAttribute("msg", "Product data not inserted");
        } else {
            req.setAttribute("msg", "Product data inserted successfully");
        }

        RequestDispatcher rd =
                req.getRequestDispatcher("AddProduct.jsp");

        rd.forward(req, res);
    }
}