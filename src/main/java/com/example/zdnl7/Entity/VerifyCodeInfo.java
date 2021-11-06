package com.example.zdnl7.Entity;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Entity
@Data
public class VerifyCodeInfo {
    @Id
    @GeneratedValue
    Long id;
    String verifyCode;
    Date expireTime;
    String ip;
    String deviceID;

    public VerifyCodeInfo(String verifyCode, Date expireTime, String ip, String deviceID) {
        this.verifyCode = verifyCode;
        this.expireTime = expireTime;
        this.ip = ip;
        this.deviceID = deviceID;
    }

    public VerifyCodeInfo() {
    }
}
