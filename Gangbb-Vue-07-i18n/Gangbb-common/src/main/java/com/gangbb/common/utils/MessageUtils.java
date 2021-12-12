package com.gangbb.common.utils;


import com.gangbb.common.spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author : Gangbb
 * @ClassName : MessageUtils
 * @Description : 获取i18n资源文件
 * @Date : 2021/3/9 15:36
 */


public class MessageUtils
{
    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     * @param key 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String getMessage(String key, Object... args)
    {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}

