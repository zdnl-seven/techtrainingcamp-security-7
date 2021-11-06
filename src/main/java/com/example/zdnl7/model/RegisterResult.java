package com.example.zdnl7.model;

import lombok.Data;

@Data
public class RegisterResult {
    int Code;
    String message;
    String sessionID;
    QueryData data;
}
