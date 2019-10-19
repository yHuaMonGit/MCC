package com.youga.mcc.service;

import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.OrderInfo;
import com.youga.mcc.obj.OrderNotesBase;

import java.util.List;

public interface OrderService {

    public static String FIELD_INTERVAL = ",";
    public static String CONDITION_INTERVAL = " and ";

    /***
     * 插入一条订单记录
     * @param newOrder
     * @param shopid
     * @return
     */
    boolean insertOrderInfo(OrderInfo newOrder, String shopid);

    /***
     * 查询订单记录，如果查询有新订单，则返回true
     * @param shopid
     * @return
     */
    boolean checkNowOrder(String shopid);

    /***
     * 通过OrderID获取订单平面信息
     * @param className
     * @param feilds
     * @param visionName
     * @param conditions
     * @return
     */
    FlatFormInfo getOnlineOrderByOrderId(String className, String feilds, String visionName, String conditions);

    /***
     * 通用型订单返回操作，根据参数不同自定义生成
     * @param className
     * @param feilds
     * @param visionName
     * @param conditions
     * @return
     */
    List<FlatFormInfo> getOnlineOrderListByGenaralSolution(String className, String feilds, String visionName, String conditions);

    /***
     * 通用型订单修改操作，根据参数列表生成对应SQL
     * @param shopid
     * @param feilds
     * @param visionName
     * @param conditions
     */
    void modifyOnlineOrderGeneral(String shopid, String feilds, String visionName, String conditions);

    /***
     * 通用型订单修改操作，根据参数列表生成对应SQL
     * 新增后缀条件conditionExtra 用于排序、组选等其他条件
     * @param className
     * @param feilds
     * @param visionName
     * @param conditions
     * @param conditionExtra
     * @return
     */
    List<FlatFormInfo> getOnlineOrderListByGenaralSolution2(String className, String feilds, String visionName, String conditions, String conditionExtra);

    /***
     * 通用型插入操作：
     *      1.根据ClassName生成对应对象
     *      2.对象解析出相应的有值字值对
     *      3.拼接SQL执行；
     * @param shopid
     * @param objectJson
     * @param className
     * @param tableName
     */
    void generalInsertSqlRequest(String shopid, String objectJson, String className, String tableName);

    /****
     * 通用型存储过程执行
     * @param proName
     * @param objectJson
     * @param targetName
     * @return
     */
    String getResultByGeneralProCall(String proName, String objectJson, String targetName);

    /***
     * 获取超时的订单信息
     * @return
     */
    List<OrderNotesBase> checkTimeOutOrder();

    /***
     * 根据OrderId删除ORder
     * @param order_no
     */
    void deleteOrder(String order_no);

    /***
     * 返回营业日报
     * @param todayFormat
     * @return
     */
    FlatFormInfo getDayLayMsg(String todayFormat);
}
