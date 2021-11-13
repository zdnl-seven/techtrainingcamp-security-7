//package com.example.zdnl7.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.zdnl7.config.system.zhenziSMS;
//import com.example.zdnl7.utils.CommonUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController(value = "/sms")
//public class SmsController {
//
//
//    @Autowired
//    private zhenziSMS sms;
//
//    /**
//     * @date: 2021/11/13 4:03 下午
//     * @author: yanzx
//     * @description: 查看还能发多少条信息
//     */
//    @RequestMapping("/number")
//    public JSONObject sms() throws Exception {
//        Integer num = sms.checkBalance();
//        JSONObject info = new JSONObject();
//        info.put("message_number", num);
//        return CommonUtil.successJson(info);
//    }
//
//    @PostMapping("/code")
//    public JSONObject code(@RequestBody JSONObject requestJson) {
//        CommonUtil.hasAllRequired(requestJson, "phone_number,password");
//        sms.sendMessage()
//    }
//
//}
