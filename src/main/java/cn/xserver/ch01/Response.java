/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.ch01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xserver.util.LogUtil;

/**
 * 响应类
 * @author HuHui
 * @version $Id: Response.java, v 0.1 2017年3月27日 下午8:46:15 HuHui Exp $
 */
public class Response {

    private static final Logger logger      = LoggerFactory.getLogger(Response.class);

    private static final int    BUFFER_SIZE = 1024;

    private Request             request;

    private OutputStream        output;

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
            File file = new File(HttpServer.WEB_ROOT, request.getUri());

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

}
