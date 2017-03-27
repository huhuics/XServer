/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.ch01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xserver.util.LogUtil;

/**
 * 简单的web服务器
 * @author HuHui
 * @version $Id: HttpServer.java, v 0.1 2017年3月27日 下午8:01:38 HuHui Exp $
 */
public class HttpServer {

    private static final Logger logger           = LoggerFactory.getLogger(HttpServer.class);

    public static final String  WEB_ROOT         = System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean             shutdown         = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {

        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port); //绑定本地端口
        } catch (IOException e) {
            LogUtil.error(e, logger, "创建ServerSocket异常");
            System.exit(1);
        }

        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //创建request
                Request request = new Request(input);
                request.parse();

                //创建response
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();

                String uri = request.getUri();
                if (StringUtils.isNotEmpty(uri)) {
                    shutdown = uri.equals(SHUTDOWN_COMMAND);
                }
            } catch (Exception e) {
                LogUtil.error(e, logger, "监听异常");
                continue;
            }

        }

    }
}
