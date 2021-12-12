package com.gangbb.web.controller.system;

import com.gangbb.common.constant.Constants;
import com.gangbb.common.model.ApiRestResponse;
import com.gangbb.common.utils.ServletUtils;
import com.gangbb.common.utils.spring.SpringUtils;
import com.gangbb.core.model.dto.SysUserDTO;
import com.gangbb.core.model.request.LoginRequest;
import com.gangbb.core.security.service.SysPermissionService;
import com.gangbb.core.security.service.TokenService;
import com.gangbb.core.service.SysMenuService;
import com.gangbb.core.service.impl.SysLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author liangyixiang
 * @Description 登录验证
 * @Date 2021/7/11
 **/
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginServiceImpl loginService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;


    /**
     * 登录
     *
     * @param loginRequest 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public ApiRestResponse login(@Valid @RequestBody LoginRequest loginRequest)
    {

        // 生成令牌
        String token = loginService.login(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getCode(),
                loginRequest.getUuid());
        Map<String, String> result= new HashMap<>();
        result.put(Constants.TOKEN, token);
        return ApiRestResponse.success(result);
    }

    @PostMapping("/loginAfter")
    public ApiRestResponse loginAfter(){

        // 获取当前的用户
        SysUserDTO sysUserDTO = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());


        return ApiRestResponse.success(sysUserDTO);
    }
}
