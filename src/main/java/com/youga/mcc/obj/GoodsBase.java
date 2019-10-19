package com.youga.mcc.obj;



public class GoodsBase {
    String GoodsId;
    String GoodsName;
    String GoodsPrice;
    String GoodsCount;
    String GoodsIntergral;
    String GoodsStore;

    //2019-01-10 新增对应字段对应接收库存信息
    String merchantId = ""; //商户对应表;
    String GoodsClassify = ""; //品牌;
    String GoodsInstock = ""; //库存量;
    String GoodsStander = ""; //规格;

    //2019-08-01 新增商品保质期，用于打印产品条码
    String com_shelf_life = "";

    //2019-09-12 新增字段用于标识商品是否打折
    int getIsOffset = 0;

    //库存分类
    String GoodsCls = "";


    public GoodsBase(){}

    public GoodsBase(String goodsId, String goodsName, String goodsPrice, String goodsCount, String goodsIntergral) {
        GoodsId = goodsId;
        GoodsName = goodsName;
        GoodsPrice = goodsPrice;
        GoodsCount = goodsCount;
        GoodsIntergral = goodsIntergral;
    }

    public int getGetIsOffset() {
        return getIsOffset;
    }

    public void setGetIsOffset(int getIsOffset) {
        this.getIsOffset = getIsOffset;
    }

    public String getCom_shelf_life() {
        return com_shelf_life;
    }

    public void setCom_shelf_life(String com_shelf_life) {
        this.com_shelf_life = com_shelf_life;
    }

    public String getGoodsId() {
        return GoodsId;
    }

    public void setGoodsId(String goodsId) {
        GoodsId = goodsId;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getGoodsPrice() {
        return GoodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        GoodsPrice = goodsPrice;
    }

    public String getGoodsCount() {
        return GoodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        GoodsCount = goodsCount;
    }

    public String getGoodsIntergral() {
        return GoodsIntergral;
    }

    public void setGoodsIntergral(String goodsIntergral) {
        GoodsIntergral = goodsIntergral;
    }

    public String getGoodsStore() {
        return GoodsStore;
    }

    public void setGoodsStore(String goodsStore) {
        GoodsStore = goodsStore;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getGoodsClassify() {
        return GoodsClassify;
    }

    public void setGoodsClassify(String goodsClassify) {
        GoodsClassify = goodsClassify;
    }

    public String getGoodsInstock() {
        return GoodsInstock;
    }

    public void setGoodsInstock(String goodsInstock) {
        GoodsInstock = goodsInstock;
    }

    public String getGoodsStander() {
        return GoodsStander;
    }

    public void setGoodsStander(String goodsStander) {
        GoodsStander = goodsStander;
    }

//    public void calculateStore()
//    {
//        GoodsDao goodsDao = new GoodsDaoImpl();
//        this.GoodsStore = String.valueOf(Integer.valueOf(goodsDao.getGoodsStore(this.getGoodsId()))-Integer.valueOf(this.getGoodsCount()));
//    }


    public String getGoodsCls() {
        return GoodsCls;
    }

    public void setGoodsCls(String goodsCls) {
        GoodsCls = goodsCls;
    }
}
