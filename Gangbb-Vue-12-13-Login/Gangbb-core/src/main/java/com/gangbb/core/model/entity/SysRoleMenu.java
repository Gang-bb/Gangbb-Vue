package com.gangbb.core.model.entity;


/**
 * 角色和菜单关联表 sys_role_menu表对应实体类
 *
 * @author Gangbb
 * @since 2021-05-27
 */
 
public class SysRoleMenu extends BaseEntity {
	/** 角色ID */
	private Long roleId;
	/** 菜单ID */
	private Long menuId;

		public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}
	
		public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	public Long getMenuId() {
		return this.menuId;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        SysRoleMenu that = (SysRoleMenu) o;
        return roleId.equals(that.roleId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(roleId);
    }
    
    @Override
    public String toString() {
        return "SysRoleMenu{" +
        				"roleId=" + roleId +
		        				",menuId='" + menuId + "'" + 
		                '}';
    }
	
}