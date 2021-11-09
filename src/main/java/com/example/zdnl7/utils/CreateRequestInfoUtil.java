package com.example.zdnl7.utils;

import com.example.zdnl7.dao.RequestDao;
import com.example.zdnl7.entity.RequestInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class CreateRequestInfoUtil {


    @Resource
    RequestDao reqInfo;

    Date nowDate = new Date();
    public void Create(String ip,String device_id) {
        reqInfo.save(
                new RequestInfo(
                        nowDate.getTime(),
                        0l,
                        ip,
                        device_id,
                        nowDate
                )
        );
    }
}
