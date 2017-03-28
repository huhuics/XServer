/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xserver.util.LogUtil;

/**
 * 简单的Servlet
 * @author HuHui
 * @version $Id: PrimitiveServlet.java, v 0.1 2017年3月28日 上午9:40:15 HuHui Exp $
 */
public class PrimitiveServlet implements Servlet {

    private static final Logger logger = LoggerFactory.getLogger(PrimitiveServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        LogUtil.info(logger, "PrimitiveServlet.init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        LogUtil.info(logger, "PrimitiveServlet.service");
        PrintWriter out = res.getWriter();
        out.println("Hello, this message is from PrimitiveServlet");
        out.print("from PrimitiveServlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        LogUtil.info(logger, "PrimitiveServlet.destroy");
    }

}
