package com.youga.mcc.dao;

import com.youga.mcc.obj.GoodsBase;

import java.util.List;

public interface StoreDao {

    /***
     * get all store by shop id
     * @param shopid
     * @return
     */
    List<GoodsBase> getAllStoreFromMerchant(String shopid);

    /***
     * modifyGoodsCls
     * @param merchantid
     * @param goodsid
     * @param clsType
     * @return
     */
    boolean modifyGoodsClassify(String merchantid, String goodsid, String clsType);

    /***
     * 获取商品通过clsType;
     * @param shopid
     * @param clsType
     * @return
     */
    List<GoodsBase> getAllStoreFromClsType(String shopid, String clsType);

    /***
     * 获取商品通过Name(模糊匹配)
     * @param shopid
     * @param goodsName
     * @return
     */
    List<GoodsBase> getAllStoreFromGoodsName(String shopid, String goodsName) throws Exception;

    /***
     * 2019-01-13 按照ID查询 Dao层实现
     * @param shopid
     * @param goodsid
     * @return
     */
    GoodsBase getAllStoreFromGoodsId(String shopid, String goodsid);

    /***
     * 2019-01-04 添加商品 Dao层实现
     * 成功返回true
     * @param shopid
     * @param goodsInfo
     * @return
     */
    boolean addAllStoreFromGoodsId(String shopid, GoodsBase goodsInfo);


    /***
     * 续货Dao层实现(update)
     * @param shopid
     * @param goodsid
     * @param goodscount
     * @return
     */
    boolean conAllStoreFromGoodsId(String shopid, String goodsid, String goodscount);

    /***
     * 2019-05-05 通过ID获取商品信息
     * @param goodsid
     * @param shopid
     * @return
     */
    GoodsBase getGoodsInfoById(String goodsid, String shopid);

    /****
     * 2019-05-06 通过存储过程直接对数据进行处理，优化业务层逻辑；
     * @param shopid
     * @param goodsid
     */
    List<GoodsBase> setBillList(String shopid, String goodsid);


    //同步修改
    void modifBillList(String shopid, String goodsid, String modify_type);

    void clearBill(String shopid);
}
