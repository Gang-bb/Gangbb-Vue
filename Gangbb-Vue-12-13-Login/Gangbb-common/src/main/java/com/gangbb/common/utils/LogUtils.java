package com.gangbb.common.utils;


/**
 * @Author Gangbb
 * @Description 处理并记录日志文件
 * @Date 2021/7/11
 **/
public class LogUtils
{
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
