package com.youga.mcc.obj;

import java.util.ArrayList;
import java.util.List;

public class MerchantInfo {

    /***
     * merchant data structure
     */

    //merchant base;
    String merchantId = null;
    String merchantName = null;
    String longitude = null;
    String latitude = null;
    String medicalFlag = null;
    String serviceFlag = null;
    String shopFlag = null;


    //merchant Service
    List<PetService> services = new ArrayList<>();

    //login info
    String loginUser = null;


    public MerchantInfo(String merchantId, String merchantName, String longitude, String latitude, String medicalFlag, String serviceFlag, String shopFlag) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.medicalFlag = medicalFlag;
        this.serviceFlag = serviceFlag;
        this.shopFlag = shopFlag;
    }

    public MerchantInfo() {

    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getMedicalFlag() {
        return medicalFlag;
    }

    public void setMedicalFlag(String medicalFlag) {
        this.medicalFlag = medicalFlag;
    }

    public String getServiceFlag() {
        return serviceFlag;
    }

    public void setServiceFlag(String serviceFlag) {
        this.serviceFlag = serviceFlag;
    }

    public String getShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(String shopFlag) {
        this.shopFlag = shopFlag;
    }

    public List<PetService> getServices() {
        return services;
    }

    public void setServices(List<PetService> services) {
        this.services = services;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
}
