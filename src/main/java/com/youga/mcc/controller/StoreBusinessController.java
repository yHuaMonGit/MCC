package com.youga.mcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.GoodsBase;
import com.youga.mcc.service.StoreService;
import com.youga.mcc.service.impl.StoreServiceImpl;
import com.youga.mcc.util.EnCodingUtil;
import com.youga.mcc.util.MCCErrorCode;
import org.apache.log4j.spi.ErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class StoreBusinessController {

    StoreService storeService = new StoreServiceImpl();

    @RequestMapping("/storeAdd")
    public void storeAdd(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println(" it's here !");

    }

    @RequestMapping("/getAllStore")
    public void getAllStore(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        List<GoodsBase> store_list = storeService.getAllStoreByMerchantId(shopid);
        if (null != store_list){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(store_list);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_EMPTY_CODE);
            return;
        }

    }

    @RequestMapping("/modifyGoodsCls")
    public void modifyGoodsCls(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        String clsType = req.getParameter("clsType");

        if (storeService.modifyGoodsCls(merchantid,goodsid,clsType)){
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().print(1);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_MODIFY_CLS_CODE);
            return;
        }

    }

    //getStoreByClsType
    @RequestMapping("/getStoreByClsType")
    public void getStoreByClsType(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        String clsType = req.getParameter("clsType");
        List<GoodsBase> store_list = storeService.getAllStoreByClsType(shopid,clsType);
        if (null != store_list){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(store_list);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_EMPTY_CODE);
            return;
        }

    }


    //2019-01-12 按照商品名称模糊匹配
    //getStoreByGoodsName
    @RequestMapping("/getStoreByGoodsName")
    public void getStoreByGoodsName(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String shopid = req.getParameter("shopid");
        String goodsName = req.getParameter("goodsName");
        //对于中文隐患做转化处理
        goodsName = EnCodingUtil.decodeUnicode(goodsName);
        List<GoodsBase> store_list = storeService.getAllStoreByGoodsName(shopid,goodsName);
        if (null != store_list){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(store_list);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_EMPTY_CODE);
            return;
        }

    }

    //2019-01-03 按照ID查询商品数据
    //getStoreByGoodsId
    @RequestMapping("/getStoreByGoodsId")
    public void getStoreByGoodsId(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String shopid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        GoodsBase goodsInfo = storeService.getAllStoreByGoodsId(shopid,goodsid);

        if (null != goodsInfo.getGoodsId()){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(goodsInfo);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.STORE_EMPTY_CODE);
            return;
        }

    }

    //2019-01-14 检查商品是否存在
    //checkStoreByGoodsId
    @RequestMapping("/checkStoreByGoodsId")
    public void checkStoreByGoodsId(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String shopid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        GoodsBase goodsInfo = storeService.checkStoreByGoodsId(shopid,goodsid);

        if (null != goodsInfo.getGoodsId()){

            resp.getWriter().print(MCCErrorCode.STORE_IS_EXIST);
            return;

        }
        else {
            resp.getWriter().print(0);
            return;
        }

    }

    //2019-01-14 添加商品
    //addStoreByGoodsId
    @RequestMapping("/addStoreByGoodsId")
    public void addStoreByGoodsId(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String shopid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        String goodsname = req.getParameter("goodsname");
        //utf-8解析
        goodsname = EnCodingUtil.decodeUnicode(goodsname);
        String goodscount = req.getParameter("goodscount");
        String goodsprice = req.getParameter("goodsprice");
        String goodsbrand = req.getParameter("goodsbrand");
        String goodscls = req.getParameter("goodscls");
        if (null!=goodsbrand && !"".equals(goodsbrand)){
            goodsbrand = EnCodingUtil.decodeUnicode(goodsbrand);
        }
        String goodsstander = req.getParameter("goodsstander");

        GoodsBase goodsInfo = new GoodsBase(goodsid,goodsname,goodsprice,goodscount,
                String.valueOf(Float.valueOf(goodsprice).intValue()/10));

        goodsInfo.setGoodsClassify(goodsbrand);
        goodsInfo.setGoodsStander(goodsstander);
        goodsInfo.setGoodsCls(goodscls);

        if (storeService.addStoreByGoodsId(shopid,goodsInfo)){
            resp.getWriter().print(0);
            return;
        }else {
            resp.getWriter().print(MCCErrorCode.STORE_ADD_FAILD);
        }

    }


    //2019-01-15 商品续货
    //conStoreByGoodsId
    @RequestMapping("/conStoreByGoodsId")
    public void conStoreByGoodsId(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String shopid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        String goodscount = req.getParameter("goodscount");


        if (storeService.conStoreByGoodsId(shopid,goodsid,goodscount)){
            resp.getWriter().print(0);
            return;
        }else {
            resp.getWriter().print(MCCErrorCode.STORE_ADD_FAILD);
        }

    }



}
