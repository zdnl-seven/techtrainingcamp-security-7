package com.example.zdnl7.model;

import lombok.Data;

@Data
public class RegisterResult {
    int code;
    String message;
    String sessionID;
    QueryData data;
}
