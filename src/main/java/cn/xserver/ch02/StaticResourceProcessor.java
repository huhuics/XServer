/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.ch02;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xserver.util.LogUtil;

/**
 * 静态资源处理类
 * @author HuHui
 * @version $Id: StaticResourceProcessor.java, v 0.1 2017年3月28日 上午10:04:02 HuHui Exp $
 */
public class StaticResourceProcessor {

    private static final Logger logger = LoggerFactory.getLogger(StaticResourceProcessor.class);

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            LogUtil.error(e, logger, "处理静态资源异常");
        }
    }

}
