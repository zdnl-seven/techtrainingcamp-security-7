package com.example.zdnl7.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zdnl7.config.system.zhenziSMS;
import com.example.zdnl7.utils.CommonUtil;
import com.example.zdnl7.utils.IpUtil;
import com.example.zdnl7.utils.constants.ErrorEnum;
import javafx.scene.paint.PhongMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms")
public class SmsController {


    @Autowired
    private zhenziSMS sms;

    private final Map<String, String> codeMap = new HashMap<>();

    /**
     * @date: 2021/11/13 4:03 下午
     * @author: yanzx
     * @description: 查看还能发多少条信息
     */
    @RequestMapping("/number")
    public JSONObject sms() throws Exception {
        Integer num = sms.checkBalance();
        JSONObject info = new JSONObject();
        info.put("message_number", num);
        return CommonUtil.successJson(info);
    }

    @PostMapping("/code")
    public JSONObject code(@RequestBody JSONObject requestJson, HttpServletRequest request) throws Exception {
        JSONObject info = new JSONObject();
        String ip = IpUtil.getIpAddr(request);
        CommonUtil.hasAllRequired(requestJson, "phone_number");
        String phoneNumber = requestJson.getString("phone_number");
        Map<String, Object> codeInfoMap = sms.sendMessage(phoneNumber, ip);
        if ((boolean) codeInfoMap.get("success")) {
            info.put("state", "发送成功");
            info.put("code", codeInfoMap);
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_40001);
        }
        return CommonUtil.successJson(info);
    }


}
