package com.gangbb.core.model.request;

import javax.validation.constraints.NotBlank;

/**
 * @Author Gangbb
 * @Description 新增用户请求参数
 * @Date 2021/7/12
 **/
public class AddUserRequest {
    /**
     * 用户账号
     */
    @NotBlank(message = "用户账号 userName 不能为空")
    private String userName;

    /**
     * 用户邮箱
     */
    @NotBlank(message = "用户邮箱 email 不能为空")
    private String email;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码 phoneNumber 不能为空")
    private String phoneNumber;

    /**
     * 密码
     */
    @NotBlank(message = "密码 password 不能为空")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
