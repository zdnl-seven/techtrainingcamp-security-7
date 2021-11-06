package com.example.zdnl7.Service;

import com.example.zdnl7.Dao.UserInfoImpl;
import com.example.zdnl7.Dao.VerifyCodeInfoImpl;
import com.example.zdnl7.Entity.UserInfo;
import com.example.zdnl7.Model.LoginResult;
import com.example.zdnl7.Model.QueryData;
import com.example.zdnl7.Util.RandomUtil;
import com.example.zdnl7.Util.SecurityCheckUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class LoginService {
    @Resource
    SecurityCheckUtil securityCheckUtil;

    @Resource
    UserInfoImpl userInfo;

    @Resource
    RandomUtil randomUtil;

    @Resource
    VerifyCodeInfoImpl verifyCodeInfo;

    public LoginResult doLoginByUserName(String username, String password, String ip, String deviceID) {
        LoginResult result = new LoginResult();
        QueryData data = new QueryData();
        int decisionType = securityCheckUtil.securityCheckLogin(ip, deviceID);
        data.setDecisionType(decisionType);

        if (decisionType == 3) {
            result.setMessage("安全验证不通过");
        } else if (decisionType == 2) {
            result.setMessage("操作过于频繁，请稍后再试");
        } else {
            UserInfo user = userInfo.findByName(username);
            if (user==null) {
                result.setMessage("用户不存在");
            } else if (!user.getPassword().equals(password)) {
                result.setMessage("密码错误");
                //TODO:更新该ip/deviceID输入密码错误次数
            } else {
                result.setCode(1);
                result.setMessage("登陆成功");
                String sessionID = randomUtil.creatSessionID();
                data.setSessionId(sessionID);

                Date timeNow = new Date();
                Date expireTime = new Date(timeNow.getTime() + 15*24*60*60*1000);
                data.setExpireTime(expireTime);

                user.setLatestIp(ip);
                user.setLatestDevice(deviceID);
                user.setSessionID(sessionID);
                userInfo.save(user);
            }
        }
        result.setData(data);
        return result;
    }

    public LoginResult doLoginByPhone(String phoneNumber,String verifyCode,String ip,String deviceID) {
        LoginResult result = new LoginResult();
        QueryData data = new QueryData();
        int decisionType = securityCheckUtil.securityCheckLogin(ip, deviceID);
        data.setDecisionType(decisionType);
        if (decisionType == 3) {
            result.setMessage("安全验证不通过");
        } else if (decisionType == 2) {
            result.setMessage("操作过于频繁，请稍后再试");
        } else {
            UserInfo user = userInfo.findByPhone(phoneNumber);
            if (user==null) {
                result.setMessage("手机号未注册");
            } else if (verifyCodeInfo.findByIpAndDeviceID(ip,deviceID)==null|| !verifyCodeInfo.findByIpAndDeviceID(ip, deviceID).getVerifyCode().equals(verifyCode)) {
                result.setMessage("验证码错误");
            } else {
                result.setCode(1);
                result.setMessage("登陆成功");
                String sessionID = randomUtil.creatSessionID();
                data.setSessionId(sessionID);

                Date timeNow = new Date();
                Date expireTime = new Date(timeNow.getTime() + 15*24*60*60*1000);
                data.setExpireTime(expireTime);

                user.setLatestIp(ip);
                user.setLatestDevice(deviceID);
                user.setSessionID(sessionID);
                userInfo.save(user);
            }
        }
        result.setData(data);
        return result;
    }
}
