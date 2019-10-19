package com.youga.mcc.dao.impl;

import com.youga.mcc.dao.StoreDao;
import com.youga.mcc.obj.GoodsBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDaoImpl implements StoreDao {
    @Override
    public List<GoodsBase> getAllStoreFromMerchant(String shopid) {

        List<GoodsBase> storeList = new ArrayList<>();

        String seach_table = "youga_pet.goods_store_list_"+shopid;

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
                BaseDao.closeAll(conn, stmt);
                return null;
            }
            while(rs.next())
            {
                GoodsBase goodsBase = new GoodsBase();
                goodsBase.setGoodsId(rs.getString("goodsid"));
                goodsBase.setGoodsName(rs.getString("goodsname"));
                goodsBase.setGoodsPrice(rs.getString("goodsprice"));
                int grat = Float.valueOf(goodsBase.getGoodsPrice()).intValue()/10;
                goodsBase.setGoodsIntergral(String.valueOf(grat));
                goodsBase.setGoodsStore(rs.getString("instock"));
                goodsBase.setGoodsCount("1");
                goodsBase.setGoodsStander(rs.getString("stander"));
                goodsBase.setGoodsClassify(rs.getString("classify"));

                storeList.add(goodsBase);
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
    public boolean modifyGoodsClassify(String merchantid, String goodsid, String clsType) {
        Boolean returnCode=false;
        String seach_table = "youga_pet.goods_store_list_"+merchantid;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update "+seach_table+" set cls = \""
                    +clsType+
                    "\" Where goodsid = \""+goodsid+"\";";

            stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            if (result == 1){
                returnCode = true;
            }else {
                returnCode = false;
            }

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
        return returnCode;
    }

    @Override
    public List<GoodsBase> getAllStoreFromClsType(String shopid, String clsType) {
        List<GoodsBase> storeList = new ArrayList<>();

        String seach_table = "youga_pet.goods_store_list_"+shopid;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  "+seach_table+" where cls = \""+clsType+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

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
                GoodsBase goodsBase = new GoodsBase();
                goodsBase.setGoodsId(rs.getString("goodsid"));
                goodsBase.setGoodsName(rs.getString("goodsname"));
                goodsBase.setGoodsPrice(rs.getString("goodsprice"));
                int grat = Float.valueOf(goodsBase.getGoodsPrice()).intValue()/10;
                goodsBase.setGoodsIntergral(String.valueOf(grat));
                goodsBase.setGoodsStore(rs.getString("instock"));
                goodsBase.setGoodsCount("1");
                goodsBase.setGoodsStander(rs.getString("stander"));
                goodsBase.setGoodsClassify(rs.getString("classify"));

                storeList.add(goodsBase);
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
    public List<GoodsBase> getAllStoreFromGoodsName(String shopid, String goodsName) throws Exception {
        List<GoodsBase> storeList = new ArrayList<>();

        String seach_table = "youga_pet.goods_store_list_"+shopid;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  "+seach_table+" where goodsName like \"%"+goodsName+"%\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

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
                GoodsBase goodsBase = new GoodsBase();
                goodsBase.setGoodsId(rs.getString("goodsid"));
                goodsBase.setGoodsName(rs.getString("goodsname"));
                goodsBase.setGoodsPrice(rs.getString("goodsprice"));
                int grat = Float.valueOf(goodsBase.getGoodsPrice()).intValue()/10;
                goodsBase.setGoodsIntergral(String.valueOf(grat));
                goodsBase.setGoodsStore(rs.getString("instock"));
                goodsBase.setGoodsCount("1");
                goodsBase.setGoodsStander(rs.getString("stander"));
                goodsBase.setGoodsClassify(rs.getString("classify"));

                storeList.add(goodsBase);
            }
            BaseDao.closeAll(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
            BaseDao.closeAll(conn, stmt);
        }
        return storeList;
    }

    @Override
    public GoodsBase getAllStoreFromGoodsId(String shopid, String goodsid) {
        GoodsBase goodsInfo = new GoodsBase();

        String seach_table = "youga_pet.goods_store_list_"+shopid;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select * from  "+seach_table+" where goodsid = \""+goodsid+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
//goodsid,goodsname,goodsprice,instock,stander,classify

            if (rs.next())
            {
                rs.previous();
            }
            else {
                BaseDao.closeAll(conn, stmt);
                return new GoodsBase();
            }

            while(rs.next())
            {
                goodsInfo.setGoodsId(rs.getString("goodsid"));
                goodsInfo.setGoodsName(rs.getString("goodsname"));
                goodsInfo.setGoodsPrice(rs.getString("goodsprice"));
                int grat = Float.valueOf(goodsInfo.getGoodsPrice()).intValue()/10;
                goodsInfo.setGoodsIntergral(String.valueOf(grat));
                goodsInfo.setGoodsStore(rs.getString("instock"));
                goodsInfo.setGoodsCount("1");
                goodsInfo.setGoodsStander(rs.getString("stander"));
                goodsInfo.setGoodsClassify(rs.getString("classify"));
                goodsInfo.setCom_shelf_life(rs.getString("com_shelf_life"));

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
        return goodsInfo;
    }

    @Override
    public boolean addAllStoreFromGoodsId(String shopid, GoodsBase goodsInfo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String seach_table = "youga_pet.goods_store_list_"+shopid;
        int result = 0;

        try {
            conn = BaseDao.getConnection();
            String sql;

            String goodsid = goodsInfo.getGoodsId();
            String goodsname = goodsInfo.getGoodsName();
            String stander = goodsInfo.getGoodsStander();
            String goodsprice = goodsInfo.getGoodsPrice();
            String instock = goodsInfo.getGoodsCount();
            String classify = goodsInfo.getGoodsClassify();
            String cls = goodsInfo.getGoodsCls();

            sql = "insert into "+seach_table+" (goodsid,goodsname,goodsprice,instock,stander,classify,cls) " +
                        "value ( \"" +goodsid+"\""+
                        ",\"" +goodsname+"\""+
                        ",\"" +goodsprice+
                        "\",\"" +instock+
                        "\",\"" +stander+
                        "\",\""+classify+
                        "\",\""+cls
                        + "\");";

            stmt = conn.prepareStatement(sql);
            result = stmt.executeUpdate();

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

        if (result == 1 ){
            return true;
        }else return false;
    }

    @Override
    public boolean conAllStoreFromGoodsId(String shopid, String goodsid, String goodscount) {
        Boolean returnCode=false;

        GoodsBase goodsInfo = this.getAllStoreFromGoodsId(shopid,goodsid);
        goodscount = String.valueOf(Integer.valueOf(goodscount)+
                Integer.valueOf(goodsInfo.getGoodsStore()));

        String seach_table = "youga_pet.goods_store_list_"+shopid;
        Connection conn = null;
        PreparedStatement stmt=null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "update "+seach_table+" set instock = \""
                    +goodscount+
                    "\" Where goodsid = \""+goodsid+"\";";

            stmt = conn.prepareStatement(sql);
            int result = stmt.executeUpdate();

            if (result == 1){
                returnCode = true;
            }else {
                returnCode = false;
            }

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
        return returnCode;
    }

    @Override
    public GoodsBase getGoodsInfoById(String goodsid, String shopid) {
        return null;
    }

    @Override
    public List<GoodsBase> setBillList(String shopid, String goodsid) {


        String FLG = "XL-00001-9";

        List<GoodsBase> billlist = new ArrayList<>();
        String seach_table = "youga_pet.bill_list_"+shopid;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            //存储过程执行,若特殊ID则当做获取列表处理，仅获取列表
            if (!goodsid.equals(FLG)){
                CallableStatement c=conn.prepareCall("{call getBillGoodsUp(?)}");
                c.setString(1,goodsid);
                c.execute();
            }
            //获取数据
            String sql = "select * from  "+seach_table+" ;";
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
                GoodsBase goodsBase = new GoodsBase();
                goodsBase.setGoodsId(rs.getString("goodsid"));
                goodsBase.setGoodsInstock(getInstockByGoodsId(shopid,goodsBase.getGoodsId()));
                goodsBase.setGoodsName(rs.getString("goodsname"));
                goodsBase.setGoodsPrice(rs.getString("goodsprice"));
                int grat = Float.valueOf(goodsBase.getGoodsPrice()).intValue()/10;
                goodsBase.setGoodsIntergral(String.valueOf(grat));
                goodsBase.setGoodsCount(rs.getString("goodsNum"));
                goodsBase.setGetIsOffset(getGoodsOffset(goodsBase.getGoodsId()));

                billlist.add(goodsBase);
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

        return billlist;

    }

    private int getGoodsOffset(String goodsId) {
        int result = 0;
        String seach_table = "youga_pet.goods_store_list_yg000001";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            //存储过程执行,若特殊ID则当做获取列表处理，仅获取列表
            //获取数据
            String sql = "select isgoodsOff from  "+seach_table+" where goodsid=\""+goodsId+"\";";
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            if (rs.next())
            {
                rs.previous();
            }
            else {
                BaseDao.closeAll(conn, stmt);
                return 0;
            }
            while(rs.next())
            {
                result = rs.getInt("isgoodsOff");
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    BaseDao.closeAll(conn, stmt);
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return result;
    }

    private String getInstockByGoodsId(String shopid, String goodsId) {

        String result = null;
        String seach_table = "youga_pet.goods_store_list_"+shopid;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            //存储过程执行,若特殊ID则当做获取列表处理，仅获取列表
            //获取数据
            String sql = "select instock from  "+seach_table+" where goodsid=\""+goodsId+"\";";
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
                result = rs.getString("instock");
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    BaseDao.closeAll(conn, stmt);
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public void modifBillList(String shopid, String goodsid, String modify_type) {


        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //存储过程执行
            conn = BaseDao.getConnection();
            CallableStatement c=conn.prepareCall("{call ModifyBillsList(?,?)}");
            c.setString(1,goodsid);
            c.setInt(2,Integer.valueOf(modify_type));
            c.execute();

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
    public void clearBill(String shopid) {
        String seach_table = "youga_pet.bill_list_"+shopid;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = BaseDao.getConnection();
            String sql;
            sql = "delete from "+seach_table+";";
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


}
