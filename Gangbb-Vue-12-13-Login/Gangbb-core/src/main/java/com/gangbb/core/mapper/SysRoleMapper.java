package com.gangbb.core.mapper;

import com.gangbb.core.model.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SysRoleMapper
 *
 * @author Gangbb
 * @since 2021-05-27
 */

@Mapper
public interface SysRoleMapper {

	/**
	 * 查询所有记录
	 *
	 * @return 返回集合，没有返回空List
	 */
	List<SysRole> listAll();


	/**
	 * 根据主键查询
	 *
	 * @param id 主键
	 * @return 返回记录，没有返回null
	 */
	SysRole getById(Long id);

	/**
	 * 新增，插入所有字段
	 *
	 * @param sysRole 新增的记录
	 * @return 返回影响行数
	 */
	int insert(SysRole sysRole);

	/**
	 * 新增，忽略null字段
	 *
	 * @param sysRole 新增的记录
	 * @return 返回影响行数
	 */
	int insertIgnoreNull(SysRole sysRole);

	/**
	 * 修改，修改所有字段
	 *
	 * @param sysRole 修改的记录
	 * @return 返回影响行数
	 */
	int update(SysRole sysRole);

	/**
	 * 修改，忽略null字段
	 *
	 * @param sysRole 修改的记录
	 * @return 返回影响行数
	 */
	int updateIgnoreNull(SysRole sysRole);

	/**
	 * 删除记录
	 *
	 * @param sysRole 待删除的记录
	 * @return 返回影响行数
	 */
	int delete(SysRole sysRole);

	/**
	 * 根据用户ID查询角色
	 *
	 * @param userId 用户ID
	 * @return 角色列表
	 */
	public List<SysRole> selectRolePermissionByUserId(Long userId);

}