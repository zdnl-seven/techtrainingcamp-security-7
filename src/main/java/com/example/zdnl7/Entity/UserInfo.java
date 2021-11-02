package com.example.zdnl7.Entity;

import lombok.Data;
import org.springframework.stereotype.Controller;

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
    String email;
    String phone;
    int age;
    Date birthday;
    Date createdAt;
    String latestIp;
    String latestDevice;
    boolean isDeleted;
}
