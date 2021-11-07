package com.example.zdnl7.config.system;

import com.example.zdnl7.config.interceptors.ReqInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class RequestConfig implements WebMvcConfigurer {

    @Autowired
    private ReqInterceptors reqInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reqInterceptors).addPathPatterns("/**");
    }


}

