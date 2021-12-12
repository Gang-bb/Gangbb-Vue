package com.gangbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author : Gangbb
 * @ClassName : AdminApplication
 * @Description : 启动程序
 * @Date : 2021/3/6 10:49
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class AdminApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(AdminApplication.class, args);
    }
}

