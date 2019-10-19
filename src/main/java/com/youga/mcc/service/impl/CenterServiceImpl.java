package com.youga.mcc.service.impl;

import com.youga.mcc.dao.MerchantDao;
import com.youga.mcc.dao.UserDao;
import com.youga.mcc.dao.impl.MerchantDaoImpl;
import com.youga.mcc.dao.impl.UserDaoImpl;
import com.youga.mcc.obj.MerchantInfo;
import com.youga.mcc.service.CenterService;

public class CenterServiceImpl implements CenterService {

    MerchantDao merchantDao = new MerchantDaoImpl();
    UserDao userDao = new UserDaoImpl();
    @Override
    public MerchantInfo getMerchantInfo(String acc) {
        MerchantInfo merchantInfo ;

        String shopid = userDao.getShopIdByUser(acc);
        merchantInfo = merchantDao.getMerchantBaseInfo(shopid);

        return merchantInfo;
    }

    @Override
    public boolean merchantIdVerify(String merchantid) {
        if (merchantDao.checkMerchantId(merchantid)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public MerchantInfo getMerchantInfoByShopID(String merchantid) {
        return merchantDao.getMerchantBaseInfo(merchantid);
    }
}
