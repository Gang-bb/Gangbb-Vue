package com.gangbb.test.model.entity;


import java.io.Serializable;


/**
 * (TestMybatis)实体类
 *
 * @author Gangbb
 * @since 2021-03-07 09:27:12
 */
public class TestMybatis implements Serializable {
    private static final long serialVersionUID = -15945377879783826L;
    /**
    * 主键id
    */
    private Integer id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别
    */
    private String gender;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}