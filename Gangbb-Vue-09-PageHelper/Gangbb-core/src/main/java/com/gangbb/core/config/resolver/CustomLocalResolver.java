package com.gangbb.core.config.resolver;

import com.gangbb.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author : Gangbb
 * @ClassName : CustomLocalResolver
 * @Description :
 * @Date : 2021/3/9 20:20
 */
public class CustomLocalResolver implements LocaleResolver {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Nullable
    private Locale defaultLocale;

    public void setDefaultLocale(@Nullable Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    @Nullable
    public Locale getDefaultLocale() {
        return this.defaultLocale;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取application.properties默认的配置
        Locale defaultLocale = this.getDefaultLocale();
        //http请求头没获取到Accept-Language才采用默认配置
        if(defaultLocale != null && request.getHeader("Accept-Language") == null) {
            return defaultLocale;
        } else {
            //request.getHeader("Accept-Language")获取得到的情况
            Locale requestLocale = request.getLocale();
            //获取request.getHeader("Accept-Language")的值
            //从URL获取的locale值
            String localeFlag = request.getParameter("locale");
            logger.info("localeFlag:{}",localeFlag);
            //url链接有传locale参数的情况，eg:zh_CN
            if (!StringUtils.isEmpty(localeFlag)) {
                String[] split = localeFlag.split("_");
                requestLocale = new Locale(split[0], split[1]);
            }
            //没传的情况，默认返回request.getHeader("Accept-Language")的值
            return requestLocale;
        }
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
