package com.chenyilei.atcrowdfunding.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/23- 10:40
 */

@ControllerAdvice
public class TECon {

    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView ec(Exception e, Map mp){
        mp.put("e",e.getMessage());
        return new ModelAndView("error/error",mp);
    }
}
