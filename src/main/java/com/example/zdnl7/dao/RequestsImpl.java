package com.example.zdnl7.dao;

import com.example.zdnl7.entity.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestsImpl extends JpaRepository<RequestInfo,Long> {
}
