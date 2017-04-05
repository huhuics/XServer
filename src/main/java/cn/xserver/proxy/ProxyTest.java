/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.proxy;

import java.lang.reflect.Proxy;

/**
 * 
 * @author HuHui
 * @version $Id: ProxyTest.java, v 0.1 2017年4月1日 下午3:27:46 HuHui Exp $
 */
public class ProxyTest {

    public static void main(String[] args) {

        TimingInvocationHandler handler = new TimingInvocationHandler(new OperateImpl());

        //创建代理类的实例对象
        Operate operate = (Operate) Proxy.newProxyInstance(Operate.class.getClassLoader(), new Class[] { Operate.class }, handler);

        operate.opMethod1();
        operate.opMethod2(123);
        operate.opMethod3(34543);

    }
}
