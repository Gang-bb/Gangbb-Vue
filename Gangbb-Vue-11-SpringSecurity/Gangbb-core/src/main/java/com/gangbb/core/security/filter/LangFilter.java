package com.gangbb.core.security.filter;

import com.gangbb.common.utils.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @author : Gangbb
 * @ClassName : LangFilter
 * @Description :
 * @Date : 2021/3/13 12:22
 */
@Component
public class LangFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String localeFlag = httpServletRequest.getParameter("lang");
        Locale locale = new Locale("zh", "CN");
        if (!StringUtils.isEmpty(localeFlag)) {
            String[] split = localeFlag.split("_");
            locale = new Locale(split[0], split[1]);
        }
        LocaleContextHolder.setLocale(locale);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
