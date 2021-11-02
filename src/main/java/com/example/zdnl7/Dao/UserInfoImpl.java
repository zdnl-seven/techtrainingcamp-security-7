package com.example.zdnl7.Dao;

import com.example.zdnl7.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoImpl extends JpaRepository<UserInfo,Long> {
}
