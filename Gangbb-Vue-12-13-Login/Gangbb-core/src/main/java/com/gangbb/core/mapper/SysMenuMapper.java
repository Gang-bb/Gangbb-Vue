package com.gangbb.core.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * SysMenuMapper
 *
 * @author Gangbb
 * @since 2021-05-27
 */

@Mapper
public interface SysMenuMapper {

	/**
	 * 根据用户ID查询菜单权限
	 *
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	public List<String> selectMenuPermsByUserId(Long userId);


	
}