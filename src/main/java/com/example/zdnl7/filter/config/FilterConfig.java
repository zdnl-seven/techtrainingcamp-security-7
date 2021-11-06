package com.example.zdnl7.filter.config;

import com.example.zdnl7.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
    @Resource
    LoginFilter loginFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**","/apply_code/**","register/**","/index.html");
    }
}
