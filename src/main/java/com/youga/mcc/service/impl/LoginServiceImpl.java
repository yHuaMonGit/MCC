package com.youga.mcc.service.impl;

import com.youga.mcc.dao.UserDao;
import com.youga.mcc.dao.impl.UserDaoImpl;
import com.youga.mcc.service.LoginService;

public class LoginServiceImpl implements LoginService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean checkLogin(String acc, String pass) {

        // 先做成明文密码校验，后期修改为md5密码加密比较
        if (userDao.allowAccess(acc,pass)){
            return true;
        }else {
            return false;
        }
    }
}
