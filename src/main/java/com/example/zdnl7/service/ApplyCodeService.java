package com.example.zdnl7.service;

import com.example.zdnl7.dao.VerifyCodeDao;
import com.example.zdnl7.entity.VerifyCodeInfo;
import com.example.zdnl7.model.ApplyCodeData;
import com.example.zdnl7.model.ApplyCodeResult;
import com.example.zdnl7.utils.RandomUtil;
import com.example.zdnl7.utils.SecurityCheckUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ApplyCodeService {
    @Resource
    SecurityCheckUtil securityCheckUtil;

    @Resource
    RandomUtil randomUtil;

    @Resource
    VerifyCodeDao verifyCodeInfo;

    public ApplyCodeResult doApplyCode(String phoneNumber,String ip,String deviceID) {
        int decisionType = securityCheckUtil.securityCheckApplyCode(phoneNumber,ip,deviceID);
        ApplyCodeResult result = new ApplyCodeResult();
        ApplyCodeData applyCodeData = new ApplyCodeData();
        applyCodeData.setDecisionType(decisionType);

        if (decisionType == 3) {
            result.setMessage("安全验证不通过");
        } else if (decisionType == 2) {
            result.setMessage("操作过于频繁，请稍后再试");
        } else {
            result.setCode(1);
            result.setMessage("获取验证码成功");
            String verifyCode = randomUtil.creatVerifyCode();
            Date timeNow = new Date();
            Date expireTime = new Date(timeNow.getTime() + 3*60*1000);

            applyCodeData.setVerifyCode(verifyCode);
            applyCodeData.setExpireTime(expireTime);

            VerifyCodeInfo vc = new VerifyCodeInfo(verifyCode,expireTime,ip,deviceID);
            verifyCodeInfo.save(vc);
        }
        result.setData(applyCodeData);
        return result;
    }
}