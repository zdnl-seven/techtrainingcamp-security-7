package com.example.zdnl7.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ApiAdapter {
    public static void adapt(JSONObject request, HttpServletRequest servletRequest) {
        adapt((Map<String, Object>) request, servletRequest);
    }

    public static void adapt(Map<String, Object> request, HttpServletRequest servletRequest) {
        if (request.containsKey("environment")) {
            return;
        }
        String ip = IpUtil.getIpAddr(servletRequest);
        request.put("environment", Map.of("ip", ip, "device_id", ip));
    }
}
