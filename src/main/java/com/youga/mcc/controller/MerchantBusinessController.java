package com.youga.mcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.youga.mcc.obj.MerchantInfo;
import com.youga.mcc.service.CenterService;
import com.youga.mcc.service.impl.CenterServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MerchantBusinessController {

    CenterService centerService = new CenterServiceImpl();
    @RequestMapping("/getmerchant")
    public void getmerchant(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        MerchantInfo merchantInfo = null;
        String acc = req.getParameter("acc");

        merchantInfo = centerService.getMerchantInfo(acc);

        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONObject.toJSONString(merchantInfo);
        resp.getWriter().print(JsonValue);
    }

    @RequestMapping("/merchantIdVerify")
    public void merchantIdVerify(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String merchantid = req.getParameter("shopid");

        if (centerService.merchantIdVerify(merchantid)){
            resp.getWriter().print(1);
            return;
        }
        resp.getWriter().print(0);
    }


    @RequestMapping("/getMerchantById")
    public void getMerchantById(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        MerchantInfo merchantInfo = null;
        String merchantid = req.getParameter("shopid");

        merchantInfo = centerService.getMerchantInfoByShopID(merchantid);
        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONObject.toJSONString(merchantInfo);
        resp.getWriter().print(JsonValue);
    }

}
