package com.youga.mcc.dao;

import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.OrderInfo;
import com.youga.mcc.obj.OrderNotesBase;

import java.util.List;

public interface OrderDao {
    /***
     * Dao层实现，插入失败返回False
     * @param newOrder
     * @param shopid
     * @return
     */
    boolean insertOrderInfo(OrderInfo newOrder, String shopid);

    /***
     * 若有新的订单记录，则返回true；
     * @param shopid
     * @return
     */
    boolean checkNowOrder(String shopid);

    /***
     * 获取已超时的订单，并返回
     * @return
     */
    List<OrderNotesBase> checkTimeOutOrder();

    /***
     * 删除订单
     * @param order_no
     */
    void deleteOrder(String order_no);

    FlatFormInfo getDayLayMsg(String todayFormat);
}
