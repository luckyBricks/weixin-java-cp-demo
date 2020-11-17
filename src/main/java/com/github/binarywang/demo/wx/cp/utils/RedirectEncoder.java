package com.github.binarywang.demo.wx.cp.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author ：Bricks
 * @date ：Created in 2020/11/17 20:18
 * @description：重定向URLencoder
 * @modified By：
 */
public class RedirectEncoder {
    public static String encodeX(String target) throws UnsupportedEncodingException {
        return URLEncoder.encode(target, StandardCharsets.UTF_8.toString());
    }

    public static void main(String[] args) {
        try {
            String tableauUrl = "http://www.baidu.com?parm1=1&parm2=2";
            String accUrl = "http://67b950926228.ngrok.io/wx/cp/oauth/getInfo?tableauPage=" + encodeX(tableauUrl);
            String impl = "http://67b950926228.ngrok.io/wx/cp/oauth/createUrlForCode?index=0&accUrl=" + encodeX(accUrl);
            System.out.println(impl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
}
