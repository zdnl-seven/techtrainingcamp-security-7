package com.example.zdnl7.Dao;

import com.example.zdnl7.Entity.VerifyCodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyCodeInfoImpl extends JpaRepository<VerifyCodeInfo,Long> {
    VerifyCodeInfo findByIpAndDeviceID(String ip,String deviceID);
}
