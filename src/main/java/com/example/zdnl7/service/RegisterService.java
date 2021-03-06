package com.example.zdnl7.service;

import com.example.zdnl7.dao.RequestDao;
import com.example.zdnl7.dao.UserDao;
import com.example.zdnl7.dao.VerifyCodeDao;
import com.example.zdnl7.entity.UserInfo;
import com.example.zdnl7.model.QueryData;
import com.example.zdnl7.model.RegisterResult;
import com.example.zdnl7.utils.CreateRequestInfoUtil;
import com.example.zdnl7.utils.RandomUtil;
import com.example.zdnl7.utils.SecurityCheckUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RegisterService {
    @Resource
    SecurityCheckUtil securityCheckUtil;

    @Resource
    UserDao userInfo;

    @Resource
    VerifyCodeDao verifyCodeInfo;

    @Resource
    RandomUtil randomUtil;

    @Resource
    RequestDao reqInfo;

    @Resource
    CreateRequestInfoUtil createRequestInfoUtil;

    public RegisterResult doRegister(String username, String password, String phoneNumber, String verifyCode, String ip, String deviceID) {


        if(reqInfo.existsByIp(ip)==false)createRequestInfoUtil.Create(ip,deviceID);

        RegisterResult result = new RegisterResult();
        QueryData data = new QueryData();
        int decisionType = securityCheckUtil.securityCheckRegister(phoneNumber, ip, deviceID);
        data.setDecisionType(decisionType);

        if (decisionType >= 4) {
            result.setMessage("安全验证不通过");
        } else if (decisionType == 2) {
            result.setMessage("操作过于频繁，请稍后再试");
        } else {
            if (userInfo.existsByName(username)) {
                result.setMessage("用户名已存在");
            } else if (userInfo.existsByPhone(phoneNumber)) {
                result.setMessage("该手机已注册");
            } else if (verifyCodeInfo.findByIpAndDeviceID(ip, deviceID) == null ||
                    !verifyCodeInfo.findByIpAndDeviceID(ip, deviceID).getVerifyCode().equals(verifyCode)) {
                result.setMessage("验证码错误");
            } else {
                result.setCode(1);
                result.setMessage("注册成功");

                String sessionID = randomUtil.creatSessionID();
                result.setSessionID(sessionID);
                data.setSessionId(sessionID);

                Date timeNow = new Date();
                Date expireTime = new Date(timeNow.getTime() + 15 * 24 * 60 * 60 * 1000);
                data.setExpireTime(expireTime);

                UserInfo ui = new UserInfo(username, password, phoneNumber, timeNow, sessionID, ip, deviceID, false);
                userInfo.save(ui);
            }
        }
        result.setData(data);
        return result;
    }
}