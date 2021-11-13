package com.example.zdnl7.config.system;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Salong
 * @date 2021/3/10 18:37
 * @Email:salong0503@aliyun.com
 */
@Configuration
public class zhenziSMS {

    @Value("${sms.apiUrl}")
    //访问路径（个人开发者使用https://sms_developer.zhenzikj.com，企业开发者使用https://sms.zhenzikj.com）
    private String apiUrl;

    @Value("${sms.appId}")
    private String appId;

    @Value("${sms.appSecret}")
    private String appSecret;

    //设置过期时间
    @Value("${sms.timeOut}")
    private Integer timeOut;

    //设置验证码长度
    @Value("${sms.codeLength}")
    private Integer codeLength;

    private ZhenziSmsClient client;


    @Bean
    public ZhenziSmsClient initSMS() {
        if (client == null) {
            client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            return client;
        }
        return client;
    }

    /**
     * @date: 2021/11/13 4:07 下午
     * @author: yanzx
     * @desc: 查询榛子云剩余当前账户可发验证码短信条数
     */
    public Integer checkBalance() throws Exception {
        String balance = client.balance();   //{"code":0,"data":537}
        System.out.println(balance);
        isSendSuccess isSendSuccess = JSONObject.parseObject(balance, isSendSuccess.class);
        return (Integer) isSendSuccess.getData();
    }

    /**
     * @date: 2021/11/13 4:07 下午
     * @author: yanzx
     * @desc: 生成验证码和验证码id
     */
    private Map<String, Object> createVerificationCode() {
        HashMap<String, Object> map = new HashMap<>();
        //这个是榛子云短信平台用户中心下的短信管理的短信模板的模板id
        map.put("templateId", "7234");
        //生成验证码
        int pow = (int) Math.pow(10, codeLength - 1);
        String verificationCode = String.valueOf((int) (Math.random() * 9 * pow + pow));
        //随机生成messageId，验证验证码的时候，需要携带这个参数去取验证码
        String messageId = UUID.randomUUID().toString();
        map.put("messageId", messageId);
        //两个参数分别为验证码和过期时间
        map.put("code", verificationCode);
        map.put("expireMinute", String.valueOf(timeOut));
        return map;
    }

    //发送验证码（如果params的success为true，则发送成功，则需要把params中的messageId和验证码存起来，验证验证码的时候用到）
    public Map<String, Object> sendMessage(String phoneNumber, String clientIp) throws Exception {
        Map<String, Object> params = createVerificationCode();
        //发送手机目标（number字段不可修改）
        params.put("phoneNumber", phoneNumber);
        //防止一个客户端多次刷验证码，防刷专用,这个clientIp只是个防刷标记，
        // 不一定是客户端ip，也可以是客户端登录的账号，或者能鉴权的属性
        if (StringUtils.isNotBlank(clientIp)) {
            params.put("clientIp", clientIp);
        }
        String result = client.send(params);
        isSendSuccess success = JSONObject.parseObject(result, isSendSuccess.class);
        System.out.println(result);
        if (success.getCode() == 0) {
            params.put("success", true);
        } else {
            params.put("success", false);
        }
        return params;
    }

    //验证短信验证码,传入缓存中存入的messageId和验证码
    public boolean checkMessage(String messageId, String cacheCode) throws Exception {
        String result = client.findSmsByMessageId(messageId);
        Verification verification = JSONObject.parseObject(result, Verification.class);
        if (verification.getCode() == 0) {
            //取到了数据，开始验证码是否正确
            String veificationCode = verification.getData().getMessage();
            int index = veificationCode.indexOf("验证码:");
            String code = veificationCode.substring(index + 4, index + 4 + codeLength);
            //验证验证码
            if (!code.equals(cacheCode)) {
                return false;
            }
            //验证时间是否过期
            Date createTime = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").parse(verification.getData().getCreateTime());
            long spent = new Date().getTime() - createTime.getTime();
            return spent / (1000 * 60) <= timeOut;
        } else {
            return false;
        }
    }

    //向榛子云发送验证码请求时候，榛子云返回的报文,code=0为成功
    //实例：{"code":0,"data":"发送成功"}
    @Data
    static class isSendSuccess implements Serializable {
        private Integer code;
        private Object data;
    }

    //验证验证码的时候，榛子云返回的报文

    /**
     * @author yanzx
     * @description: 实例：{"code":0,"data":{"message":"验证码:33356，2分钟内有效，请勿泄漏给他人使用。",
     * "appName":"云短信体验",
     * "createTime":"2021-03-10 19:24:19",
     * "status":"success",
     * "toNumber":"15871770252",
     * "messageId":"123"}}
     * @date: 2021/11/13 3:32 下午
     */
    @Data
    static class Verification implements Serializable {
        private Integer code;
        private Message data;
    }

    @Data
    static class Message implements Serializable {
        private String message;
        private String appName;
        private String createTime;
        private String status;
        private String toNumber;
        private String messageId;
    }
}
