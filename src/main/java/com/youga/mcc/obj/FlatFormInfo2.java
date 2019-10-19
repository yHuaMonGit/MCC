package com.youga.mcc.obj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatFormInfo2 {

    /***
     * 平面表单数据结构封装
     * 数据存储以map形式存储：<key,value>
     *     key:数据字段名称
     *     value:对应字段数据；
     * 封装中应包含 请求类名、请求字段数量、请求条件、查询状态；
     */

    private Map<String,Object> values = new HashMap<String,Object>();

    private String ClassName;

    private int feildsNum ;

    private List<String> conditionsList ;

    private int searchStatus;

    public FlatFormInfo2(String className, int feildsNum, List<String> conditionsList) {
        ClassName = className;
        this.feildsNum = feildsNum;
        this.conditionsList = conditionsList;
    }

    public FlatFormInfo2() {

    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public int getFeildsNum() {
        return feildsNum;
    }

    public void setFeildsNum(int feildsNum) {
        this.feildsNum = feildsNum;
    }

    public List<String> getConditionsList() {
        return conditionsList;
    }

    public void setConditionsList(List<String> conditionsList) {
        this.conditionsList = conditionsList;
    }

    public int getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(int searchStatus) {
        this.searchStatus = searchStatus;
    }
}
