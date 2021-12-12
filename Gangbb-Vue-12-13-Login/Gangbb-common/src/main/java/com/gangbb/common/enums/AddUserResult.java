package com.gangbb.common.enums;

/**
 * @Author Gangbb
 * @Description 新增用户结果 枚举
 * @Date 2021/7/12
 **/
public enum AddUserResult {
    /** 1:登录账号已存在 */
    ACCOUNT_ALREADY_EXISTS("1", "登录账号已存在"),
    /** 2:手机号码已存在 */
    PHONE_ALREADY_EXISTS("2", "手机号码已存在"),
    /** 3:邮箱账号已存在 */
    EMAIL_ALREADY_EXISTS("3", "邮箱账号已存在"),
    ;


    private String code;
    private String name;

    private AddUserResult(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getValue(String code) {
        AddUserResult[] enums = values();
        for (AddUserResult item : enums) {
            if (item.code.equals(code)) {
                return item.getName();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
