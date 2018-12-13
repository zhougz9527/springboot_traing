package com.example.springboot_traing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * @Author: Think
 * @Date: 2018/12/13
 * @Time: 22:28
 */
@MappedSuperclass
abstract class BaseEntity {

    private Timestamp gtime;
    private Timestamp utime;


    @Basic
    @JsonIgnore
    @Column(name = "gtime")
    public Timestamp getGtime() {
        return gtime;
    }

    public void setGtime(Timestamp gtime) {
        this.gtime = gtime;
    }

    @Basic
    @JsonIgnore
    @Column(name = "utime")
    public Timestamp getUtime() {
        return utime;
    }

    public void setUtime(Timestamp utime) {
        this.utime = utime;
    }

}
