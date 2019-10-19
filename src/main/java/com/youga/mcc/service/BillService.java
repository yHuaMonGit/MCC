package com.youga.mcc.service;

import com.youga.mcc.obj.GoodsBase;

import java.util.List;

public interface BillService {


    /***
     * 2019-05-05 通过中转获取bill list；
     * @param shopid
     * @param goodsid
     * @return
     */
    List<GoodsBase> getBillById(String shopid, String goodsid);

    /***
     * 2019-05-06 同步修改增删减bill list
     * @param shopid
     * @param goodsid
     * @param modify_type
     * @return
     */
    void modifyBillById(String shopid, String goodsid, String modify_type);

    /***
     * 清空bill list
     * @param shopid
     */
    void clearBill(String shopid);
}
