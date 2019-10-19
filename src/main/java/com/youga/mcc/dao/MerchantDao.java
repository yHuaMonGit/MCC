package com.youga.mcc.dao;

import com.youga.mcc.obj.MerchantInfo;

public interface MerchantDao {

    /***
     * this method for get Merchant Info from DB;
     * @param shopid
     * @return
     */
    MerchantInfo getMerchantBaseInfo(String shopid);

    /***
     * check merchant id is exist;
     * exist :true;
     * not :false
     * @param merchantid
     */
    boolean checkMerchantId(String merchantid);
}
