package com.example.zdnl7.Controller;

import com.example.zdnl7.Model.ApplyCodeResult;
import com.example.zdnl7.Service.ApplyCodeService;
import com.example.zdnl7.Util.ConstUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping(constUtil.REQUEST_PATH_APPLY_CODE)
    public ApplyCodeResult doApplyCode(@RequestBody Map requestParam) {
        ApplyCodeResult applyCodeResult = new ApplyCodeResult();
        String phoneNumber = (String) requestParam.get(constUtil.PARAM_KEY_PHONE_NUMBER);
        Map<String,String> environment = (Map<String,String>) requestParam.get(constUtil.PARAM_KEY_ENVIRONMENT);
        String ip = environment.get(constUtil.PARAM_KEY_IP);
        String deviceID = environment.get(constUtil.PARAM_KEY_DEVICE_ID);

        applyCodeResult = applyCodeService.doApplyCode(phoneNumber,ip,deviceID);
        return applyCodeResult;
    }
}
