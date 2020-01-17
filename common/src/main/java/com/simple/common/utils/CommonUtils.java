package com.simple.common.utils;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 基本工具类
 * @author swen
 */
public class CommonUtils {

    /**
     * 生成uuid
     * @return
     */
    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 判断字符串是不是NULL或是空字符串,如果是，返回true，不是false
     * @param str 待判断字符串
     * @return true/false
     */
    public static boolean isNullOrEmpty(final String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断字符串对象是不是NULL或空，如果是，返回true，不是false
     * @param str 字符串对象
     * @return true/false
     */
    public static boolean isNullOrEmpty(final Object str){
        return str == null || "".equals(str.toString());
    }

    /**
     * 数组转字符串
     * @param array 数组
     * @param surffix 分割符号
     * @return
     */
    public static String array2String(final String[] array,final String surffix){
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<array.length;i++){
            if(i==array.length-1){
                builder.append(array[i]);
            }
            else {
                builder.append(array[i]).append(surffix);
            }
        }
        return builder.toString();
    }

    /**
     * 获取IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        //使用代理，则获取第一个IP地址
        if(StringUtils.isEmpty(ip) && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
