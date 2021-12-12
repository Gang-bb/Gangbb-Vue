package com.gangbb.core.model.entity;

import java.util.Date;

/**
 * 菜单权限表 sys_menu表对应实体类
 *
 * @author Gangbb
 * @since 2021-05-27
 */
 
public class SysMenu extends BaseEntity {
	/** 菜单ID */
	private Long id;
	/** 菜单名称 */
	private String menuName;
	/** 父菜单ID */
	private Long parentId;
	/** 显示顺序 */
	private Integer orderNum;
	/** 路由地址 */
	private String path;
	/** 组件路径 */
	private String component;
	/** 是否为外链（0是 1否） */
	private Integer isFrame;
	/** 是否缓存（0缓存 1不缓存） */
	private Integer isCache;
	/** 菜单类型（M目录 C菜单 F按钮） */
	private String menuType;
	/** 菜单状态（0显示 1隐藏） */
	private String visible;
	/** 菜单状态（0正常 1停用） */
	private String status;
	/** 权限标识 */
	private String perms;
	/** 菜单图标 */
	private String icon;
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
	
		public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public String getMenuName() {
		return this.menuName;
	}
	
		public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Long getParentId() {
		return this.parentId;
	}
	
		public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public Integer getOrderNum() {
		return this.orderNum;
	}
	
		public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
	
		public void setComponent(String component) {
		this.component = component;
	}
	
	public String getComponent() {
		return this.component;
	}
	
		public void setIsFrame(Integer isFrame) {
		this.isFrame = isFrame;
	}
	
	public Integer getIsFrame() {
		return this.isFrame;
	}
	
		public void setIsCache(Integer isCache) {
		this.isCache = isCache;
	}
	
	public Integer getIsCache() {
		return this.isCache;
	}
	
		public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	public String getMenuType() {
		return this.menuType;
	}
	
		public void setVisible(String visible) {
		this.visible = visible;
	}
	
	public String getVisible() {
		return this.visible;
	}
	
		public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
		public void setPerms(String perms) {
		this.perms = perms;
	}
	
	public String getPerms() {
		return this.perms;
	}
	
		public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getIcon() {
		return this.icon;
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
        SysMenu that = (SysMenu) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "SysMenu{" +
        				"id=" + id +
		        				",menuName='" + menuName + "'" + 
		        				",parentId='" + parentId + "'" + 
		        				",orderNum='" + orderNum + "'" + 
		        				",path='" + path + "'" + 
		        				",component='" + component + "'" + 
		        				",isFrame='" + isFrame + "'" + 
		        				",isCache='" + isCache + "'" + 
		        				",menuType='" + menuType + "'" + 
		        				",visible='" + visible + "'" + 
		        				",status='" + status + "'" + 
		        				",perms='" + perms + "'" + 
		        				",icon='" + icon + "'" + 
		        				",creator='" + creator + "'" + 
		        				",createTime='" + createTime + "'" + 
		        				",reviser='" + reviser + "'" + 
		        				",updateTime='" + updateTime + "'" + 
		        				",remark='" + remark + "'" + 
		                '}';
    }
	
}