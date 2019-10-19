package com.youga.mcc.controller;


import com.youga.mcc.service.OrderService;
import com.youga.mcc.service.impl.OrderServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TaskBusinessController {

    OrderService orderService = new OrderServiceImpl();

    @RequestMapping("/checkNewOrder")
    public void checkNewOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        if (orderService.checkNowOrder(shopid)){
            resp.getWriter().print("2");
        }else {
            resp.getWriter().print("1");
        }

    }

}
