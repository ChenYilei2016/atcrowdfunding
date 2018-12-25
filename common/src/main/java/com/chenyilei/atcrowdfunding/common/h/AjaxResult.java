package com.chenyilei.atcrowdfunding.common.h;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/24- 19:56
 */

public class AjaxResult <T>{
    private boolean success ;
    private String message ;
    private Page<T> page;

    public boolean isSuccess() {
        return success;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public boolean getSuccess() {
        return success;
    }

    public AjaxResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
