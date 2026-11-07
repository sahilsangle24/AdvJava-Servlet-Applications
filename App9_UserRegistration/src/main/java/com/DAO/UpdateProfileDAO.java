
package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.Bean.UserBean;

public class UpdateProfileDAO {

    public int updateProfile(UserBean ub) {
        int rowCount = 0;

        try {
            Connection con = DBConnection.getCon();

            PreparedStatement pstmt = con.prepareStatement(
                "UPDATE registration SET FirstName=?, LastName=?, MailID=?, phone=? WHERE UserName=?"
            );

            pstmt.setString(1, ub.getFIRSTNAME());
            pstmt.setString(2, ub.getLASTNAME());
            pstmt.setString(3, ub.getMAILID());
            pstmt.setString(4, ub.getPHONE());
            pstmt.setString(5, ub.getUSERNAME());

            rowCount = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCount;
    }
}
