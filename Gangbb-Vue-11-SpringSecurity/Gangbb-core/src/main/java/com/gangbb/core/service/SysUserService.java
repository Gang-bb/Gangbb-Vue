package com.gangbb.core.service;

import com.gangbb.core.model.entity.sys.SysUser;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : SysUserService
 * @Description : 用户 业务层
 * @Date : 2021/3/13 14:27
 */
public interface SysUserService {
    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);

}
