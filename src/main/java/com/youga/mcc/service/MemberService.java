package com.youga.mcc.service;

import com.youga.mcc.dao.MemberDao;
import com.youga.mcc.dao.impl.MemberDaoImpl;
import com.youga.mcc.obj.MemberInfo;

import java.util.List;

public interface MemberService {


    MemberDao memberDao = new MemberDaoImpl();

    /***
     * 2019-01-18 获取门店会员信息
     * @param shopid
     * @return
     */
    List<MemberInfo> getAllMemberByMerchantId(String shopid);

    /***
     * 2019-05-07 获取单个用户信息
     * @param msisdn
     * @return
     */
    MemberInfo getMemberInfoByMsisdn(String msisdn);

    /***
     * 用于结账后更新用户信息
     * @param msisdn
     * @param balance
     * @param integral
     */
    void updateMemberInfo(String msisdn, String balance, String integral);
}
