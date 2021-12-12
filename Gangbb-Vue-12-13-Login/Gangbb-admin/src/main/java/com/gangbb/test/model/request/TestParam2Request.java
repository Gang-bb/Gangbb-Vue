package com.gangbb.test.model.request;

import javax.validation.constraints.NotBlank;

/**
 * @Author Gangbb
 * @Description 测试参数校验类2
 * @Date 2021/7/15
 **/
public class TestParam2Request{


    @NotBlank(message = "TestParam2中的 param3 不能为空")
    String param3;

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }
}