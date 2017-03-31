/**
S * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.ch02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xserver.constant.Constants;
import cn.xserver.util.LogUtil;

/**
 * 响应类
 * @author HuHui
 * @version $Id: Response.java, v 0.1 2017年3月28日 上午10:03:31 HuHui Exp $
 */
public class Response implements ServletResponse {

    private static final Logger logger      = LoggerFactory.getLogger(Response.class);

    private static final int    BUFFER_SIZE = 1024;

    private Request             request;

    private OutputStream        output;

    private PrintWriter         writer;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {

        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        try {
            File file = new File(Constants.WEB_ROOT, request.getUri());

            LogUtil.info(logger, "file path={0}", file.getAbsolutePath());

            if (file.exists()) {
                fis = new FileInputStream(file);
                int ch;
                while ((ch = fis.read(bytes)) != -1) {
                    output.write(bytes, 0, ch);
                }
            } else { //file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n" + "Content-Length: 23\r\n" + "\r\n"
                                      + "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            LogUtil.error(e, logger, "发送静态资源异常");
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

    }

    /** 
     * @see javax.servlet.ServletResponse#getCharacterEncoding()
     */
    @Override
    public String getCharacterEncoding() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletResponse#getContentType()
     */
    @Override
    public String getContentType() {
        return null;
    }

    /** 
     * @see javax.servlet.ServletResponse#getOutputStream()
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    /** 
     * @see javax.servlet.ServletResponse#getWriter()
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(output, true);//true标识为auto flush,对println有用,但对print无用
        return writer;
    }

    /** 
     * @see javax.servlet.ServletResponse#setCharacterEncoding(java.lang.String)
     */
    @Override
    public void setCharacterEncoding(String charset) {
    }

    /** 
     * @see javax.servlet.ServletResponse#setContentLength(int)
     */
    @Override
    public void setContentLength(int len) {
    }

    /** 
     * @see javax.servlet.ServletResponse#setContentType(java.lang.String)
     */
    @Override
    public void setContentType(String type) {
    }

    /** 
     * @see javax.servlet.ServletResponse#setBufferSize(int)
     */
    @Override
    public void setBufferSize(int size) {
    }

    /** 
     * @see javax.servlet.ServletResponse#getBufferSize()
     */
    @Override
    public int getBufferSize() {
        return 0;
    }

    /** 
     * @see javax.servlet.ServletResponse#flushBuffer()
     */
    @Override
    public void flushBuffer() throws IOException {
    }

    /** 
     * @see javax.servlet.ServletResponse#resetBuffer()
     */
    @Override
    public void resetBuffer() {
    }

    /** 
     * @see javax.servlet.ServletResponse#isCommitted()
     */
    @Override
    public boolean isCommitted() {
        return false;
    }

    /** 
     * @see javax.servlet.ServletResponse#reset()
     */
    @Override
    public void reset() {
    }

    /** 
     * @see javax.servlet.ServletResponse#setLocale(java.util.Locale)
     */
    @Override
    public void setLocale(Locale loc) {
    }

    /** 
     * @see javax.servlet.ServletResponse#getLocale()
     */
    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public void setContentLengthLong(long len) {
    }

}
