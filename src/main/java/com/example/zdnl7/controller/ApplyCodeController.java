package com.example.zdnl7.controller;

import com.example.zdnl7.model.ApplyCodeResult;
import com.example.zdnl7.service.ApplyCodeService;
import com.example.zdnl7.utils.constants.ConstUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class ApplyCodeController {
    @Resource
    ConstUtil constUtil;

    @Resource
    ApplyCodeService applyCodeService;

    @ResponseBody
    @PostMapping("apply_code")
    public ApplyCodeResult doApplyCode(@RequestBody Map<String, Object> requestParam) {
        ApplyCodeResult applyCodeResult;
        String phoneNumber = (String) requestParam.get(constUtil.PARAM_KEY_PHONE_NUMBER);
        Map<String, String> environment = (Map<String, String>) requestParam.get(constUtil.PARAM_KEY_ENVIRONMENT);
        String ip = environment.get(constUtil.PARAM_KEY_IP);
        String deviceID = environment.get(constUtil.PARAM_KEY_DEVICE_ID);
        applyCodeResult = applyCodeService.doApplyCode(phoneNumber, ip, deviceID);
        return applyCodeResult;
    }
}
