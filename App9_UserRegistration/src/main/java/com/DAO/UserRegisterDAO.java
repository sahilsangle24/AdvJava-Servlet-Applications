package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.Bean.UserBean;

public class UserRegisterDAO {

    public int insertUserData(UserBean ub) {

        int rowCount = 0;

        try {
            Connection con = DBConnection.getCon();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO registration VALUES (?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, ub.getUSERNAME());
            ps.setString(2, ub.getPASSWORD());
            ps.setString(3, ub.getFIRSTNAME());
            ps.setString(4, ub.getLASTNAME());
            ps.setString(5, ub.getMAILID());
            ps.setString(6, ub.getPHONE());

            rowCount = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCount;
    }
}