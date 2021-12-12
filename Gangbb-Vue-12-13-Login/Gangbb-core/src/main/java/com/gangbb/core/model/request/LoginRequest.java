package com.gangbb.core.model.request;/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/7/12
 **/

import javax.validation.constraints.NotBlank;

/**
 * @Author Gangbb
 * @Description 登录 请求参数
 * @Date 2021/7/12
 **/
public class LoginRequest {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名 username 不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户密码 password 不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码 code 不能为空")
    private String code;

    /**
     * 唯一标识
     */
    @NotBlank(message = "唯一标识 uuid 不能为空")
    private String uuid = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
