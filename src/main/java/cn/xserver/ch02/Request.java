/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xserver.util.LogUtil;

/**
 * 请求类
 * @author HuHui
 * @version $Id: Request.java, v 0.1 2017年3月28日 上午10:02:56 HuHui Exp $
 */
public class Request implements ServletRequest {

    private static final Logger logger = LoggerFactory.getLogger(Request.class);

    private InputStream         input;

    private String              uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public String getUri() {
        return uri;
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }
        return null;
    }

    public void parse() {
        //读取socket中的字节
        StringBuffer request = new StringBuffer();

        int i;
        byte[] buffer = new byte[2048];

        try {
            i = input.read(buffer);
        } catch (IOException e) {
            i = -1;
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }

        uri = parseUri(request.toString());

        LogUtil.info(logger, "request.uri={0}", uri);
    }

    /** 
     * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
     */
    @Override
    public Object getAttribute(String name) {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getAttributeNames()
     */
    @Override
    public Enumeration getAttributeNames() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getCharacterEncoding()
     */
    @Override
    public String getCharacterEncoding() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
     */
    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
    }

    /** 
     * @see javax.servlet.ServletRequest#getContentLength()
     */
    @Override
    public int getContentLength() {
        return 0;
    }

    /** 
     * @see javax.servlet.ServletRequest#getContentType()
     */
    @Override
    public String getContentType() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getInputStream()
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
     */
    @Override
    public String getParameter(String name) {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getParameterNames()
     */
    @Override
    public Enumeration getParameterNames() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
     */
    @Override
    public String[] getParameterValues(String name) {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getParameterMap()
     */
    @Override
    public Map getParameterMap() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getProtocol()
     */
    @Override
    public String getProtocol() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getScheme()
     */
    @Override
    public String getScheme() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getServerName()
     */
    @Override
    public String getServerName() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getServerPort()
     */
    @Override
    public int getServerPort() {
        return 0;
    }

    /** 
     * @see javax.servlet.ServletRequest#getReader()
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getRemoteAddr()
     */
    @Override
    public String getRemoteAddr() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getRemoteHost()
     */
    @Override
    public String getRemoteHost() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#setAttribute(java.lang.String, java.lang.Object)
     */
    @Override
    public void setAttribute(String name, Object o) {
    }

    /** 
     * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
     */
    @Override
    public void removeAttribute(String name) {
    }

    /** 
     * @see javax.servlet.ServletRequest#getLocale()
     */
    @Override
    public Locale getLocale() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getLocales()
     */
    @Override
    public Enumeration getLocales() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#isSecure()
     */
    @Override
    public boolean isSecure() {
        return false;
    }

    /** 
     * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
     */
    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
     */
    @Override
    public String getRealPath(String path) {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getRemotePort()
     */
    @Override
    public int getRemotePort() {
        return 0;
    }

    /** 
     * @see javax.servlet.ServletRequest#getLocalName()
     */
    @Override
    public String getLocalName() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getLocalAddr()
     */
    @Override
    public String getLocalAddr() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletRequest#getLocalPort()
     */
    @Override
    public int getLocalPort() {
        return 0;
    }

}
