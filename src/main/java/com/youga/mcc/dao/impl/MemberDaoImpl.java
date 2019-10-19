package com.youga.mcc.dao.impl;

import com.youga.mcc.dao.MemberDao;
import com.youga.mcc.obj.MemberInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
    @Override
    public List<MemberInfo> getAllMemberByMerchantId(String shopid) {
        List<MemberInfo> memberlist = new ArrayList<>();

        String seach_table = "youga_pet.member_offline_info_"+shopid;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  "+seach_table+" ;";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }
            while(rs.next())
            {
                /***
                 * rs.getString("memberID"),
                 *                         rs.getString("username"),
                 *                         rs.getString("memberLevel"),
                 *                         rs.getString("integral"),
                 *                         rs.getString("memberFlag"),
                 *                         rs.getString("msisdn"),
                 *                         rs.getString("Amount"),
                 *                         rs.getString("balance"),
                 *                         rs.getString("serviceOff"),
                 *                         rs.getString("goodsOff")
                 */

                MemberInfo member = new MemberInfo();
                member.setMemberID(rs.getString("memberID"));
                member.setMemberName(rs.getString("username"));
                member.setMsisdn(rs.getString("msisdn"));
                member.setIntegral(rs.getString("integral"));
                member.setBalance(rs.getString("balance"));
                member.setPetName(rs.getString("petName"));
                member.setGoodsOff(rs.getString("goodsOff"));
                member.setServiceOff(rs.getString("serviceOff"));
                memberlist.add(member);
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberlist;
    }

    @Override
    public MemberInfo getMemberInfoByMsisdn(String msisdn) {
        MemberInfo member =null;

        String seach_table = "youga_pet.baking_member_info";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  "+seach_table+" where msisdn = \""+msisdn+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                return null;
            }
            while(rs.next())
            {
                 member = new MemberInfo();
                member.setMemberID(rs.getString("memberID"));
                member.setMemberName(rs.getString("username"));
                member.setMsisdn(rs.getString("msisdn"));
                member.setIntegral(rs.getString("integral"));
                member.setBalance(rs.getString("balance"));
                member.setPetName(rs.getString("petName"));
                member.setGoodsOff(rs.getString("goodsOff"));
                member.setServiceOff(rs.getString("serviceOff"));
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public void updateMemberInfo(String msisdn, String balance, String integral) {
        Boolean returnCode=false;
        String seach_table = "youga_pet.baking_member_info";
        Connection conn = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update "+seach_table+" set balance = \""
                    +balance+"\" , integral =\""+integral+
                    "\" Where msisdn = \""+msisdn+"\";";

            //System.out.println(sql);
            PreparedStatement stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
