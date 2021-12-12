package com.gangbb.core.model.dto;

import com.gangbb.common.constant.TableSupport;
import com.gangbb.common.utils.ServletUtils;
import com.gangbb.common.utils.StringUtils;

import javax.validation.constraints.Max;



/**
 * @author : Gangbb
 * @ClassName : Page
 * @Description : 分页数据
 * @Date : 2021/3/12 21:41
 */
public class PageDTO {

    /** 当前记录起始索引 */
    @Max(9999)
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 排序列 */
    private String orderByColumn;

    /** 排序的方向desc或者asc */
    private String isAsc = "asc";

    /**
     * 封装分页对象
     */
    public static PageDTO getMyPage()
    {
        PageDTO myyPage = new PageDTO();
        // 获取请求参数中的各项值
        myyPage.setPageNum(ServletUtils.getParameterToInt(TableSupport.PAGE_NUM));
        myyPage.setPageSize(ServletUtils.getParameterToInt(TableSupport.PAGE_SIZE));
        myyPage.setOrderByColumn(ServletUtils.getParameter(TableSupport.ORDER_BY_COLUMN));
        myyPage.setIsAsc(ServletUtils.getParameter(TableSupport.IS_ASC));
        return myyPage;
    }

    public static PageDTO buildPageRequest()
    {
        return getMyPage();
    }

    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        // 如果前端没传，就默认从小到大排序(正序)
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        this.isAsc = isAsc;
    }
}
