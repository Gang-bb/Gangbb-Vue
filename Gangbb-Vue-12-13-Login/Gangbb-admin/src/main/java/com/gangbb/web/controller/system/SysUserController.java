package com.gangbb.web.controller.system;

import com.gangbb.common.annotation.Log;
import com.gangbb.common.enums.BusinessType;
import com.gangbb.common.model.ApiRestResponse;
import com.gangbb.core.controller.BaseController;
import com.gangbb.core.model.request.AddUserRequest;
import com.gangbb.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author Gangbb
 * @Description 用户信息
 * @Date 2021/7/12
 **/
@RestController
@RequestMapping("/test/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService userService;

    /**
     * 新增用户
     */
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/addUser")
    public ApiRestResponse addUser(@Valid @RequestBody AddUserRequest addUserRequest) {

        return toApiRes(userService.addUser(addUserRequest));
    }
}