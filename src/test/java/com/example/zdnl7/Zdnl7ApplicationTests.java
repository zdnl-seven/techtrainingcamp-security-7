package com.example.zdnl7;

import com.example.zdnl7.dao.UserDao;
import com.example.zdnl7.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

@SpringBootTest
class Zdnl7ApplicationTests {
//    @Autowired
//    RequestInfo requestInfo;
//
//    @Autowired
//    UserInfo userInfo;
//
//    @Autowired
//    VerifyCodeInfo verifyCodeInfo;
//
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserDao userDao;


    @Test
    void generateTables() {

    }

    @Test
    void testRedisConnect() {
        redisTemplate.opsForValue().set("myKey", "myValue");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

//    @Test
//    void testJpaFindUserById(){
//        /**
//         * @description: 测试jpa的使用
//         * @return: void
//         * @author yanzx
//         * @date: 2021/11/6 6:41 下午
//         */
//        Optional<UserInfo> users = userDao.findById(3L); // UserInfo(id=1, name=闫振兴, password=209243, phone=13583060997, createdAt=2021-11-06 18:38:34.0, sessionID=aaaadafafdafaf, latestIp=127.0.0.1, latestDevice=ddada)
//        System.out.println(users);
//
//
//    }




}
