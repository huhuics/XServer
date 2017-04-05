/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.proxy;

/**
 * 
 * @author HuHui
 * @version $Id: OperateImpl.java, v 0.1 2017年4月1日 下午3:17:43 HuHui Exp $
 */
public class OperateImpl implements Operate {

    @Override
    public void opMethod1() {
        System.out.println("call from opMethod1");
    }

    @Override
    public void opMethod2(int i) {
        System.out.println("call from opMethod2, i=" + i);
    }

    @Override
    public int opMethod3(int i) {
        System.out.println("call from opMethod3, i=" + i);
        return i;
    }

}
