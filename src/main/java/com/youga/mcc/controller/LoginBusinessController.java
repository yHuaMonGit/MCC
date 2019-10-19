package com.youga.mcc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youga.mcc.obj.MerchantInfo;
import com.youga.mcc.service.CenterService;
import com.youga.mcc.service.LoginService;
import com.youga.mcc.service.impl.CenterServiceImpl;
import com.youga.mcc.service.impl.LoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class LoginBusinessController {

    LoginService loginService = new LoginServiceImpl();
    CenterService centerService = new CenterServiceImpl();

    @RequestMapping("/accVerify")
    public void accVerify(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        MerchantInfo merchantInfo = null;
        String acc = req.getParameter("acc");
        String pass = req.getParameter("pass");

        //登录校验
        if (loginService.checkLogin(acc,pass)){
            merchantInfo = centerService.getMerchantInfo(acc);
            //通过校验，登录用户应当入登陆监控数据库；

        }
        resp.setContentType("text/html;charset=UTF-8");
        String JsonValue = JSONObject.toJSONString(merchantInfo);
        resp.getWriter().print(JsonValue);
    }

}
