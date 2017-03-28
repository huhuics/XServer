/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.constant;

import java.io.File;

/**
 * 常量
 * @author HuHui
 * @version $Id: Constants.java, v 0.1 2017年3月28日 上午10:52:29 HuHui Exp $
 */
public class Constants {

    /** 应用根目录下的webroot路径 */
    public static final String WEB_ROOT         = System.getProperty("user.dir") + File.separator + "webroot";

    /** 结束指令 */
    public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

}
