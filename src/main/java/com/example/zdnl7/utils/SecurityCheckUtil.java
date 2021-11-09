package com.example.zdnl7.utils;

import com.example.zdnl7.dao.RequestDao;
import com.example.zdnl7.dao.UserDao;
import com.example.zdnl7.entity.RequestInfo;
import com.example.zdnl7.entity.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

//处理是否恶意访问
@Component
public class SecurityCheckUtil {

    @Resource
    RequestDao requestInfo;
    //TODO:更新Loginservice中的decisionType：当注册
    //不同方法对应的是不同接口的判断逻辑，可以看着群里md写，不好写或者写不了的就改就行

    //蒲俊宋：此处假设传进SecurityCheck里面的IP已经被存进RequestDAO里面了
    public int securityCheckApplyCode(String phoneNumber, String ip, String deviceID) {

        Date timeNow = new Date();

        RequestInfo nowReq = requestInfo.findByIp(ip);
        if (nowReq == null) {
            return 0;
        }
        if (nowReq.getTimes() > 3) {
            return 3;
            //尝试次数>3，拒绝访问
        } else if (nowReq.getTimes() >= 1 && timeNow.getTime() - nowReq.getPrevious10Time().getTime() <= 1000 * 60 * 5) {
            return 2;
            //曾经有过尝试并且距离上一次请求不超过5分钟，返回尝试频率太高
        } else {
            return 0;
        }
    }

    public int securityCheckRegister(String phoneNumber, String ip, String deviceID) {

        Date timeNow = new Date();
        RequestInfo nowReq = requestInfo.findByIp(ip);
        if (nowReq == null) {
            return 0;
        }
        if (nowReq.getTimes() > 3) {
            return 4;
        } else if (nowReq.getTimes() >= 1 && timeNow.getTime() - nowReq.getPrevious10Time().getTime() <= 1000 * 60 * 5) {
            return 2;
        } else {
            return 1;
        }
    }

    public int securityCheckLogin(String ip, String deviceID) {
        Date timeNow = new Date();
        RequestInfo nowReq = requestInfo.findByIp(ip);
        if (nowReq == null) {
            return 0;
        }
        if (nowReq.getTimes() > 3) {
            return 3;
        } else if (nowReq.getTimes() >= 1 && timeNow.getTime() - nowReq.getPrevious10Time().getTime() <= 1000 * 60 * 5) {
            return 2;
        } else if (nowReq.getTimes() >= 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
