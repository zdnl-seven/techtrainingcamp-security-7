package com.example.zdnl7.dao;

import com.example.zdnl7.entity.VerifyCodeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyCodeDao extends JpaRepository<VerifyCodeInfo,Long> {
    VerifyCodeInfo findByIpAndDeviceID(String ip,String deviceID);
    boolean existsByIpAndDeviceID(String ip,String deviceID);
}
