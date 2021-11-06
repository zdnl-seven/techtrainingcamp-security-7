package com.example.zdnl7.utils;

import org.springframework.stereotype.Component;

//处理是否恶意访问
@Component
public class SecurityCheckUtil {
    //TODO：风控相关的逻辑都还没写
    //不同方法对应的是不同接口的判断逻辑，可以看着群里md写，不好写或者写不了的就改就行
    public int securityCheckApplyCode(String phoneNumber,String ip,String deviceID) {
        return 0;
    }

    public int securityCheckRegister(String phoneNumber, String ip, String deviceID) {
        return 0;
    }

    public int securityCheck(String ip,String deviceID) {
        return 0;
    }
    public int securityCheckLogin(String ip,String deviceID) {
        return 0;
    }
}
