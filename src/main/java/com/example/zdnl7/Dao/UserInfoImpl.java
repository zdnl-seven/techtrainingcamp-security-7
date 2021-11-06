package com.example.zdnl7.Dao;

import com.example.zdnl7.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoImpl extends JpaRepository<UserInfo,Long> {
    boolean existsByName(String name);
    boolean existsByPhone(String phoneNumber);
    UserInfo findByName(String username);
    UserInfo findByPhone(String phoneNumber);
    UserInfo findBySessionIDAndLatestIpAndLatestDevice(String sessionID,String ip,String deviceID);
}
