package com.example.zdnl7.Util;

import org.springframework.stereotype.Component;

import java.util.Random;

//生成验证码
@Component
public class RandomUtil {
    public String creatVerifyCode() {
        int result=0;
        Random r = new Random(System.currentTimeMillis());
        for (int cnt =1;cnt<=6;cnt++) {
            result=result*10+r.nextInt()%10;
        }
        return String.valueOf(result);
    }

    public String creatSessionID() {
        //TODO:写一个生成sessionID的接口
        return null;
    }
}
