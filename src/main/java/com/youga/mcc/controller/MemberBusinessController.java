package com.youga.mcc.controller;

import com.alibaba.fastjson.JSONObject;
import com.youga.mcc.obj.GoodsBase;
import com.youga.mcc.obj.MemberInfo;
import com.youga.mcc.service.MemberService;
import com.youga.mcc.service.impl.MemberServiceImpl;
import com.youga.mcc.util.MCCErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MemberBusinessController {

    MemberService memberService = new MemberServiceImpl();

    @RequestMapping("/getAllMember")
    public void getAllMember(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String shopid = req.getParameter("shopid");
        List<MemberInfo> member_list = memberService.getAllMemberByMerchantId(shopid);
        if (null != member_list){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(member_list);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.MEMBER_EMPTY_CODE);
            return;
        }

    }

    //getMemberInfoByMsisdn
    @RequestMapping("/getMemberInfoByMsisdn")
    public void getMemberInfoByMsisdn(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String msisdn = req.getParameter("msisdn");
        MemberInfo member = memberService.getMemberInfoByMsisdn(msisdn);
        if (null != member){
            resp.setContentType("text/html;charset=UTF-8");
            String JsonValue = JSONObject.toJSONString(member);
            resp.getWriter().print(JsonValue);
        }
        else {
            resp.getWriter().print(MCCErrorCode.MEMBER_EMPTY_CODE);
            return;
        }

    }

    //updateMemberInfo
    @RequestMapping("/updateMemberInfo")
    public void updateMemberInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String msisdn = req.getParameter("msisdn");
        String balance = req.getParameter("balance");
        String integral = req.getParameter("integral");

        memberService.updateMemberInfo(msisdn,balance,integral);

    }

}
