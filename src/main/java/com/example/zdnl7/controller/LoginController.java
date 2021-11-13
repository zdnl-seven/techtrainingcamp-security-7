package com.example.zdnl7.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.zdnl7.config.exception.CommonJsonException;
import com.example.zdnl7.config.system.zhenziSMS;
import com.example.zdnl7.model.LoginResult;
import com.example.zdnl7.service.LoginService;
import com.example.zdnl7.utils.CommonUtil;
import com.example.zdnl7.utils.IpUtil;
import com.example.zdnl7.utils.RandomUtil;
import com.example.zdnl7.utils.constants.ConstUtil;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController {
    @Resource
    ConstUtil constUtil;

    @Resource
    LoginService loginService;

    @PostMapping("login_by_username")
    public LoginResult doLoginByUserName(@RequestBody(required = false) JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "username,password,environment");
        Map<String, String> environment = (Map<String, String>) requestJson.get("environment");
        String username = requestJson.getString("username");
        String password = requestJson.getString("password");
        String ip = environment.get("ip");
        String deviceId = environment.get("device_id");

        return loginService.doLoginByUserName(username, password, ip, deviceId);
    }

    @PostMapping("login_by_phone")
    public LoginResult doLoginByPhone(@RequestBody JSONObject requestJson) {

        CommonUtil.hasAllRequired(requestJson, "phone_number,verify_code,environment");
        Map<String, String> environment = (Map<String, String>) requestJson.get("environment");
        String phoneNumber = requestJson.getString("phone_number");
        String verifyCode = requestJson.getString("verify_code");
        String ip = environment.get("ip");
        String deviceID = environment.get("device_id");

        return loginService.doLoginByPhone(phoneNumber, verifyCode, ip, deviceID);
    }

    @GetMapping("/test")
    public JSONObject test(HttpServletRequest request) {
        String ip = IpUtil.getIpAddr(request);
        JSONObject info = new JSONObject();
        info.put("ip", ip);
        return CommonUtil.successJson(info);
    }
}
