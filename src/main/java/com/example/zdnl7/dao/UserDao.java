package com.example.zdnl7.dao;

import com.example.zdnl7.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserInfo,Long> {
    boolean existsByName(String name);
    boolean existsByPhone(String phoneNumber);
    UserInfo findByName(String username);
    UserInfo findByPhone(String phoneNumber);
    UserInfo findBySessionIDAndLatestIpAndLatestDevice(String sessionID,String ip,String deviceID);
}
