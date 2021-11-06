package com.example.zdnl7.service;

import com.example.zdnl7.dao.UserDao;
import com.example.zdnl7.entity.UserInfo;
import com.example.zdnl7.model.LogoutResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogoutService {
    @Resource
    UserDao userInfo;

    public LogoutResult doLogout(String sessionID, int actionType, String ip, String deviceID) {
        LogoutResult result = new LogoutResult();
        UserInfo user = userInfo.findBySessionIDAndLatestIpAndLatestDevice(sessionID,ip,deviceID);
        if (user==null) {
            result.setMessage("操作失败");
        } else {
            result.setCode(1);
            result.setMessage("操作成功");

            user.setSessionID(null);
            if (actionType==1) {
                userInfo.save(user);
            } else {
                userInfo.delete(user);
            }
        }
        return result;
    }
}
