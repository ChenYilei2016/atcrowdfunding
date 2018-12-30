package com.chenyilei.atcrowdfunding.common.h;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/24- 19:56
 */

@NoArgsConstructor
@Data
public class AjaxResult <T>{
    private boolean success = true;
    private String message ;
    private Page<T> page;
    private T data;

    public AjaxResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AjaxResult(boolean success, String message, Page page) {
        this.success = success;
        this.message = message;
        this.page = page;
    }

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
