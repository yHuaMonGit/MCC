package com.youga.mcc.service.impl;

import com.youga.mcc.dao.StoreDao;
import com.youga.mcc.dao.impl.StoreDaoImpl;
import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.GoodsBase;
import com.youga.mcc.service.StoreService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreServiceImpl implements StoreService {

    StoreDao storeDao = new StoreDaoImpl();

    @Override
    public List<GoodsBase> getAllStoreByMerchantId(String shopid) {
        return storeDao.getAllStoreFromMerchant(shopid);
    }

    @Override
    public boolean modifyGoodsCls(String merchantid, String goodsid, String clsType) {
        return storeDao.modifyGoodsClassify(merchantid,goodsid,clsType);
    }

    @Override
    public List<GoodsBase> getAllStoreByClsType(String shopid, String clsType) {
        return storeDao.getAllStoreFromClsType(shopid,clsType);
    }

    @Override
    public List<GoodsBase> getAllStoreByGoodsName(String shopid, String goodsName) throws Exception {
        return storeDao.getAllStoreFromGoodsName(shopid,goodsName);
    }

    @Override
    public GoodsBase getAllStoreByGoodsId(String shopid, String goodsid) {
        return storeDao.getAllStoreFromGoodsId(shopid,goodsid);
    }

    @Override
    public GoodsBase checkStoreByGoodsId(String shopid, String goodsid) {
        return storeDao.getAllStoreFromGoodsId(shopid,goodsid);
    }

    @Override
    public boolean addStoreByGoodsId(String shopid, GoodsBase goodsInfo) {
        return storeDao.addAllStoreFromGoodsId(shopid,goodsInfo);
    }

    @Override
    public boolean conStoreByGoodsId(String shopid, String goodsid, String goodscount) {
        return storeDao.conAllStoreFromGoodsId(shopid,goodsid,goodscount);
    }

}
