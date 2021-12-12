package com.gangbb.common.annotation;

import com.gangbb.common.enums.BusinessType;
import com.gangbb.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @author : Gangbb
 * @ClassName : Log
 * @Description : 自定义操作日志记录注解
 * @Date : 2021/3/7 12:46
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
