package com.chenyilei.atcrowdfunding.main.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @date 2018/12/23- 14:07
 */

@Controller
public class TESTT {

    @RequestMapping("/a")
    public String aa(){
        return "success";
    }

}
