package com.example.zdnl7.Dao;

import com.example.zdnl7.Entity.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestsImpl extends JpaRepository<RequestInfo,Long> {
}
