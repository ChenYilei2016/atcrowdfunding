package com.chenyilei.atcrowdfunding.common.h;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/25- 12:52
 */
public class Page<T> {
    private List<T> datas;

    //总页数
    private int totalno;
    //总element 数
    private int totalsize;

    //当前页数
    private int pageno;
    //当前页间隔
    private int pagesize;

    public Page() {
    }

    public Page(PageInfo pageInfo){
        this.datas = pageInfo.getList();
        this.pageno = pageInfo.getPageNum();
        this.pagesize= pageInfo.getPageSize();
        this.totalno = pageInfo.getPages();
        this.totalsize = (int)pageInfo.getTotal();
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getTotalno() {
        return totalno;
    }

    public void setTotalno(int totalno) {
        this.totalno = totalno;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public int getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(int totalsize) {
        this.totalsize = totalsize;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }



}
