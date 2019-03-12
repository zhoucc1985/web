package com.cloud.common.utils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;

/**
 * 获取IP工具类
 *
 * @author huangYl
 * @date 2018/11/26
 **/

public class IpUtils {

    /**
     * 获取反向代理的真实IP
     */
    public static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "X-REAL-IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR",
            "REMOTE-HOST"
    };

    /**
     * 获取客户端的IP地址
     */
    public static String getClientIp(HttpServletRequest request) {
        String clientIp = getRealClientIp(request);
        if (null != clientIp && !clientIp.trim().isEmpty()) {
            return clientIp;
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取代理的IP地址
     */
    private static String getRealClientIp(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取本机ipv4地址
     * @author Pan Jianneng
     * @date 2018/12/19 4:28 PM
     * @return java.lang.String
     */
    public static String getCurrentServerIpAddress(){
        try {
            Enumeration<?> enumeration=NetworkInterface.getNetworkInterfaces();
            InetAddress ip=null;
            while(enumeration.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) enumeration.nextElement();
                Enumeration<?> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address){
                        String ip1=ip.getHostAddress();
                        //出去本地IP，直接返回第一个IP
                        if(!StringUtils.equals("127.0.0.1",ip1)){
                            return ip1;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        getCurrentServerIpAddress();
    }
}
