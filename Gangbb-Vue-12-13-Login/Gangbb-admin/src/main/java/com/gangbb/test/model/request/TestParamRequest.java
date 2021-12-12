package com.gangbb.test.model.request;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author Gangbb
 * @Description 测试参数校验类
 * @Date 2021/7/15
 **/
public class TestParamRequest {

    @NotBlank(message = "param1 不能为空")
    String param1;

    @NotNull(message = "param2 不能为空")
    Integer param2;

    @NotNull(message = "param2 不能为空")
    @Max(value = 20, message = "年龄不能超过20")
    Integer age;

    @NotNull
    @Valid
    TestParam2Request testParam2;

    public TestParam2Request getTestParam2() {
        return testParam2;
    }

    public void setTestParam2(TestParam2Request testParam2) {
        this.testParam2 = testParam2;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}