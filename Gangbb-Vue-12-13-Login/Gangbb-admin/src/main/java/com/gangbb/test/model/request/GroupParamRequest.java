package com.gangbb.test.model.request;

import com.gangbb.test.model.request.group.Group1;
import com.gangbb.test.model.request.group.Group2;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author Gangbb
 * @Description 参数校验-分组校验示例
 * @Date 2021/7/15
 **/
public class GroupParamRequest {

    @NotBlank(message = "param1 不能为空", groups = {Group1.class, Group2.class})
    private String param1;

    @NotNull(message = "param2 不能为空", groups = {Group1.class})
    private Integer param2;

    @NotBlank(message = "param3 不能为空", groups = {Group2.class})
    private String param3;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public Integer getParam2() {
        return param2;
    }

    public void setParam2(Integer param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }
}