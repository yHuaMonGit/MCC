package com.youga.mcc.dao;


import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.FlatFormInfo2;

import java.util.List;

public interface UniversalSqlDao {


    /***
     * 通用型平面数据单条记录视图查询
     * @param className
     * @param feilds
     * @param vision
     * @param conditions
     * @return
     */
    public FlatFormInfo getFlatFormInfoFromVision(String className, List<String> feilds, String vision, List<String> conditions);

    /***
     * 通用型平面数据批量记录视图查询
     * @param className
     * @param feilds
     * @param vision
     * @param conditions
     * @return
     */
    public List<FlatFormInfo> getFlatFormInfoListFromVision(String className, List<String> feilds, String vision, List<String> conditions);


    /***
     * 通用型平面数据刷新
     * @param shopid
     * @param feildslist
     * @param visionName
     * @param conditionslist
     */
    void modifyOnlineOrderGeneral(String shopid, List<String> feildslist, String visionName, List<String> conditionslist);

    /***
     * 重载方法，多增一个参数
     * @param className
     * @param feildslist
     * @param visionName
     * @param conditionslist
     * @param conditionExtra
     * @return
     */
    List<FlatFormInfo> getFlatFormInfoListFromVision2(String className, List<String> feildslist, String visionName, List<String> conditionslist, String conditionExtra);

    /***
     * 通用型数据插入
     * @param shopid
     * @param flatFormInfo
     * @param tableName
     */
    void generalInsertSqlRequest(String shopid, FlatFormInfo2 flatFormInfo, String tableName);

    /****
     * 通用型存储过程加载
     * @param proName
     * @param flatFormInfo
     * @param targetName
     * @return
     */
    String generalExecuteProWithResult(String proName, FlatFormInfo2 flatFormInfo, String targetName);
}
