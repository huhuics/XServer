/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.ch02;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xserver.constant.Constants;
import cn.xserver.util.LogUtil;

/**
 * Servlet处理类
 * @author HuHui
 * @version $Id: ServletProcessor1.java, v 0.1 2017年3月28日 上午10:04:34 HuHui Exp $
 */
public class ServletProcessor1 {

    private static final Logger logger = LoggerFactory.getLogger(ServletProcessor1.class);

    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);

            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            LogUtil.info(logger, "repository={0}", repository);
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (Exception e) {
            LogUtil.error(e, logger, "处理servlet异常");
        }

        Class myClass = null;

        try {
            LogUtil.info(logger, "servletName={0}", servletName);
            //            myClass = loader.loadClass(servletName);
            myClass = Class.forName("cn.xserver.ch02.PrimitiveServlet");
        } catch (ClassNotFoundException e) {
            LogUtil.error(e, logger, "servlet类无法找到");
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request, response);
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("", e);
        } catch (ServletException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }

    }
}
