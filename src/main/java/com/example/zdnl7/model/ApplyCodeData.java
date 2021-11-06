package com.example.zdnl7.model;

import lombok.Data;

import java.util.Date;

@Data
public class ApplyCodeData {
    String verifyCode;
    Date ExpireTime;
    int DecisionType;
}
