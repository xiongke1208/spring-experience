package com.x.y.z.annotation.base.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 1234qwer on 2018/5/23.
 */
@Controller
public class IndexController implements InitializingBean{

    @RequestMapping("index")
    @ResponseBody
    public String index(){
        System.out.println(".............");
        return "success";
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("=============IndexController created==============");
    }
}
