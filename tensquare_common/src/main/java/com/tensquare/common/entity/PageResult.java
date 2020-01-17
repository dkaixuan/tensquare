package com.tensquare.common.entity;

import java.util.List;

/**
 * @author kaixuan
 * @version 1.0
 * 分页结果类
 * @date 2020/1/13 14:29
 */
public class PageResult<T>{

    private Long total;
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
