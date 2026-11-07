package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.bean.ProductBean;

public class AddProductDAO {

	public int insertProductData(ProductBean pb) {
		
		int rowCount = 0;

		try
		{
			Connection con = DBConnection.getCon();
			
			PreparedStatement pstmt=con.prepareStatement(
					"insert into product values(?,?,?,?,?)");
			
			pstmt.setString(1, pb.getProductCode());
            pstmt.setString(2, pb.getProductName());
			pstmt.setString(3, pb.getProductCompany());
			pstmt.setString(4, pb.getProductPrice());
			pstmt.setString(5, pb.getProductQuantity());
			
	
			rowCount=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		    throw new RuntimeException(e);
		}
		return rowCount;
	}
	
}
