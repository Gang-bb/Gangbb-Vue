package com.gangbb.core.security.service;


import com.gangbb.common.constant.UserConstants;
import com.gangbb.common.enums.UserStatus;
import com.gangbb.common.exception.ApiException;
import com.gangbb.common.utils.StringUtils;
import com.gangbb.core.model.dto.SysUserDTO;
import com.gangbb.core.model.entity.SysUser;
import com.gangbb.core.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : Gangbb
 * @ClassName : UserDetailsService
 * @Description :
 * @Date : 2021/5/27 15:42
 */
@Service("GangbbUserDetailsService")
    public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserConstants.USER_DELETE == user.getDelFlag())
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new ApiException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode() == user.getStatus())
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new ApiException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new SysUserDTO(user, permissionService.getMenuPermission(user));
    }
}

