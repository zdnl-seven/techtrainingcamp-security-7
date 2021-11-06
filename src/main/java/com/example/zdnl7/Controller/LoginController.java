package com.example.zdnl7.Controller;

import com.example.zdnl7.Model.LoginResult;
import com.example.zdnl7.Service.LoginService;
import com.example.zdnl7.Util.ConstUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class LoginController {
    @Resource
    ConstUtil constUtil;

    @Resource
    LoginService loginService;

    @ResponseBody
    @GetMapping("login_username")
    public LoginResult doLoginByUserName(@RequestBody Map<String, Object> requestParam) {
        LoginResult result = new LoginResult();
        String username = (String) requestParam.get(constUtil.PARAM_KEY_USER_NAME);
        String password = (String) requestParam.get(constUtil.PARAM_KEY_PASSWORD);
        Map<String,String> environment = (Map<String, String>) requestParam.get(constUtil.PARAM_KEY_ENVIRONMENT);
        String ip = environment.get(constUtil.PARAM_KEY_IP);
        String deviceID = environment.get(constUtil.PARAM_KEY_DEVICE_ID);

        result = loginService.doLoginByUserName(username,password,ip,deviceID);
        return result;
    }

    @ResponseBody
    @GetMapping("login_phone")
    public LoginResult doLoginByPhone(@RequestBody Map<String, Object> requestParam) {
        LoginResult result = new LoginResult();
        String phoneNumber = (String) requestParam.get(constUtil.PARAM_KEY_PHONE_NUMBER);
        String verifyCode = (String) requestParam.get(constUtil.PARAM_KEY_VERIFY_CODE);
        Map<String,String> environment = (Map<String, String>) requestParam.get(constUtil.PARAM_KEY_ENVIRONMENT);
        String ip = environment.get(constUtil.PARAM_KEY_IP);
        String deviceID = environment.get(constUtil.PARAM_KEY_DEVICE_ID);

        result = loginService.doLoginByPhone(phoneNumber,verifyCode,ip,deviceID);
        return result;
    }
}
