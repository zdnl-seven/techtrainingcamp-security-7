package com.example.zdnl7.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class UserInfo {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String password;
    String phone;
    Date createdAt;
    String sessionID;
    String latestIp;
    String latestDevice;

    public UserInfo(String name, String password, String phone, Date createdAt, String sessionID, String latestIp, String latestDevice, boolean isDeleted) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.createdAt = createdAt;
        this.sessionID = sessionID;
        this.latestIp = latestIp;
        this.latestDevice = latestDevice;
    }

    public UserInfo() {
    }
}
