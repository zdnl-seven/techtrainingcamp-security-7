package com.example.zdnl7;

import com.example.zdnl7.Entity.RequestInfo;
import com.example.zdnl7.Entity.UserInfo;
import com.example.zdnl7.Entity.VerifyCodeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
