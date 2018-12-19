package com.example.springboot_traing.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Author: Think
 * @Date: 2018/12/19
 * @Time: 18:12
 */
@Entity
public class Pretty extends BaseEntity{
    private int id;
    private String image;
    private byte valid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "valid")
    public byte getValid() {
        return valid;
    }

    public void setValid(byte valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pretty pretty = (Pretty) o;

        if (id != pretty.id) return false;
        if (valid != pretty.valid) return false;
        return image != null ? image.equals(pretty.image) : pretty.image == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (int) valid;
        return result;
    }
}
