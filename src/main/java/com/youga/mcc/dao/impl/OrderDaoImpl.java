package com.youga.mcc.dao.impl;

import com.youga.mcc.dao.OrderDao;
import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.GoodsBase;
import com.youga.mcc.obj.OrderInfo;
import com.youga.mcc.obj.OrderNotesBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean insertOrderInfo(OrderInfo orderInfo, String shopid) {
        String seach_table = "youga_pet.offline_order_tobe_paid_info_"+shopid;
        Connection conn = null;
        PreparedStatement stmt = null;
        String goodsList = orderInfo.getGoodsListStr();
        String sql=null;

        try {
            conn = BaseDao.getConnection();

            sql = "insert into "+seach_table+" (orderId,orderOutTime,msisdn,memberID,memberLevel," +
                    "memberFlag,iniAmount,actAmount,activeOffType,activeOff,goodslist,goodsRemarks,decAmount,decAuthor,paymentType) " +
                    "value ( \"" +orderInfo.getOrderId()+"\""+
                    ",\"" +orderInfo.getOrderTime()+"\""+
                    ",\"" +orderInfo.getMsisdn()+"\""+
                    ",\"" +orderInfo.getMemberID()+"\""+
                    ",\"" +orderInfo.getMemberLevel()+"\""+
                    ",\"" +orderInfo.getMemberFlag()+"\""+
                    ",\"" +orderInfo.getMoneyAmount()+"\""+
                    ",\"" +orderInfo.getActiveAmount()+"\""+
                    ",\"" +orderInfo.getActiveType()+"\""+
                    ",\"" +orderInfo.getActiveOff()+
                    "\",\"" +goodsList+
                    "\",\"" +orderInfo.getNote()+
                    "\",\"" +orderInfo.getDecAmount()+
                    "\",\"" +orderInfo.getDecAuthor()+
                    "\",\"" +orderInfo.getPaymentWay()+
                    "\");";

            System.out.println(sql);

            stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }

        //插入商品-订单信息
        insertGoods(orderInfo.getOrderId(),orderInfo.getGoodsListStr());

        return true;

    }

    @Override
    public boolean checkNowOrder(String shopid) {
        List<GoodsBase> storeList = new ArrayList<>();

        String seach_table = "youga_pet.portal_store_order_prepayment_notes";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Boolean result = false;

        try {
            conn = BaseDao.getConnection();
            String sql = "select count(*) from  "+seach_table+" where ischecked = 0 and order_status = 1 and shop_id = \""+shopid+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                BaseDao.closeAll(conn, stmt);
                return false;
            }
            while(rs.next())
            {
                int count = rs.getInt(1);
                if (count>0){
                    result = true;
                }
            }
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return result;

    }

    @Override
    public List<OrderNotesBase> checkTimeOutOrder() {
        List<OrderNotesBase> storeList = new ArrayList<>();

        String seach_table = "youga_pet.portal_store_order_prepayment_notes";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Boolean result = false;

        try {
            conn = BaseDao.getConnection();
            String sql = "select  order_no,order_amount_total,member_id from  "+seach_table+" where order_status = 0 and now() >SUBDATE(createTime,interval -15 minute);;";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                BaseDao.closeAll(conn, stmt);
                return null;
            }
            while(rs.next())
            {
                OrderNotesBase orderNotesBase = new OrderNotesBase();
                orderNotesBase.setOrder_no(rs.getString("order_no"));
                orderNotesBase.setMember_id(rs.getString("member_id"));
                orderNotesBase.setOrder_amount_total(rs.getString("order_amount_total"));
                storeList.add(orderNotesBase);
            }
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return storeList;
    }

    @Override
    public void deleteOrder(String order_no) {
        String seach_table = "youga_pet.portal_store_order_prepayment_notes";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "delete from "+seach_table+" where order_no = \""+order_no+"\";";
            stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public FlatFormInfo getDayLayMsg(String todayFormat) {

        String seach_table = "youga_pet.offline_order_tobe_paid_info_yg000001";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String cashAmount = "";
        String mobileAmount = "";
        FlatFormInfo flatFormInfo = new FlatFormInfo();

        Boolean result = false;

        try {
            conn = BaseDao.getConnection();
            String sql = "select  sum(actAmount) from  "+seach_table+" where orderOutTime like '%"+todayFormat+"%' and paymentType = 1 ;";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();

            if (rs.next())
            {
                rs.previous();
            }
            else {
                BaseDao.closeAll(conn, stmt);
                return null;
            }
            while(rs.next())
            {
                cashAmount = rs.getString(1);
            }

            sql = "select sum(chargeAmount) from member_charge_list where chargeDT like '%"+todayFormat+"%' and chargeType = 1;";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                BaseDao.closeAll(conn, stmt);
                return null;
            }
            while(rs.next())
            {
                String tmpRst = rs.getString(1);
                if (null!=tmpRst&&!"".equals(tmpRst)&&!"0".equals(tmpRst))
                {
                    cashAmount = String.valueOf(Float.valueOf(tmpRst)+Float.valueOf(cashAmount));
                }else
                    {
                        cashAmount = cashAmount;
                    }

            }



            //计算POS机收入
            sql = "select  sum(actAmount) from  "+seach_table+" where orderOutTime like '%"+todayFormat+"%' and paymentType = 2 ;";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                BaseDao.closeAll(conn, stmt);
                return null;
            }
            while(rs.next())
            {
                mobileAmount = rs.getString(1);
            }


            sql = "select sum(chargeAmount) from member_charge_list where chargeDT like '%"+todayFormat+"%' and chargeType = 2;";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                BaseDao.closeAll(conn, stmt);
                return null;
            }
            while(rs.next())
            {
                String tmpRst = rs.getString(1);
                if (null!=tmpRst&&!"".equals(tmpRst)&&!"0".equals(tmpRst))
                {
                    mobileAmount = String.valueOf(Float.valueOf(tmpRst)+Float.valueOf(mobileAmount));
                }else
                {
                    mobileAmount = mobileAmount;
                }
            }
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("cashAmount",cashAmount);
        resultMap.put("mobileCashAmount",mobileAmount);
        flatFormInfo.setValues(resultMap);

        return flatFormInfo;

    }

    private void insertGoods(String orderId, String goodsListStr) {

        //通过存储过程进行添加
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = BaseDao.getConnection();


            String [] goodsArr = goodsListStr.split(",");

            for (String gid : goodsArr){

                CallableStatement c=conn.prepareCall("{call InsertOD_GOODS(?,?)}");
                c.setString(1,orderId);
                c.setString(2,gid);
                c.execute();

            }

            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {

            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
}
