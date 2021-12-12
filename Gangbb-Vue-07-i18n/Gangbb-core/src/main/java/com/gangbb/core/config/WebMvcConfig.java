package com.gangbb.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author : Gangbb
 * @ClassName : WebMvcConfig
 * @Description : 自定义WebMvcConfigurer类来扩展springmvc的功能
 * @Date : 2021/3/9 15:22
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册Locale拦截器(国际化)
        registry.addInterceptor(getLocaleChangeInterceptor());
    }

    /**
     * Locale拦截器(国际化)
     * @return
     */
    public LocaleChangeInterceptor getLocaleChangeInterceptor(){
        // 定义一个拦截器
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        // 拦截请求中 key 为 lang 的参数
        interceptor.setParamName("lang");

        return interceptor;
    }
    /**
     * 默认解析器 其中locale表示默认语言
     */
    @Bean
    LocaleResolver localeResolver() {
        //替换掉默认的 AcceptHeaderLocaleResolver
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        //设置默认语言为简体中文
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }
}
