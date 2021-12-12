package com.gangbb.core.model.entity;

/**
 * 部门表 sys_dept表对应实体类
 *
 * @author Gangbb
 * @since 2021-05-27
 */
 
public class SysDept extends BaseEntity {
	/** 部门id */
	private Long id;
	/** 父部门id */
	private Long parentId;
	/** 祖级列表 */
	private String ancestors;
	/** 部门名称 */
	private String deptName;
	/** 显示顺序 */
	private Integer orderNum;
	/** 负责人 */
	private String leader;
	/** 联系电话 */
	private String phone;
	/** 邮箱 */
	private String email;
	/** 部门状态（0正常 1停用） */
	private String status;
	
		public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
		public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Long getParentId() {
		return this.parentId;
	}
	
		public void setAncestors(String ancestors) {
		this.ancestors = ancestors;
	}
	
	public String getAncestors() {
		return this.ancestors;
	}
	
		public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getDeptName() {
		return this.deptName;
	}
	
		public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public Integer getOrderNum() {
		return this.orderNum;
	}
	
		public void setLeader(String leader) {
		this.leader = leader;
	}
	
	public String getLeader() {
		return this.leader;
	}
	
		public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
		public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
		public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
			
	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        SysDept that = (SysDept) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "SysDept{" +
        				"id=" + id +
		        				",parentId='" + parentId + "'" + 
		        				",ancestors='" + ancestors + "'" + 
		        				",deptName='" + deptName + "'" + 
		        				",orderNum='" + orderNum + "'" + 
		        				",leader='" + leader + "'" + 
		        				",phone='" + phone + "'" + 
		        				",email='" + email + "'" + 
		        				",status='" + status + "'" + 
		                                    '}';
    }
	
}