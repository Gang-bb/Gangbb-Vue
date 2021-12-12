package com.gangbb.common.enums;

/**
 * @author : Gangbb
 * @ClassName : UserStatus
 * @Description : 用户状态
 * @Date : 2021/5/28 13:37
 */
public enum UserStatus
{
    OK(1, "启用"), DISABLE(2, "停用"), LOCK(3, "锁定");

    private final int code;
    private final String info;

    UserStatus(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}