package com.example.zdnl7.utils;

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

    /**
     * 获取四位随机数字短信验证码
     *
     * @return
     */
    public static String getRandomCode() {
        StringBuffer codeStrBuf = new StringBuffer();
        String sources = "0123456789";
        Random rand = new Random();
        for (int j = 0; j < 4; j++) {
            codeStrBuf.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return codeStrBuf.toString();
    }

}
