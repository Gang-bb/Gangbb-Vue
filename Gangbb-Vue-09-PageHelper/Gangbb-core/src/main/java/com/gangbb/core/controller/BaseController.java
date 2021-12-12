package com.gangbb.core.controller;

import com.gangbb.common.constant.HttpStatus;
import com.gangbb.common.model.ApiRestResponse;
import com.gangbb.common.utils.DateUtils;
import com.gangbb.common.utils.StringUtils;
import com.gangbb.common.utils.sql.SqlUtil;
import com.gangbb.core.model.page.PageDto;
import com.gangbb.core.model.page.TableDataInfo;
import com.gangbb.core.model.page.TableSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * @author : Gangbb
 * @ClassName : BaseController
 * @Description : web层通用数据处理
 * @Date : 2021/3/12 21:21
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        //从前端获取分页参数
        PageDto myPage = TableSupport.buildPageRequest();
        Integer pageNum = myPage.getPageNum();
        Integer pageSize = myPage.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(myPage.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected ApiRestResponse toApiRes(int rows)
    {
        return rows > 0 ? ApiRestResponse.success() : ApiRestResponse.error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }
}
