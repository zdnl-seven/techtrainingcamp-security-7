package com.example.zdnl7.utils;

import org.springframework.stereotype.Component;

//生成验证码
@Component
public class RandomUtil {
    public String creatVerifyCode() {
        StringBuilder numStr = new StringBuilder();
        int num;
        for (int i = 0; i < 6; i++) {
            // Math.random() 随机出0-1之间的实数，返回值是一个double 类型的
            num = (int) (Math.random() * 10);
            numStr.append(String.valueOf(num));
        }
        return numStr.toString();
    }

    public String creatSessionID() {
        //TODO:写一个生成sessionID的接口
        return null;
    }
}
