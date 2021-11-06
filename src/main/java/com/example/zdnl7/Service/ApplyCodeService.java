package com.example.zdnl7.Service;

import com.example.zdnl7.Dao.VerifyCodeInfoImpl;
import com.example.zdnl7.Entity.VerifyCodeInfo;
import com.example.zdnl7.Model.ApplyCodeData;
import com.example.zdnl7.Model.ApplyCodeResult;
import com.example.zdnl7.Util.RandomUtil;
import com.example.zdnl7.Util.SecurityCheckUtil;
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
    VerifyCodeInfoImpl verifyCodeInfo;

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