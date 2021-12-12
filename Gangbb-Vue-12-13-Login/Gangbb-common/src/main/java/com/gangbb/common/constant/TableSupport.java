package com.gangbb.common.constant;

/**
 * @author : Gangbb
 * @ClassName : TableSupport
 * @Description : 获取分页参数
 * @Date : 2021/3/12 21:57
 */

public class TableSupport
{
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 封装分页对象
     */
//    public static PageDTO getMyPage()
//    {
//        PageDTO myyPage = new PageDTO();
//        // 获取请求参数中的各项值
//        myyPage.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
//        myyPage.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
//        myyPage.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
//        myyPage.setIsAsc(ServletUtils.getParameter(IS_ASC));
//        return myyPage;
//    }
//
//    public static PageDTO buildPageRequest()
//    {
//        return getMyPage();
//    }
}
