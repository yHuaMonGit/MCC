package com.youga.mcc.dao.impl;

import com.youga.mcc.dao.MerchantDao;
import com.youga.mcc.obj.MerchantInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MerchantDaoImpl implements MerchantDao {
    @Override
    public MerchantInfo getMerchantBaseInfo(String shopid) {
        Connection conn = null;
        MerchantInfo merchant = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.mcc_shop_base_info where shopid = \""+shopid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }

            while(rs.next())
            {
                merchant =new MerchantInfo(
                        rs.getString("shopid"),
                        rs.getString("shopname"),
                        rs.getString("longitude"),
                        rs.getString("latitude"),
                        rs.getString("medicalFlag"),
                        rs.getString("serviceFlag"),
                        rs.getString("shopFlag")
                );

            }
            BaseDao.closeAll(conn, stmt, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return merchant;
}

    @Override
    public boolean checkMerchantId(String merchantid) {
        Connection conn = null;
        MerchantInfo merchant = null;
        try {
            conn = BaseDao.getConnection();
            String sql = "select * from youga_pet.mcc_shop_base_info where shopid = \""+merchantid+"\";";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                return false;
            }
            BaseDao.closeAll(conn, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
