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
}
