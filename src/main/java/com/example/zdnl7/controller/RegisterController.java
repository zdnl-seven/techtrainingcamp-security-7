package com.example.zdnl7.controller;

import com.example.zdnl7.model.RegisterResult;
import com.example.zdnl7.service.RegisterService;
import com.example.zdnl7.utils.ConstUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class RegisterController {
    @Resource
    ConstUtil constUtil;

    @Resource
    RegisterService registerService;

    @ResponseBody
    @PostMapping("/register")
    public RegisterResult doRegister(@RequestBody Map<String, Object> requestParam) {
        RegisterResult result = new RegisterResult();
        String username = (String) requestParam.get(constUtil.PARAM_KEY_USER_NAME);
        String password = (String) requestParam.get(constUtil.PARAM_KEY_PASSWORD);
        String phoneNumber = (String) requestParam.get(constUtil.PARAM_KEY_PHONE_NUMBER);
        String verifyCode = (String) requestParam.get(constUtil.PARAM_KEY_VERIFY_CODE);
        Map<String,String> environment = (Map<String, String>) requestParam.get(constUtil.PARAM_KEY_ENVIRONMENT);
        String ip = environment.get(constUtil.PARAM_KEY_IP);
        String deviceID = environment.get(constUtil.PARAM_KEY_DEVICE_ID);

        result = registerService.doRegister(username,password,phoneNumber,verifyCode,ip,deviceID);
        return result;
    }
}
