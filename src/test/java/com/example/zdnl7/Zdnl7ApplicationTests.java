package com.example.zdnl7;

import com.example.zdnl7.entity.RequestInfo;
import com.example.zdnl7.entity.UserInfo;
import com.example.zdnl7.entity.VerifyCodeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Zdnl7ApplicationTests {
    @Autowired
    RequestInfo requestInfo;

    @Autowired
    UserInfo userInfo;

    @Autowired
    VerifyCodeInfo verifyCodeInfo;



    @Test
    void generateTables(){

    }

}
