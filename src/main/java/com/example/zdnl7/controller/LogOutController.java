package com.example.zdnl7.controller;

import com.example.zdnl7.model.LogoutResult;
import com.example.zdnl7.service.LogoutService;
import com.example.zdnl7.utils.ApiAdapter;
import com.example.zdnl7.utils.constants.ConstUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@CrossOrigin(origins = {"http://127.0.0.1:8080", "http://localhost:8080"}, allowCredentials = "true", allowedHeaders = "*")
public class LogOutController {
//    @Resource
    ConstUtil constUtil;

    @Resource
    LogoutService logoutService;

    @ResponseBody
    @GetMapping("logout")
    public LogoutResult doLogout(@RequestBody Map<String, Object> requestParam, HttpServletRequest servletRequest) {
        ApiAdapter.adapt(requestParam, servletRequest);
        LogoutResult result;
        String sessionID = (String) requestParam.get(constUtil.PARAM_KEY_SESSION_ID);
        int actionType = (Integer) requestParam.get(constUtil.PARAM_KEY_ACTION_TYPE);
        Map<String,String> environment = (Map<String,String>) requestParam.get(constUtil.PARAM_KEY_ENVIRONMENT);
        String ip = environment.get(constUtil.PARAM_KEY_IP);
        String deviceID = environment.get(constUtil.PARAM_KEY_DEVICE_ID);

        result = logoutService.doLogout(sessionID,actionType,ip,deviceID);
        return result;
    }
}
