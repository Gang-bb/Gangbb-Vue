package com.gangbb.core.security.handle;

import com.alibaba.fastjson.JSON;
import com.gangbb.common.constant.Constants;
import com.gangbb.common.constant.HttpStatus;
import com.gangbb.common.model.ApiRestResponse;
import com.gangbb.common.utils.ServletUtils;
import com.gangbb.common.utils.StringUtils;
import com.gangbb.core.manager.AsyncManager;
import com.gangbb.core.manager.factory.AsyncFactory;
import com.gangbb.core.model.dto.SysUserDTO;
import com.gangbb.core.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Gangbb
 * @Description 自定义退出处理类 返回成功
 * @Date 2021/7/11
 **/
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        SysUserDTO loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(ApiRestResponse.error(String.valueOf(HttpStatus.SUCCESS), "退出成功")));
    }
}
