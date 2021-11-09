package com.example.zdnl7.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class RequestInfo {
    @Id
    @GeneratedValue
    Long id;
    Long times;
    String ip;
    String device;
    Date previous10Time;

    public RequestInfo(Long id, Long times, String ip, String device, Date previous10Time) {
        this.id = id;
        this.times = times;
        this.ip = ip;
        this.device = device;
        this.previous10Time = previous10Time;
    }

    public RequestInfo() {

    }
}
