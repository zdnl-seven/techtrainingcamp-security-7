package com.example.zdnl7.config.system;

import com.example.zdnl7.filter.LoginFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("adding cors");
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*", "https://localhost:*",
                        "http://127.0.0.1:*", "https://127.0.0.1:*");
    }
}
