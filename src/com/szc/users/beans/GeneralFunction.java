package com.szc.users.beans;

import java.util.List;

/**
 * @author traivs
 * 用于定义获取bean方法
 */
public class GeneralFunction {
    private int startNum;  //开始的行数
    private  int pageNum;  //每页的数据量
    private List<?> beanList;

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<?> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<?> beanList) {
        this.beanList = beanList;
    }
}
