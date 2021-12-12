package com.gangbb.common.exception;

import com.gangbb.common.utils.MessageUtils;

/**
 * @author : Gangbb
 * @ClassName : ApiException
 * @Description : 自定义异常
 * @Date : 2021/3/9 19:47
 */



public class ApiException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String code;


    /**
     * 错误消息
     */
    private String message;



    public ApiException(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public ApiException(String code)
    {
        this(code, MessageUtils.getMessageByCode(code));
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

