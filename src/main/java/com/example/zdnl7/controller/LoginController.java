package com.example.zdnl7.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zdnl7.model.LoginResult;
import com.example.zdnl7.service.LoginService;
import com.example.zdnl7.utils.ApiAdapter;
import com.example.zdnl7.utils.CommonUtil;
import com.example.zdnl7.utils.IpUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:8080", "http://localhost:8080"}, allowCredentials = "true", allowedHeaders = "*")
public class LoginController {

    @Resource
    LoginService loginService;

    @PostMapping("login")
    public LoginResult doLogin(@RequestBody JSONObject request, HttpServletRequest servletRequest) {
        ApiAdapter.adapt(request, servletRequest);
        if (request.containsKey("username")) {
            return doLoginByUserName(request);
        } else {
            return doLoginByPhone(request);
        }
    }

    @PostMapping("login_by_name")
    public LoginResult doLoginByUserName(JSONObject request) {
        CommonUtil.hasAllRequired(request, "username,password,ip");
        String username = request.getString("username");
        String password = request.getString("password");
        Map<String,String> environment = (Map<String, String>) request.get("environment");
        String ip = environment.get("ip");
        String deviceID = environment.get("device_id");

        return loginService.doLoginByUserName(username, password, ip, deviceID);
    }

    @PostMapping("login_by_phone")
    public LoginResult doLoginByPhone(@RequestBody JSONObject request) {

        CommonUtil.hasAllRequired(request, "phone_number,verify_code,environment");
        String phoneNumber = request.getString("phone_number");
        String verifyCode = request.getString("verify_code");
        Map<String,String> environment = (Map<String, String>) request.get("environment");
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
