package com.fincodehub.logaop.log;


import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HtmlUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author YangJian
 * @version 1.0.0
 * @title IPBlackListAspect
 * @create 2025/4/24 23:17
 * @description <TODO description class purpose>
 */
@Aspect
@Component
public class IPBlackListAspect {

    public  final Map<String, List<Long>> requestTimes = new ConcurrentHashMap<>();
    public  final Map<String, Long> blackList  = new ConcurrentHashMap<>();


    @Before("@annotation(ipBlackList)")
    public void doBefore( IPBlackList ipBlackList) {
        // 获取客户端IP地址（需要根据实际情况实现）
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String clientIP = getClientIP(request);
        System.out.println("clientIP:"+clientIP);
        if (StringUtils.isBlank(clientIP)) {
            return;
        }
        // 内网不查询
        clientIP = StringUtils.contains(clientIP, "0:0:0:0:0:0:0:1") ? "127.0.0.1" : HtmlUtil.cleanHtmlTag(clientIP);
        System.out.println("clientIP1:"+clientIP);
        if (NetUtil.isInnerIP(clientIP)) {
            System.out.println("内网不查询");
            return;
        }

        int maxRequests = ipBlackList.maxRequests();
        long timeWindow = ipBlackList.timeWindow();
        long blockTime = ipBlackList.blockTime();
        System.out.println("maxRequests:"+maxRequests);
        long currentTime = System.currentTimeMillis();

        // 检查 IP 是否在黑名单中
        if (blackList.containsKey(clientIP)) {
            long blacklistedAt = blackList.get(clientIP);
            if (currentTime - blacklistedAt < blockTime) {
                throw new RuntimeException("IP 已被拉黑，请稍后再试");
            } else {
                blackList.remove(clientIP); // 移除过期的黑名单记录
                blackList.remove(clientIP); // 重置计时
            }
        }
        // 获取该 IP 的访问记录并清除超过时间窗口的记录
        List<Long> times = requestTimes.getOrDefault(clientIP, new CopyOnWriteArrayList<>());
        times.removeIf(time -> currentTime - time > timeWindow);
        times.add(currentTime); // 记录当前访问时间
        requestTimes.put(clientIP, times);

        // 检查在时间窗口内的请求次数
        if (times.size() > maxRequests) {
            blackList.put(clientIP, currentTime); // 拉黑 IP
            throw new RuntimeException("请求次数过多，IP 已被拉黑");
        }
    }
    private String getClientIP(HttpServletRequest request) {
        String clientIP = request.getHeader("X-Forwarded-For");
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("X-Real-IP");
        }
        if (clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP)) {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }
}
