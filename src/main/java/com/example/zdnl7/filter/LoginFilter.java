package com.example.zdnl7.filter;

import com.example.zdnl7.dao.UserInfoImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//filter学完没用过，忘光了QAQ
@Component
public class LoginFilter implements HandlerInterceptor {
    @Resource
    UserInfoImpl userInfo;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        if (false) {
            return false;
        } else {
            return true;
        }
    }
}
