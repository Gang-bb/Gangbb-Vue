package com.gangbb.common.utils.sql;


import com.gangbb.common.exception.ApiException;
import com.gangbb.common.utils.StringUtils;

/**
 * @author : Gangbb
 * @ClassName : SqlUtil
 * @Description : sql操作工具类
 * @Date : 2021/3/12 22:05
 */

public class SqlUtil
{
    /**
     * 仅支持字母、数字、下划线、空格、逗号、小数点（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value)
    {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            //A0004--参数不符合规范，不能进行查询
            throw new ApiException("A0004");
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }
}
