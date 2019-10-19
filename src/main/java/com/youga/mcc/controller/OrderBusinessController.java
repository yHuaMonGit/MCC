package com.youga.mcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.OrderInfo;
import com.youga.mcc.service.OrderService;
import com.youga.mcc.service.impl.OrderServiceImpl;
import com.youga.mcc.util.EnCodingUtil;
import com.youga.mcc.util.MCCErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class OrderBusinessController {

    OrderService orderService = new OrderServiceImpl();

    //insertOrderInfo
    @RequestMapping("/insertOrderInfo")
    public void insertOrderInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        //shopid 去#
        shopid = shopid.replace("#","");
        String orderId = req.getParameter("orderId");
        String msisdn = req.getParameter("msisdn");
        String orderOutTime = req.getParameter("orderOutTime");
        String memberID = req.getParameter("memberID");
        String memberFlag = req.getParameter("memberFlag");
        String memberLevel = req.getParameter("memberLevel");
        String iniAmount = req.getParameter("iniAmount");
        String actAmount = req.getParameter("actAmount");
        String activeOffType = req.getParameter("activeOffType");
        String activeOff = req.getParameter("activeOff");
        String goodsList = req.getParameter("goodsList");
        String goodsRemarks = req.getParameter("goodsRemarks");
        String decAmount = req.getParameter("decAmount");
        String decAuthor = req.getParameter("decAuthor");
        String paymentType = req.getParameter("paymentType");

        //在数据层再去填入order-goods-list
        //中文转化
        if (goodsRemarks.length()>0){
            goodsRemarks = EnCodingUtil.decodeUnicode(goodsRemarks);
        }
        if (decAuthor.length()>0){
            decAuthor = EnCodingUtil.decodeUnicode(decAuthor);
        }


        OrderInfo newOrder = new OrderInfo();
        //还原对象
        newOrder.setOrderId(orderId);
        newOrder.setMsisdn(msisdn);
        newOrder.setOrderTime(orderOutTime);

        newOrder.setMemberFlag(memberFlag);

        newOrder.setMemberID(memberID);
        newOrder.setMemberLevel(memberLevel);

        newOrder.setMoneyAmount(iniAmount);
        newOrder.setActiveAmount(actAmount);
        newOrder.setActiveType(activeOffType);
        newOrder.setActiveOff(activeOff);


        newOrder.setGoodsListStr(goodsList);
        newOrder.setNote(goodsRemarks);
        newOrder.setDecAmount(decAmount);
        newOrder.setDecAuthor(decAuthor);

        newOrder.setPaymentWay(paymentType);
        if (orderService.insertOrderInfo(newOrder,shopid)){
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().print(1);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_MODIFY_CLS_CODE);
            return;
        }

    }

    //order_member_address_viewer
    //getOnlineOrderByOrderId
    @RequestMapping("/getOnlineOrderByOrderId")
    public void getOnlineOrderByOrderId(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        //shopid 去#
        shopid = shopid.replace("#","");


        String feilds = req.getParameter("feilds");
        String className = req.getParameter("className");
        String conditions = req.getParameter("conditions");
        String visionName = req.getParameter("visionName");

        FlatFormInfo resultObj = orderService.getOnlineOrderByOrderId(className,feilds,visionName,conditions);

        if (null != resultObj){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(resultObj);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_MODIFY_CLS_CODE);
            return;
        }

    }

    //getOnlineOrderListGeneral
    @RequestMapping("/getOnlineOrderListGeneral")
    public void getOnlineOrderListGeneral(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        //shopid 去#
        shopid = shopid.replace("#","");
        String feilds = req.getParameter("feilds");
        String className = req.getParameter("className");
        String conditions = req.getParameter("conditions");
        String visionName = req.getParameter("visionName");

        List<FlatFormInfo> resultObj = orderService.getOnlineOrderListByGenaralSolution(className,feilds,visionName,conditions);

        if (null != resultObj){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(resultObj);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_MODIFY_CLS_CODE);
            return;
        }

    }

    //getOnlineOrderListGeneral2
    @RequestMapping("/getOnlineOrderListGeneral2")
    public void getOnlineOrderListGeneral2(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        //shopid 去#
        shopid = shopid.replace("#","");
        String feilds = req.getParameter("feilds");
        String className = req.getParameter("className");
        String conditions = req.getParameter("conditions");
        String conditionExtra = req.getParameter("conditionExtra");
        String visionName = req.getParameter("visionName");

        List<FlatFormInfo> resultObj = orderService.getOnlineOrderListByGenaralSolution2(className,feilds,visionName,conditions,conditionExtra);

        if (null != resultObj){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(resultObj);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_MODIFY_CLS_CODE);
            return;
        }

    }
    //modifyOnlineOrderGeneral
    @RequestMapping("/modifyOnlineOrderGeneral")
    public void modifyOnlineOrderGeneral(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        //shopid 去#
        shopid = shopid.replace("#","");
        String feilds = req.getParameter("feilds");
        //2019-07-16对可能含有中文插入的值进行处理
        feilds = EnCodingUtil.decodeUnicode(feilds);
        String conditions = req.getParameter("conditions");
        String visionName = req.getParameter("tableName");
        orderService.modifyOnlineOrderGeneral(shopid,feilds,visionName,conditions);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(0);
    }

    //generalInsertSqlRequest

    /***
     * 通用型插入式SQL处理
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/generalInsertSqlRequest")
    public void generalInsertSqlRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        //shopid 去#
        shopid = shopid.replace("#","");
        String objectJson = req.getParameter("objectJson");
        objectJson = EnCodingUtil.decodeUnicode(objectJson);
        String className = req.getParameter("className");
        String tableName = req.getParameter("tableName");
        orderService.generalInsertSqlRequest(shopid,objectJson,className,tableName);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(0);
    }


    /***
     * 特殊化获取求和总值
     *      getDayLayMsg
     */
    @RequestMapping("/getDayLayMsg")
    public void getDayLayMsg(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String todayFormat = req.getParameter("todayFormat");
        //shopid 去#


        FlatFormInfo resultObj = orderService.getDayLayMsg(todayFormat);

        if (null != resultObj){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(resultObj);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_MODIFY_CLS_CODE);
            return;
        }

    }

}
