package com.gangbb.test.controller;

import com.gangbb.common.model.ApiRestResponse;
import com.gangbb.test.model.request.GroupParamRequest;
import com.gangbb.test.model.request.TestParamRequest;
import com.gangbb.test.model.request.group.Group1;
import com.gangbb.test.model.request.group.Group2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author Gangbb
 * @Description 参数校验测试controller
 * @Date 2021/7/15
 **/
@RestController
public class TestParameterController {

    /**
     * @Author Gangbb
     * @Description post方法 RequestBody 对象参数校验
     * @Date 2021/7/15
     **/
    @PostMapping("/test/param")
    public ApiRestResponse param(@Valid @RequestBody TestParamRequest testParam) {
        return ApiRestResponse.success();
    }

    /**
     * @Author Gangbb
     * @Description get方法 RequestParam 参数校验
     * @Date 2021/7/15
     **/
    @GetMapping("/test/param2")
    public ApiRestResponse param2(@Valid @NotBlank(message = "姓名不能为空") @RequestParam("name")String name,
                                  @Valid @NotNull(message = "年龄不能为空")  @RequestParam("age")Integer age)
    {

        return ApiRestResponse.success();
    }

    /**
     * @Author Gangbb
     * @Description get方法 RequestParam 参数校验
     * @Date 2021/7/15
     **/
    @GetMapping("/test/param7")
    public ApiRestResponse param7(@Valid @RequestParam("name")String name,
                                  @RequestParam("age")Integer age)
    {

        return ApiRestResponse.success();
    }


    /**
     * @Author Gangbb
     * @Description post方法  非RequestBody 对象参数校验(不建议使用)
     * @Date 2021/7/15
     **/
    @PostMapping("/test/param3")
    public ApiRestResponse param3(@Valid TestParamRequest testParam)
    {

        return ApiRestResponse.success();
    }


    /**
     * @Author Gangbb
     * @Description get方法  对象参数校验
     * @Date 2021/7/15
     **/
    @GetMapping("/test/param4")
    public ApiRestResponse param4(@Valid TestParamRequest testParam)
    {

        return ApiRestResponse.success();
    }

    /**
     * @Author Gangbb
     * @Description get方法 分组校验1
     * @Date 2021/7/15
     **/
    @GetMapping("/test/param5")
    public ApiRestResponse param5(@Validated(Group1.class) @RequestBody GroupParamRequest groupParamRequest)
    {

        return ApiRestResponse.success();
    }

    /**
     * @Author Gangbb
     * @Description get方法 分组校验2
     * @Date 2021/7/15
     **/
    @GetMapping("/test/param6")
    public ApiRestResponse param6(@Validated(Group2.class) @RequestBody GroupParamRequest groupParamRequest)
    {

        return ApiRestResponse.success();
    }
}

