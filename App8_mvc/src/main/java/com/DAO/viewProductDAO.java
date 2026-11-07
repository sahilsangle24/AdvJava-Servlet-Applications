package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.ProductBean;

public class viewProductDAO {
	
	ArrayList<ProductBean> al= new ArrayList<ProductBean>();
	
	public ArrayList<ProductBean>getProductData()
	{
		try
		{
			Connection con=DBConnection.getCon();
			PreparedStatement pstmt=con.prepareStatement("select * from product");
			ResultSet rs= pstmt.executeQuery();
			while(rs.next())
			{
				ProductBean pb=new ProductBean();
				pb.setProductCode(rs.getString(1));
				pb.setProductName(rs.getString(2));
				pb.setProductCompany(rs.getString(3));
				pb.setProductPrice(rs.getString(4));;
				pb.setProductQuantity(rs.getString(5));
				
				
				al.add(pb);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return al;
				
	}
}
