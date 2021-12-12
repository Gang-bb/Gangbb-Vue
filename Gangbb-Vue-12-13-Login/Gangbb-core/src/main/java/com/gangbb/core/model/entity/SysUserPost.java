package com.gangbb.core.model.entity;


/**
 * 用户与岗位关联表 sys_user_post表对应实体类
 *
 * @author Gangbb
 * @since 2021-05-27
 */
 
public class SysUserPost extends BaseEntity {
	/** 用户ID */
	private Long userId;
	/** 岗位ID */
	private Long postId;

		public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
		public void setPostId(Long postId) {
		this.postId = postId;
	}
	
	public Long getPostId() {
		return this.postId;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) {return false;}
        SysUserPost that = (SysUserPost) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(userId);
    }
    
    @Override
    public String toString() {
        return "SysUserPost{" +
        				"userId=" + userId +
		        				",postId='" + postId + "'" + 
		                '}';
    }
	
}