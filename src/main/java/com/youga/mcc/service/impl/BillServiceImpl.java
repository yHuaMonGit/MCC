package com.youga.mcc.service.impl;

import com.youga.mcc.dao.BillDao;
import com.youga.mcc.dao.StoreDao;
import com.youga.mcc.dao.impl.BillDaoImpl;
import com.youga.mcc.dao.impl.StoreDaoImpl;
import com.youga.mcc.obj.GoodsBase;
import com.youga.mcc.service.BillService;

import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {


    StoreDao goodsDao = new StoreDaoImpl();
    BillDao billDao = new BillDaoImpl();

    @Override
    public List<GoodsBase> getBillById(String shopid, String goodsid) {

        List<GoodsBase> billlist = new ArrayList<>();
        //2019-05-06 Service层仅用两个Dao层逻辑即可处理
        //问题，对于宠物店业务，需要增加库存量的判定！
        return goodsDao.setBillList(shopid,goodsid);

    }

    @Override
    public void modifyBillById(String shopid, String goodsid, String modify_type) {

        //2019-05-06 Service层仅用两个Dao层逻辑即可处理
        //问题，对于宠物店业务，需要增加库存量的判定！
         goodsDao.modifBillList(shopid, goodsid,modify_type);
    }

    @Override
    public void clearBill(String shopid) {
        goodsDao.clearBill(shopid);
    }
}
