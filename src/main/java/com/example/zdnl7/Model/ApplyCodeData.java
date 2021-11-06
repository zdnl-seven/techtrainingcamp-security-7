package com.example.zdnl7.Model;

import lombok.Data;

import java.util.Date;

@Data
public class ApplyCodeData {
    String verifyCode;
    Date expireTime;
    int decisionType;
}
