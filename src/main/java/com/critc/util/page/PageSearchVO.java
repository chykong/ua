package com.critc.util.page;


import com.critc.util.global.GlobalConst;

/**
 * 分页查询类的父类
 *
 * @author 孔垂云
 */
public class PageSearchVO {
    private int total = 0;
    private int pageIndex = 1;
    private int pageSize = GlobalConst.pageSize;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
