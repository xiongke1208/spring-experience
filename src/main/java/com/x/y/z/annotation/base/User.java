package com.x.y.z.annotation.base;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 1234qwer on 2018/5/14.
 */
@Data
@Component
public class User implements InitializingBean{

    @Value("${user.age}")
    private String age;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("===========user created============");
    }
}
