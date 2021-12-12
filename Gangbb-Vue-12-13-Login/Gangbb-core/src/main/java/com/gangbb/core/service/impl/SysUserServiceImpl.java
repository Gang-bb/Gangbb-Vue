package com.gangbb.core.service.impl;

import com.gangbb.common.constant.UserConstants;
import com.gangbb.common.exception.ApiException;
import com.gangbb.common.utils.StringUtils;
import com.gangbb.core.mapper.SysUserMapper;
import com.gangbb.core.model.entity.SysUser;
import com.gangbb.core.model.request.AddUserRequest;
import com.gangbb.core.security.utils.SecurityUtils;
import com.gangbb.core.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : SysUserServiceImpl
 * @Description :
 * @Date : 2021/3/13 14:29
 */
@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserMapper userMapper;



    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return null;
    }

    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    @Override
    public SysUser selectUserById(Long userId) {
        return null;
    }

    /**
     * @param addUserRequest : 新增用户-请求参数
     * @Author liangyixiang
     * @Description 新增用户
     * @Date 2021/7/12
     * @return: java.lang.Integer
     **/
    @Override
    @Transactional
    public int addUser(AddUserRequest addUserRequest) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(addUserRequest, sysUser);
        // 判断登陆账号是否存在
        if (UserConstants.NOT_UNIQUE.equals(this.checkUserNameUnique(addUserRequest.getUserName())))
        {
            throw new ApiException("A0007-1");
        }// 判断手机号是否存在
        else if (StringUtils.isNotEmpty(sysUser.getPhoneNumber())
                && UserConstants.NOT_UNIQUE.equals(this.checkPhoneUnique(sysUser)))
        {
            throw new ApiException("A0007-2");
        } // 判断邮箱是否存在
        else if (StringUtils.isNotEmpty(sysUser.getEmail())
                && UserConstants.NOT_UNIQUE.equals(this.checkEmailUnique(sysUser)))
        {
            throw new ApiException("A0007-3");
        }
        // sysUser.setCreator(SecurityUtils.getUsername());
        sysUser.setCreator("Gangbb");
        sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
        return userMapper.insertUser(sysUser);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName) {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getId()) ? -1L : user.getId();
        SysUser info = userMapper.checkPhoneUnique(user.getPhoneNumber());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getId()) ? -1L : user.getId();
        SysUser info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
