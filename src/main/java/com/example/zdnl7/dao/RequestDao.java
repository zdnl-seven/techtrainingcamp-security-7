package com.example.zdnl7.dao;

import com.example.zdnl7.entity.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestDao extends JpaRepository<RequestInfo,Long> {
}
