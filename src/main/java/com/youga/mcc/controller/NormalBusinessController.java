package com.youga.mcc.controller;


import com.youga.mcc.service.OrderService;
import com.youga.mcc.service.impl.OrderServiceImpl;
import com.youga.mcc.util.EnCodingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class NormalBusinessController {


    //NormalBusinessService normalBusinessService = new NormalBusinessServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    //getResultByGeneralProCall
    /***
     * 通用型存储过程处理
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/getResultByGeneralProCall")
    public void getResultByGeneralProCall(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String proName = req.getParameter("proName");
        //shopid 去#

        String targetName = req.getParameter("targetName");
        String objectJson = req.getParameter("objectJson");

        objectJson = EnCodingUtil.decodeUnicode(objectJson);

        String result = orderService.getResultByGeneralProCall(proName,objectJson,targetName);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().print(result);
    }

}
