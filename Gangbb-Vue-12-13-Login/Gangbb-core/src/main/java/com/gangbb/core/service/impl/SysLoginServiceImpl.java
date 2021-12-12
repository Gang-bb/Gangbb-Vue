package com.gangbb.core.service.impl;

import com.gangbb.common.constant.Constants;
import com.gangbb.core.model.dto.SysUserDTO;
import com.gangbb.common.exception.ApiException;
import com.gangbb.common.utils.MessageUtils;
import com.gangbb.common.utils.redis.RedisUtil;
import com.gangbb.core.manager.AsyncManager;
import com.gangbb.core.manager.factory.AsyncFactory;
import com.gangbb.core.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author Gangbb
 * @Description 登录校验方法
 * @Date 2021/7/11
 **/
@Component
public class SysLoginServiceImpl {

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = (String) redisUtil.get(verifyKey);
        redisUtil.del(verifyKey);
        //验证码判空
        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.getMessageByCode("A0006-1")));
            // 验证码失效
            throw new ApiException("A0006-1");
        }
        //验证码验证匹配
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.getMessageByCode("A0006-2")));
            // 验证码输入错误
            throw new ApiException("A0006-2");
        }
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.getMessageByCode("A0008")));
                // 用户不存在/密码错误
                throw new ApiException("A0006-3");
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ApiException("A0009", e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_SUCCESS, MessageUtils.getMessageByCode("D0001")));
        SysUserDTO sysUserDTO = (SysUserDTO) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(sysUserDTO);
    }
}
