package com.gangbb.core.model.entity;


/**
 * 用户和角色关联表 sys_user_role表对应实体类
 *
 * @author Gangbb
 * @since 2021-05-27
 */
 
public class SysUserRole extends BaseEntity {
	/** 用户ID */
	private Long userId;
	/** 角色ID */
	private Long roleId;

		public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
		public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        SysUserRole that = (SysUserRole) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(userId);
    }
    
    @Override
    public String toString() {
        return "SysUserRole{" +
        				"userId=" + userId +
		        				",roleId='" + roleId + "'" + 
		                '}';
    }
	
}