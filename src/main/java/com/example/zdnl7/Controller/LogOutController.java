package com.example.zdnl7.Controller;

import com.example.zdnl7.Model.LogoutResult;
import com.example.zdnl7.Service.LogoutService;
import com.example.zdnl7.Util.ConstUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class LogOutController {
    @Resource
    ConstUtil constUtil;

    @Resource
    LogoutService logoutService;

    @ResponseBody
    @GetMapping("logout")
    public LogoutResult doLogout(@RequestBody Map requestParam) {
        LogoutResult result = new LogoutResult();
        String sessionID = (String) requestParam.get(constUtil.PARAM_KEY_SESSION_ID);
        int actionType = (Integer) requestParam.get(constUtil.PARAM_KEY_ACTION_TYPE);
        Map<String,String> environment = (Map<String,String>) requestParam.get(constUtil.PARAM_KEY_ENVIRONMENT);
        String ip = environment.get(constUtil.PARAM_KEY_IP);
        String deviceID = environment.get(constUtil.PARAM_KEY_DEVICE_ID);

        result = logoutService.doLogout(sessionID,actionType,ip,deviceID);
        return result;
    }
}
