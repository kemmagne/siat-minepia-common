/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.utils;

/**
 *
 * @author boris.tomfeu
 */
public class RequestPage {

    private int page;
    private int pageSize;
    private String sortField;
    private Object sortOrder;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Object getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Object sortOrder) {
        this.sortOrder = sortOrder;
    }

}
