package com.example.zdnl7.Util;

import org.springframework.stereotype.Component;

import java.util.Random;

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
