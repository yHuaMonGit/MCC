package com.youga.mcc.service;

import com.youga.mcc.obj.FlatFormInfo;
import com.youga.mcc.obj.GoodsBase;

import java.util.List;

public interface StoreService {

    /***
     * get all goods by merchant id
     * @param shopid
     * @return
     */
    List<GoodsBase> getAllStoreByMerchantId(String shopid);

    /***
     * modify goods cls
     * @param merchantid
     * @param goodsid
     * @param clsType
     * @return
     */
    boolean modifyGoodsCls(String merchantid, String goodsid, String clsType);

    /***
     * 2019-01-12 新增按照分类获取商品列表
     * @param shopid
     * @param clsType
     * @return
     */
    List<GoodsBase> getAllStoreByClsType(String shopid, String clsType);

    /***
     * 2019-01-12 新增按照商品名模糊匹配
     * @param shopid
     * @param goodsName
     * @return
     */
    List<GoodsBase> getAllStoreByGoodsName(String shopid, String goodsName) throws Exception;

    /***
     * 2019-01-13 新增按照商品Id查询
     * @param shopid
     * @param goodsid
     * @return
     */
    GoodsBase getAllStoreByGoodsId(String shopid, String goodsid);

    /***
     * 2019-01-14 检查商品是否存在
     * @param shopid
     * @param goodsid
     * @return
     */
    GoodsBase checkStoreByGoodsId(String shopid, String goodsid);

    /***
     * 2019-01-14 添加商品
     * 成功返回true;
     * 失败返回false
     * @param shopid
     * @param goodsInfo
     * @return
     */
    boolean addStoreByGoodsId(String shopid, GoodsBase goodsInfo);

    /***
     * 2019-01-15 商品续货
     * success:true
     * failed:false;
     * @param shopid
     * @param goodsid
     * @param goodscount
     * @return
     */
    boolean conStoreByGoodsId(String shopid, String goodsid, String goodscount);

}
