package com.example.zdnl7.Model;

import lombok.Data;

@Data
public class LoginResult {
    int code;
    String message;
    QueryData data;
}
