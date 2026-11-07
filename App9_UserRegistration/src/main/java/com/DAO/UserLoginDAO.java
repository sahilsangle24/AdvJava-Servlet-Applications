package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Bean.UserBean;

public class UserLoginDAO {

	public UserBean checkLogin(String username, String password)
	{
		UserBean ub=null;
		try
		{
			Connection con = DBConnection.getCon();
			PreparedStatement pstmt=con.prepareStatement("select * from registration where username=? and password=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
            	ub=new UserBean();
            	ub.setUSERNAME(rs.getString(1));
            	ub.setPASSWORD(rs.getString(2));
            	ub.setFIRSTNAME(rs.getString(3));
            	ub.setLASTNAME(rs.getString(4));
            	ub.setMAILID(rs.getString(5));
                ub.setPHONE(rs.getString(6));


            }


		}catch(Exception e)
		{
			e.printStackTrace();
		}


		return ub;

	}

}