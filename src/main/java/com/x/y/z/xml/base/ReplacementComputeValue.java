package com.x.y.z.xml.base;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * Created by 1234qwer on 2018/5/14.
 */
public class ReplacementComputeValue implements MethodReplacer {

    public Object reimplement(Object o, Method m, Object[] args) throws Throwable {

        System.out.println("reimplement");
        System.out.println(o);

        String input = (String) args[0];

        System.out.println(input);

        return "reimplement";
    }

}
