/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.ch02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xserver.constant.Constants;
import cn.xserver.util.LogUtil;

/**
 * 
 * @author HuHui
 * @version $Id: HttpServer1.java, v 0.1 2017年3月28日 上午10:02:24 HuHui Exp $
 */
public class HttpServer1 {

    private static final Logger logger   = LoggerFactory.getLogger(HttpServer1.class);

    private boolean             shutdown = false;

    public static void main(String[] args) {
        HttpServer1 server = new HttpServer1();
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

                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                socket.close();

                String uri = request.getUri();
                if (StringUtils.isNotEmpty(uri)) {
                    shutdown = uri.equals(Constants.SHUTDOWN_COMMAND);
                }
            } catch (Exception e) {
                LogUtil.error(e, logger, "监听异常");
                continue;
            }

        }

    }

}
