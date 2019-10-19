package com.youga.mcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.youga.mcc.dao.OrderDao;
import com.youga.mcc.dao.UniversalSqlDao;
import com.youga.mcc.dao.impl.OrderDaoImpl;
import com.youga.mcc.dao.impl.UniversalSqlDaoImpl;
import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.FlatFormInfo2;
import com.youga.mcc.obj.OrderInfo;
import com.youga.mcc.obj.OrderNotesBase;
import com.youga.mcc.service.OrderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    UniversalSqlDao uniDao = new UniversalSqlDaoImpl();


    @Override
    public boolean insertOrderInfo(OrderInfo newOrder, String shopid) {
       return orderDao.insertOrderInfo(newOrder,shopid);
    }

    @Override
    public boolean checkNowOrder(String shopid) {
        return orderDao.checkNowOrder(shopid);
    }

    @Override
    public FlatFormInfo getOnlineOrderByOrderId(String className, String feilds, String visionName, String conditions) {

        List<String> feildslist = new ArrayList<>();
        List<String> conditionslist = new ArrayList<>();

        feildslist = Arrays.asList(feilds.split(FIELD_INTERVAL));
        conditionslist = Arrays.asList(conditions.split(CONDITION_INTERVAL));

        return uniDao.getFlatFormInfoFromVision(className,feildslist,visionName,conditionslist);
    }

    @Override
    public List<FlatFormInfo> getOnlineOrderListByGenaralSolution(String className, String feilds, String visionName, String conditions) {

        List<String> feildslist = new ArrayList<>();
        List<String> conditionslist = new ArrayList<>();

        feildslist = Arrays.asList(feilds.split(FIELD_INTERVAL));
        conditionslist = Arrays.asList(conditions.split(CONDITION_INTERVAL));

        return uniDao.getFlatFormInfoListFromVision(className,feildslist,visionName,conditionslist);
    }

    @Override
    public void modifyOnlineOrderGeneral(String shopid, String feilds, String visionName, String conditions) {
        List<String> feildslist = new ArrayList<>();
        List<String> conditionslist = new ArrayList<>();

        feildslist = Arrays.asList(feilds.split(FIELD_INTERVAL));
        conditionslist = Arrays.asList(conditions.split(CONDITION_INTERVAL));

        uniDao.modifyOnlineOrderGeneral(shopid,feildslist,visionName,conditionslist);
    }

    @Override
    public List<FlatFormInfo> getOnlineOrderListByGenaralSolution2(String className, String feilds, String visionName, String conditions, String conditionExtra) {
        List<String> feildslist = new ArrayList<>();
        List<String> conditionslist = new ArrayList<>();

        feildslist = Arrays.asList(feilds.split(FIELD_INTERVAL));
        conditionslist = Arrays.asList(conditions.split(CONDITION_INTERVAL));

        return uniDao.getFlatFormInfoListFromVision2(className,feildslist,visionName,conditionslist,conditionExtra);
    }

    @Override
    public void generalInsertSqlRequest(String shopid, String objectJson, String className, String tableName) {
        FlatFormInfo2 flatFormInfo = new FlatFormInfo2();
        flatFormInfo.setValues(JSON.parseObject(objectJson,Map.class));
        flatFormInfo.setClassName(className);
        uniDao.generalInsertSqlRequest(shopid,flatFormInfo,tableName);
    }

    @Override
    public String getResultByGeneralProCall(String proName, String objectJson, String targetName) {
        FlatFormInfo2 flatFormInfo = new FlatFormInfo2();
        flatFormInfo.setValues(JSON.parseObject(objectJson,Map.class));
        String result = uniDao.generalExecuteProWithResult(proName,flatFormInfo,targetName);

        return result;
    }

    @Override
    public List<OrderNotesBase> checkTimeOutOrder() {
        return orderDao.checkTimeOutOrder();
    }

    @Override
    public void deleteOrder(String order_no) {
        orderDao.deleteOrder(order_no);
    }

    @Override
    public FlatFormInfo getDayLayMsg(String todayFormat) {

        return orderDao.getDayLayMsg(todayFormat);
    }
}
