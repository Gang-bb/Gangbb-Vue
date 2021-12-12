package com.gangbb.core.security.handle;

/**
 * @author : Gangbb
 * @ClassName : AuthenticationEntryPointImpl
 * @Description : 认证失败处理类 返回未授权
 * @Date : 2021/3/13 11:00
 */

import com.alibaba.fastjson.JSON;
import com.gangbb.common.model.ApiRestResponse;
import com.gangbb.common.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        // A0005--认证失败，无法访问系统资源
        String code = "A0005";
        ServletUtils.renderString(response, JSON.toJSONString(ApiRestResponse.error(code)));
    }
}
