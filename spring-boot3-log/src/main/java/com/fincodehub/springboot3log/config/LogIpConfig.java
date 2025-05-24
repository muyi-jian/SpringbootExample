package com.fincodehub.springboot3log.config;




import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LogIpConfig
 * @create 2025/5/22 21:04
 * @description 日志中获取IP
 */
public class LogIpConfig extends ClassicConverter {
    private static String hostAddress;
    static{
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return hostAddress;
    }
}