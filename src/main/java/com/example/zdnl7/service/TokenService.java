package com.example.zdnl7.service;
import com.example.zdnl7.dao.UserDao;
import com.example.zdnl7.entity.UserInfo;
import com.example.zdnl7.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.benmanes.caffeine.cache.Cache;


import java.util.UUID;

@Service
@Slf4j
public class TokenService {

    Cache<String, UserInfo> cacheMap;

    UserDao userDao;

    public TokenService(@Autowired Cache<String, UserInfo> cacheMap, @Autowired UserDao userDao) {
        this.cacheMap = cacheMap;
        this.userDao = userDao;
    }

    /**
     * 用户登录验证通过后(sso/帐密),生成token,记录用户已登录的状态
     */
    public String generateToken(String username) {
        MDC.put("username", username);
        String token = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        //设置用户信息缓存
        setCache(token, username);
        return token;
    }

    public UserInfo getUserInfo() {
        String token = MDC.get("token");
        return getUserInfoFromCache(token);
    }

    /**
     * 根据token查询用户信息
     * 如果token无效,会抛未登录的异常
     */
    private UserInfo getUserInfoFromCache(String token) {
        if (StringTools.isNullOrEmpty(token)) {
            return null;
        }
        log.debug("根据token从缓存中查询用户信息,{}", token);
        UserInfo info = cacheMap.getIfPresent(token);
        if (info == null) {
            log.info("没拿到缓存 token={}", token);
            return null;
        }
        return info;
    }

    private void setCache(String token, String username) {
        UserInfo info = getUserInfoByUsername(username);
        log.info("设置用户信息缓存:token={} , username={}, info={}", token, username, info);
        cacheMap.put(token, info);
    }

    /**
     * 退出登录时,将token置为无效
     */
    public void invalidateToken() {
        String token = MDC.get("token");
        if (!StringTools.isNullOrEmpty(token)) {
            cacheMap.invalidate(token);
        }
        log.debug("退出登录,清除缓存:token={}", token);
    }

    private UserInfo getUserInfoByUsername(String username) {
        return userDao.findByName(username);
    }
}
