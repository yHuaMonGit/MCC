package com.youga.mcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.youga.mcc.obj.GoodsBase;
import com.youga.mcc.service.BillService;
import com.youga.mcc.service.impl.BillServiceImpl;
import com.youga.mcc.util.MCCErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class BillBusinessController {


    //Service声明
    BillService billService = new BillServiceImpl();

    //2019-05-05 获取扫描结果
    //getBillById
    @RequestMapping("/getBillById")
    public void getBillById(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String shopid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        List<GoodsBase> store_list = billService.getBillById(shopid,goodsid);
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

    //modifyBillByID
    @RequestMapping("/modifyBillByID")
    public void modifyBillByID(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String shopid = req.getParameter("shopid");
        String goodsid = req.getParameter("goodsid");
        String modify_type = req.getParameter("modify_type");
        billService.modifyBillById(shopid,goodsid,modify_type);

            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().print("200");


    }

    //clearBill
    @RequestMapping("/clearBill")
    public void clearBill(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String shopid = req.getParameter("shopid");

        billService.clearBill(shopid);

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print("200");


    }
}
