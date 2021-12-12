package com.gangbb.core.mapper;

import com.gangbb.core.model.entity.SysOperationLog;

import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : SysOperationLogMapper
 * @Description :
 * @Date : 2021/3/7 17:44
 */
public interface SysOperationLogMapper {
    /**
     * 新增操作日志
     *
     * @param sysOperationLog 操作日志对象
     */
    public void insertOperationLog(SysOperationLog sysOperationLog);

    public List<SysOperationLog> selectAll();
}
