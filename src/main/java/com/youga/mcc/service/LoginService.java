package com.youga.mcc.service;

public interface LoginService {

    /***
     * this method for check login user is legal
     * legal:true;
     * not:false;
     * @param acc
     * @param pass
     * @return
     */
    boolean checkLogin(String acc, String pass);

}
