package com.example.zdnl7.utils;


import com.example.zdnl7.dao.RequestDao;
import com.example.zdnl7.entity.RequestInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class IpInfoModifyUtil {
    @Resource
    RequestDao reqInfo;

    public boolean Modify_Success(String ip) {
        if(reqInfo.existsByIp(ip)==false)return false;
        RequestInfo Now_IP = reqInfo.findByIp(ip);
        Now_IP.setTimes(0l);
        reqInfo.save(Now_IP);
        return true;
    }
    public boolean Modify_Failed(String ip) {
        if(reqInfo.existsByIp(ip)==false)return false;
        RequestInfo Now_IP = reqInfo.findByIp(ip);
        long New_Tried = Now_IP.getTimes()+1l;
        Now_IP.setTimes(New_Tried);
        Now_IP.setPrevious10Time(new Date());
        reqInfo.save(Now_IP);
        return true;
    }
}
