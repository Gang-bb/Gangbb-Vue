package com.gangbb.common.utils;


import com.gangbb.common.exception.ApiException;
import com.gangbb.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author : Gangbb
 * @ClassName : MessageUtils
 * @Description : 获取i18n资源文件
 * @Date : 2021/3/9 15:36
 */


public class MessageUtils
{
    private static final Logger log = LoggerFactory.getLogger(MessageUtils.class);
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

    public static String getMessageByCode(String code)
    {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        String key = "error.code-message[" + code + "]";
        String message = "";
        try {
            message =  messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            log.warn("没有[" + code + "]该错误码");
            throw new ApiException("B0001");
        }
        return message;
    }
}

