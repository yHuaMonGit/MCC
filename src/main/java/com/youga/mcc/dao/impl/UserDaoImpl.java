package com.youga.mcc.dao.impl;

import com.youga.mcc.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean allowAccess(String acc, String pass) {
        String getCode = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select passwd from youga_pet.mcc_admin_base_info where adminid = \"" + acc + "\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return false;
            }
            while(rs.next()){
                getCode  = rs.getString("passwd");
            }

            BaseDao.closeAll(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (getCode.equals(pass)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String getShopIdByUser(String acc) {
        String shopid = null;
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select shopid from youga_pet.mcc_admin_base_info where adminid = \"" + acc + "\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }
            while(rs.next()){
                shopid  = rs.getString("shopid");
            }

            BaseDao.closeAll(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return shopid;
    }
}
