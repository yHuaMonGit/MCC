package com.youga.mcc.dao;

import com.youga.mcc.obj.MemberInfo;

import java.util.List;

public interface MemberDao {
    /***
     * Dao层实现
     * @param shopid
     * @return
     */
    List<MemberInfo> getAllMemberByMerchantId(String shopid);

    /***
     * Dao层实现 获取单个用户信息
     * @param msisdn
     * @return
     */
    MemberInfo getMemberInfoByMsisdn(String msisdn);

    /***
     *
     * @param msisdn
     * @param balance
     * @param integral
     */
    void updateMemberInfo(String msisdn, String balance, String integral);
}
