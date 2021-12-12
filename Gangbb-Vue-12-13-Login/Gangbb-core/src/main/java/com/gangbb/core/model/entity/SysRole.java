package com.gangbb.core.model.entity;

import java.util.Date;

/**
 * 角色信息表 sys_role表对应实体类
 *
 * @author Gangbb
 * @since 2021-05-27
 */
 
public class SysRole {
	/** 角色ID */
	private Long id;
	/** 角色名称 */
	private String roleName;
	/** 角色权限字符串 */
	private String roleKey;
	/** 显示顺序 */
	private Integer roleSort;
	/** 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限） */
	private String dataScope;
	/** 菜单树选择项是否关联显示 */
	private Byte menuCheckStrictly;
	/** 部门树选择项是否关联显示 */
	private Byte deptCheckStrictly;
	/** 角色状态（0正常 1停用） */
	private String status;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;
	/** 创建者 */
	private String creator;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String reviser;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
	
	public String getRoleKey() {
		return this.roleKey;
	}
	
	public void setRoleSort(Integer roleSort) {
		this.roleSort = roleSort;
	}
	
	public Integer getRoleSort() {
		return this.roleSort;
	}
	
	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}
	
	public String getDataScope() {
		return this.dataScope;
	}
	
	public void setMenuCheckStrictly(Byte menuCheckStrictly) {
		this.menuCheckStrictly = menuCheckStrictly;
	}
	
	public Byte getMenuCheckStrictly() {
		return this.menuCheckStrictly;
	}
	
	public void setDeptCheckStrictly(Byte deptCheckStrictly) {
		this.deptCheckStrictly = deptCheckStrictly;
	}
	
	public Byte getDeptCheckStrictly() {
		return this.deptCheckStrictly;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public String getDelFlag() {
		return this.delFlag;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getCreator() {
		return this.creator;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setReviser(String reviser) {
		this.reviser = reviser;
	}
	
	public String getReviser() {
		return this.reviser;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return this.remark;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        SysRole that = (SysRole) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "SysRole{" +
				"id=" + id +
						",roleName='" + roleName + "'" + 
						",roleKey='" + roleKey + "'" + 
						",roleSort='" + roleSort + "'" + 
						",dataScope='" + dataScope + "'" + 
						",menuCheckStrictly='" + menuCheckStrictly + "'" + 
						",deptCheckStrictly='" + deptCheckStrictly + "'" + 
						",status='" + status + "'" + 
						",delFlag='" + delFlag + "'" + 
						",creator='" + creator + "'" + 
						",createTime='" + createTime + "'" + 
						",reviser='" + reviser + "'" + 
						",updateTime='" + updateTime + "'" + 
						",remark='" + remark + "'" + 
		                '}';
    }
	
}