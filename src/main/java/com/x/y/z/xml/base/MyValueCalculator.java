package com.x.y.z.xml.base;

import org.springframework.beans.factory.BeanNameAware;

/**
 * Created by 1234qwer on 2018/5/14.
 */
public class MyValueCalculator implements BeanNameAware{

    public String computeValue(String input) {
        System.out.println("computeValue:"+input);
        return input;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("====setBeanName===:"+s);
    }
}