package com.gangbb.core.model.entity;

import java.util.Date;

/**
 * 岗位信息表 sys_post表对应实体类
 *
 * @author Gangbb
 * @since 2021-05-27
 */
 
public class SysPost extends BaseEntity {
	/** 岗位ID */
	private Long id;
	/** 岗位编码 */
	private String postCode;
	/** 岗位名称 */
	private String postName;
	/** 显示顺序 */
	private Integer postSort;
	/** 状态（0正常 1停用） */
	private String status;
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
	
		public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getPostCode() {
		return this.postCode;
	}
	
		public void setPostName(String postName) {
		this.postName = postName;
	}
	
	public String getPostName() {
		return this.postName;
	}
	
		public void setPostSort(Integer postSort) {
		this.postSort = postSort;
	}
	
	public Integer getPostSort() {
		return this.postSort;
	}
	
		public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
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
        SysPost that = (SysPost) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "SysPost{" +
        				"id=" + id +
		        				",postCode='" + postCode + "'" + 
		        				",postName='" + postName + "'" + 
		        				",postSort='" + postSort + "'" + 
		        				",status='" + status + "'" + 
		        				",creator='" + creator + "'" + 
		        				",createTime='" + createTime + "'" + 
		        				",reviser='" + reviser + "'" + 
		        				",updateTime='" + updateTime + "'" + 
		        				",remark='" + remark + "'" + 
		                '}';
    }
	
}