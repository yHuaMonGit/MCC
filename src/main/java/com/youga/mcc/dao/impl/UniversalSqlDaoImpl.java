package com.youga.mcc.dao.impl;

import com.youga.mcc.dao.UniversalSqlDao;
import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.FlatFormInfo2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversalSqlDaoImpl implements UniversalSqlDao {

    private static final String CONDITION_HEAD = "where ";
    private static final String FIELD_INTERVAL = ",";
    private static final String CONDITION_INTERVAL = " and ";
    private static final String CONDITION_DELIMITER = ":";

    @Override
    public FlatFormInfo getFlatFormInfoFromVision(String className, List<String> feilds, String vision, List<String> conditions) {

        //数据源视图
        String search_vision = vision;
        //数据获取字段;
        String search_param = "";
        //筛选列表
        String search_condisions = "";
        //结果数据集
        Map<String,String> resultmap = null;

        /***
         * 异常处理
         */
        if ( 0 == feilds.size()){
            return null;
        }
        if (null == className || className.equals("")){
            return null;
        }

        if (null == vision || vision.equals("")){
            return null;
        }

        FlatFormInfo flatFormInfo = new FlatFormInfo(className,feilds.size(),conditions);

        /***
         * 数据字段、条件字段拼接
         */
        for (String feild:feilds){
            search_param += feild+FIELD_INTERVAL;
        }
        search_param = search_param.substring(0,search_param.length()-1);

        if (conditions.get(0).length()>1){

            search_condisions = CONDITION_HEAD;
            for (String condition:conditions){
                String [] conkv = condition.split(CONDITION_DELIMITER);
                if (1 == conkv.length){
                    search_condisions += conkv[0]+CONDITION_INTERVAL;
                }else if(conkv.length>1){
                    search_condisions += conkv[0]+" "+conkv[1]+" \""+conkv[2]+"\" "+CONDITION_INTERVAL;
                }
            }
            search_condisions = search_condisions.substring(0,search_condisions.length()-5);
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();

            String sql = "select "+search_param+" from  "+search_vision+"  "+search_condisions+" ;";
            //System.out.println("[statement SQL:]"+sql);
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            /***
             * 判断是否存在数据记录，若不存在，则返回1：没有查询到数据记录，检查入参
             */
            if (rs.next())
            {
                rs.previous();
            }
            else {
                flatFormInfo.setSearchStatus(1);
                BaseDao.closeAll(conn, stmt);
                return flatFormInfo;
            }
            while(rs.next())
            {
                flatFormInfo.setSearchStatus(0);
                /***
                 * 根据字段进行赋值
                 * 注意：这里的字段名必须与数据库里的字段名完全一致
                 */
                resultmap = new HashMap<String,String>();
                for (String feildName:feilds){
                    resultmap.put(feildName,rs.getString(feildName));
                }
                flatFormInfo.setValues(resultmap);
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return flatFormInfo;
    }

    @Override
    public List<FlatFormInfo> getFlatFormInfoListFromVision(String className, List<String> feilds, String vision, List<String> conditions) {
        String FIELD_INTERVAL = ",";
        String CONDITION_INTERVAL = " and ";

        //数据源视图
        String search_vision = vision;
        //数据获取字段;
        String search_param = "";
        //筛选列表
        String search_condisions = "";
        //结果数据集
        Map<String,String> resultmap = null;

        /***
         * 异常处理
         */
        if ( 0 == feilds.size()){
            return null;
        }
        if (null == className || className.equals("")){
            return null;
        }

        if (null == vision || vision.equals("")){
            return null;
        }

        //返回容器
        List<FlatFormInfo> resultList = new ArrayList<FlatFormInfo>();

        /***
         * 数据字段、条件字段拼接
         */
        for (String feild:feilds){
            search_param += feild+FIELD_INTERVAL;
        }
        search_param = search_param.substring(0,search_param.length()-1);

        if (conditions.size()>0){
            search_condisions = CONDITION_HEAD;
            for (String condition:conditions){
                String [] conkv = condition.split(CONDITION_DELIMITER);
                if (1 == conkv.length){
                    search_condisions += conkv[0]+CONDITION_INTERVAL;
                }else if(conkv.length>1){
                    search_condisions += conkv[0]+" "+conkv[1]+"  \""+conkv[2]+"\" "+CONDITION_INTERVAL;
                }
            }
            search_condisions = search_condisions.substring(0,search_condisions.length()-5);
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select "+search_param+" from  "+search_vision+"  "+search_condisions+" ;";
           // System.out.println("[statement SQL:]"+sql);
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            /***
             * 判断是否存在数据记录，若不存在，则返回1：没有查询到数据记录，检查入参
             */
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
                FlatFormInfo flatFormInfo = new FlatFormInfo(className,feilds.size(),conditions);
                flatFormInfo.setSearchStatus(0);
                /***
                 * 根据字段进行赋值
                 * 注意：这里的字段名必须与数据库里的字段名完全一致
                 */
                resultmap = new HashMap<String,String>();
                for (String feildName:feilds){
                    resultmap.put(feildName,rs.getString(feildName));
                }
                flatFormInfo.setValues(resultmap);
                resultList.add(flatFormInfo);
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public void modifyOnlineOrderGeneral(String shopid, List<String> feildslist, String visionName, List<String> conditionslist) {
        //数据源视图
        String search_vision = visionName;
        //数据获取字段;
        String search_param = "";
        //筛选列表
        String search_condisions = "";
        //结果数据集
        Map<String,String> resultmap = null;

        /***
         * 异常处理
         */
        if ( 0 == feildslist.size()){
            return ;
        }

        if (null == visionName || visionName.equals("")){
            return ;
        }


        /***
         * 数据字段、条件字段拼接
         */
        for (String feild:feildslist){
            search_param += feild+FIELD_INTERVAL;
        }
        search_param = search_param.substring(0,search_param.length()-1);

        if (conditionslist.size()>0){
            search_condisions = CONDITION_HEAD;
            for (String condition:conditionslist){
                String [] conkv = condition.split(CONDITION_DELIMITER);
                if (1 == conkv.length){
                    search_condisions += conkv[0]+CONDITION_INTERVAL;
                }else if(conkv.length>1){
                    search_condisions += conkv[0]+" "+conkv[1]+" \""+conkv[2]+"\" "+CONDITION_INTERVAL;
                }
            }
            search_condisions = search_condisions.substring(0,search_condisions.length()-5);
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();

            String sql = "update "+search_vision+" set  "+search_param+"  "+search_condisions+" ;";
            System.out.println("[statement SQL:]"+sql);
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    @Override
    public List<FlatFormInfo> getFlatFormInfoListFromVision2(String className, List<String> feildslist, String visionName, List<String> conditionslist, String conditionExtra) {
        String FIELD_INTERVAL = ",";
        String CONDITION_INTERVAL = " and ";

        //数据源视图
        String search_vision = visionName;
        //数据获取字段;
        String search_param = "";
        //筛选列表
        String search_condisions = "";
        //结果数据集
        Map<String,String> resultmap = null;

        /***
         * 异常处理
         */
        if ( 0 == feildslist.size()){
            return null;
        }
        if (null == className || className.equals("")){
            return null;
        }

        if (null == visionName || visionName.equals("")){
            return null;
        }

        //返回容器
        List<FlatFormInfo> resultList = new ArrayList<FlatFormInfo>();

        /***
         * 数据字段、条件字段拼接
         */
        for (String feild:feildslist){
            search_param += feild+FIELD_INTERVAL;
        }
        search_param = search_param.substring(0,search_param.length()-1);

        if (conditionslist.size()>0){
            search_condisions = CONDITION_HEAD;
            for (String condition:conditionslist){
                String [] conkv = condition.split(CONDITION_DELIMITER);
                if (1 == conkv.length){
                    search_condisions += conkv[0]+CONDITION_INTERVAL;
                }else if(conkv.length>1){
                    search_condisions += conkv[0]+" "+conkv[1]+"  \""+conkv[2]+"\" "+CONDITION_INTERVAL;
                }
            }
            search_condisions = search_condisions.substring(0,search_condisions.length()-5);
        }

        if (conditionExtra.length()>0){
            search_condisions += " "+conditionExtra;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();
            String sql = "select "+search_param+" from  "+search_vision+"  "+search_condisions+" ;";
            // System.out.println("[statement SQL:]"+sql);
            stmt = conn.prepareStatement(sql);
            rs =  stmt.executeQuery();
            /***
             * 判断是否存在数据记录，若不存在，则返回1：没有查询到数据记录，检查入参
             */
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
                FlatFormInfo flatFormInfo = new FlatFormInfo(className,feildslist.size(),conditionslist);
                flatFormInfo.setSearchStatus(0);
                /***
                 * 根据字段进行赋值
                 * 注意：这里的字段名必须与数据库里的字段名完全一致
                 */
                resultmap = new HashMap<String,String>();
                for (String feildName:feildslist){
                    resultmap.put(feildName,rs.getString(feildName));
                }
                flatFormInfo.setValues(resultmap);
                resultList.add(flatFormInfo);
            }
            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public void generalInsertSqlRequest(String shopid, FlatFormInfo2 flatFormInfo, String tableName) {

        //数据源视图
        String insert_table = tableName;
        //数据获取字段;
        String insert_feilds = "(";
        //筛选列表
        String insert_feilds_value = "(";



        /***
         * 异常处理
         */
        if ( 0 == flatFormInfo.getValues().size()){
            return ;
        }

        if (null == insert_table || insert_table.equals("")){
            return ;
        }


        /***
         * map 遍历获取字段
         */
        Map<String, Object> objMap = flatFormInfo.getValues();

        //遍历map中的键
        for (String key : objMap.keySet()) {
            insert_feilds += key+",";
            insert_feilds_value += "\""+objMap.get(key)+"\",";
        }
        insert_feilds = insert_feilds.substring(0,insert_feilds.length()-1);
        insert_feilds+=")";
        insert_feilds_value = insert_feilds_value.substring(0,insert_feilds_value.length()-1);
        insert_feilds_value+=")";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = BaseDao.getConnection();

            String sql = "INSERT INTO  "+insert_table+" "+insert_feilds+" VALUES "+insert_feilds_value+" ;";
            System.out.println("[statement SQL:]"+sql);
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            BaseDao.closeAll(conn, stmt);
        } catch (SQLException e) {
            try {
                BaseDao.closeAll(conn, stmt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }


    }

    @Override
    public String generalExecuteProWithResult(String proName, FlatFormInfo2 flatFormInfo, String targetName) {


        //动态获取输入参数个数
        String parmaNum = "(";
        //获取结果
        String result = null;

        for (int i = 0 ;i<flatFormInfo.getValues().size();i++)
        {
            parmaNum +="?,";
        }
        //parmaNum=parmaNum.substring(0,parmaNum.length()-1);
        parmaNum+="?)";



        //存储过程执行部分
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = BaseDao.getConnection();

            //"{call InsertOD_GOODS(?,?)}"
            CallableStatement c=conn.prepareCall("{call "+proName+parmaNum+"}");

            //设置存储过程参数
            //c.setString(1,orderId);
            Map<String, Object> map = flatFormInfo.getValues();

            int count = 1;
            for (String key : map.keySet()) {
                c.setString(Integer.valueOf(key), (String) map.get(key));
            }
            c.registerOutParameter(targetName,Types.INTEGER);


            c.execute();
            result = c.getString(targetName);
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
}
