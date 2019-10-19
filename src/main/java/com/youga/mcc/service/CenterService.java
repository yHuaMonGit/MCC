package com.youga.mcc.service;

import com.youga.mcc.obj.MerchantInfo;

public interface CenterService {

    /***
     * this method for get MerchantInfo from merchant user account;
     * @param acc
     * @return
     */
    MerchantInfo getMerchantInfo(String acc);

    /***
     * check merchant id is legal
     * @param merchantid
     * @return
     */
    boolean merchantIdVerify(String merchantid);

    /***
     * get merchant info by id;
     * @param merchantid
     * @return
     */
    MerchantInfo getMerchantInfoByShopID(String merchantid);
}
