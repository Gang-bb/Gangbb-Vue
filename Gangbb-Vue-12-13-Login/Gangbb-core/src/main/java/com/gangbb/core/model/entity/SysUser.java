package com.gangbb.core.model.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户信息表(SysUser)实体类
 *
 * @author Gangbb
 * @since 2021-03-13 14:24:00
 */
public class SysUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4408818320420978827L;

    /**
    * 用户ID
    */
    private Long id;
    /**
    * 用户账号
    */
    private String userName;
    /**
    * 用户昵称
    */
    private String nickName;
    /**
    * 用户类型（00系统用户）
    */
    private String userType;
    /**
    * 用户邮箱
    */
    private String email;
    /**
    * 手机号码
    */
    private String phoneNumber;
    /**
    * 用户性别（0男 1女 2未知）
    */
    private Integer sex;
    /**
    * 头像地址
    */
    private String avatar;
    /**
    * 密码
    */
    private String password;
    /**
    * 用户状态 1-启用 2-禁用 3-锁定
    */
    private Integer status;
    /**
    * 最后登录IP
    */
    private String loginIp;
    /**
    * 最后登录时间
    */
    private Date loginDate;
    /**
    * 错误次数
    */
    private Integer errorCount;

    public boolean isAdmin()
    {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }


}