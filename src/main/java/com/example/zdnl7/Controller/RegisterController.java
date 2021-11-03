package com.example.zdnl7.Controller;

import com.example.zdnl7.Model.RegisterResult;
import com.example.zdnl7.Service.RegisterService;
import com.example.zdnl7.Util.ConstUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping(constUtil.REQUEST_PATH_REGISTER)
    public RegisterResult doRegister(@RequestBody Map requestParam) {
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
