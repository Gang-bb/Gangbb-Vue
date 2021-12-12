package com.gangbb.core.manager.factory;

/**
 * @author : Gangbb
 * @ClassName : AsyncFactory
 * @Description : 异步工厂（产生任务用）
 * @Date : 2021/3/7 17:08
 */

import java.util.TimerTask;

import com.gangbb.common.utils.spring.SpringUtils;
import com.gangbb.common.utils.ip.AddressUtils;
import com.gangbb.core.model.entity.sys.SysOperationLog;
import com.gangbb.core.service.SysOperationLogService;

/**
 * 异步工厂（产生任务用）
 *
 * @author ruoyi
 */
public class AsyncFactory
{

    /**
     * 操作日志记录
     *
     * @param sysOperationLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOperation(final SysOperationLog sysOperationLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                sysOperationLog.setOperationLocation(AddressUtils.getRealAddressByIP(sysOperationLog.getOperationIp()));
                SpringUtils.getBean(SysOperationLogService.class).insertOperationLog(sysOperationLog);
            }
        };
    }
}
