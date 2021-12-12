package com.gangbb.core.security.filter;

import com.gangbb.common.utils.StringUtils;
import com.gangbb.core.model.dto.SysUserDTO;
import com.gangbb.core.security.service.TokenService;
import com.gangbb.core.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Gangbb
 * @ClassName : JwtAuthenticationTokenFilter
 * @Description :
 * @Date : 2021/5/28 14:19
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        //从token获取LoginUser
        SysUserDTO sysUserDTO = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(sysUserDTO) && StringUtils.isNull(SecurityUtils.getAuthentication()))
        {
            //验证令牌有效期，操作相差不足20分钟，自动刷新缓存
            tokenService.verifyToken(sysUserDTO);
            //在Security上下文中写入用户登录相关所有信息
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUserDTO, null, sysUserDTO.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
