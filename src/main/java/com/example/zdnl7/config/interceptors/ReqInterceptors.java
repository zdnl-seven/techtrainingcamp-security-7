package com.example.zdnl7.config.interceptors;

import com.example.zdnl7.dao.RequestDao;
import com.example.zdnl7.entity.RequestInfo;
import com.example.zdnl7.utils.constants.ConstUtil;
import org.checkerframework.common.aliasing.qual.LeakedToResult;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class ReqInterceptors implements HandlerInterceptor {

    @Resource
    RequestDao reqInfo;

    @Resource
    ConstUtil constUtil;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response){
        if(reqInfo.existsByIp(request.getParameter(constUtil.PARAM_KEY_IP))==false) {

            reqInfo.save(new RequestInfo(
                    Long.parseLong(request.getParameter(constUtil.PARAM_KEY_SESSION_ID)),
                    new Date().getTime(),
                    request.getParameter(constUtil.PARAM_KEY_IP),
                    request.getParameter(constUtil.PARAM_KEY_DEVICE_ID),
                    new Date()
            ));
        }

        return true;
    }
}
