package com.chenyilei.atcrowdfunding.common.h;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/24- 19:56
 */

public class AjaxResult {
    private boolean success ;
    private String message ;

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
