package com.example.zdnl7.Service;

import com.example.zdnl7.Dao.UserInfoImpl;
import com.example.zdnl7.Entity.UserInfo;
import com.example.zdnl7.Model.LogoutResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogoutService {
    @Resource
    UserInfoImpl userInfo;

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
