package com.gangbb.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Gangbb
 * @ClassName : TestController
 * @Description :
 * @Date : 2021/3/6 20:04
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String Hello(){
        return "第一个接口";
    }
}
