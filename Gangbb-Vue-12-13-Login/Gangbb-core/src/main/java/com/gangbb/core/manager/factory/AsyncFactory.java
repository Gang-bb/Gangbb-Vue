package com.gangbb.core.manager.factory;

/**
 * @author : Gangbb
 * @ClassName : AsyncFactory
 * @Description : 异步工厂（产生任务用）
 * @Date : 2021/3/7 17:08
 */

import com.gangbb.common.constant.Constants;
import com.gangbb.common.utils.LogUtils;
import com.gangbb.common.utils.ServletUtils;
import com.gangbb.common.utils.ip.AddressUtils;
import com.gangbb.common.utils.ip.IpUtils;
import com.gangbb.common.utils.spring.SpringUtils;
import com.gangbb.core.model.entity.SysLoginInfo;
import com.gangbb.core.model.entity.SysOperationLog;
import com.gangbb.core.service.SysLoginInfoService;
import com.gangbb.core.service.SysOperationLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author ruoyi
 */
public class AsyncFactory
{
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

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

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLoginInfo(final String username, final String status, final String message,
                                             final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask()
        {
            @Override
            public void run()
            {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLoginInfo loginInfo = new SysLoginInfo();
                loginInfo.setUserName(username);
                loginInfo.setIpAddress(ip);
                loginInfo.setLoginLocation(address);
                loginInfo.setBrowser(browser);
                loginInfo.setOs(os);
                loginInfo.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status))
                {
                    loginInfo.setStatus(Constants.SUCCESS);
                }
                else if (Constants.LOGIN_FAIL.equals(status))
                {
                    loginInfo.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(SysLoginInfoService.class).insertLoginInfo(loginInfo);
            }
        };
    }
}
