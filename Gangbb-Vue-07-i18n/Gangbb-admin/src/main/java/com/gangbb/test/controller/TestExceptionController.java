package com.gangbb.test.controller;

import com.gangbb.test.other.TestI18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author : Gangbb
 * @ClassName : TestExceptionController
 * @Description :
 * @Date : 2021/3/9 14:18
 */
@RestController
@RequestMapping("/test/exception")
public class TestExceptionController {
    @Autowired
    MessageSource messageSource;

    /**
     * 测试i8n国际化
     * 传入参数lang 决定中英文环境
     * @return
     */
    @GetMapping("/i8n")
    public String testI18n(){
        //直接获取i18n数据
        //return messageSource.getMessage("exception.default", null, LocaleContextHolder.getLocale());

        //通过MessageUtils获取i18n数据
        //return TestI18n.getMessage();

        // 控制为中文数据
        Locale locale = new Locale("zh", "CN");
        return messageSource.getMessage("exception.default", null, locale);

    }
}
