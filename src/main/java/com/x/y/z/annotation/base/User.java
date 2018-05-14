package com.x.y.z.annotation.base;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 1234qwer on 2018/5/14.
 */
@Data
@Component
public class User {

    @Value("${jdbc.url}")
    private String age;

}
