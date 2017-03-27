/**
 * 深圳金融电子结算中心
 * Copyright (c) 1995-2017 All Rights Reserved.
 */
package cn.xserver.ch01;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xserver.util.LogUtil;

/**
 * 请求类
 * @author HuHui
 * @version $Id: Request.java, v 0.1 2017年3月27日 下午8:45:29 HuHui Exp $
 */
public class Request {

    private static final Logger logger = LoggerFactory.getLogger(Request.class);

    private InputStream         input;

    private String              uri;

    public Request(InputStream input) {
        this.input = input;
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
            LogUtil.error(e, logger, "读取Socket异常");
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }

        LogUtil.info(logger, "request.toString={0}", request.toString());

        uri = parseUri(request.toString());

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

    public String getUri() {
        return uri;
    }

}
