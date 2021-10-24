package com.atguigu.entity;

import java.io.Serializable;

/**
 * QueryPageBean
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-26
 * @Description:
 *
 * QueryPageBean:表示请求参数
 */
public class QueryPageBean implements Serializable {
    /**
     * 定义查询条件 where ，根据用户名查询 name = ?
     */
    private String queryString;
    /**
     * 定义每个页面需要展示的数据
     */
    private Integer pageSize;
    /**
     * 定义页码
     */
    private Integer currentPage;
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}