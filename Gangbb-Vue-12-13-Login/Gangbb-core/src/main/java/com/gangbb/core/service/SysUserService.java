package com.gangbb.core.service;

import com.gangbb.core.model.entity.SysUser;
import com.gangbb.core.model.request.AddUserRequest;

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

    /**
     * @Author liangyixiang
     * @Description 新增用户
     * @Date 2021/7/12
     * @param addUserRequest: 新增用户-请求参数
     * @return: java.lang.Integer
     **/
    int addUser(AddUserRequest addUserRequest);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public String checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public String checkEmailUnique(SysUser user);
}
