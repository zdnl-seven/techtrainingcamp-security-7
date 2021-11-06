package com.example.zdnl7.model;

import lombok.Data;

import java.util.Date;

@Data
public class QueryData {
    String sessionId;
    Date expireTime;
    int decisionType;
}
