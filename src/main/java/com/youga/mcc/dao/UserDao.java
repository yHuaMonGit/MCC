package com.youga.mcc.dao;

public interface UserDao {
    /***
     * this method for check user is legal in
     * database
     * if not correct :false;
     * correct : true;
     * @param acc
     * @param pass
     * @return
     */
    boolean allowAccess(String acc, String pass);

    /***
     * get shop id by user;
     * @param acc
     * @return
     */
    String getShopIdByUser(String acc);
}
