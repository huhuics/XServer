/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 
 * @author HuHui
 * @version $Id: TimingInvocationHandler.java, v 0.1 2017年4月1日 下午3:22:50 HuHui Exp $
 */
public class TimingInvocationHandler implements InvocationHandler {

    private Object target;

    public TimingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = method.invoke(target, args);
        System.out.println(method.getName() + "costs time is " + (System.currentTimeMillis() - start));
        return obj;
    }

}
