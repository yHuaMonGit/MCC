package com.youga.mcc.service.impl;

import com.youga.mcc.obj.MemberInfo;
import com.youga.mcc.service.MemberService;

import java.util.List;

public class MemberServiceImpl implements MemberService {
    @Override
    public List<MemberInfo> getAllMemberByMerchantId(String shopid) {
        return memberDao.getAllMemberByMerchantId(shopid);
    }

    @Override
    public MemberInfo getMemberInfoByMsisdn(String msisdn) {
        return memberDao.getMemberInfoByMsisdn(msisdn);
    }

    @Override
    public void updateMemberInfo(String msisdn, String balance, String integral) {
        memberDao.updateMemberInfo(msisdn,balance,integral);
    }
}
