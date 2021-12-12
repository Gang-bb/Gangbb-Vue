package com.gangbb.core.model.entity;


/**
 * 角色和部门关联表 sys_role_dept表对应实体类
 *
 * @author Gangbb
 * @since 2021-05-27
 */
 
public class SysRoleDept extends BaseEntity {
	/** 角色ID */
	private Long roleId;
	/** 部门ID */
	private Long deptId;

		public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}
	
		public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
	public Long getDeptId() {
		return this.deptId;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        SysRoleDept that = (SysRoleDept) o;
        return roleId.equals(that.roleId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(roleId);
    }
    
    @Override
    public String toString() {
        return "SysRoleDept{" +
        				"roleId=" + roleId +
		        				",deptId='" + deptId + "'" + 
		                '}';
    }
	
}