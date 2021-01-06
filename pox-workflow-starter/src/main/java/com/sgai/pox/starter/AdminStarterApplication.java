package com.sgai.pox.starter;

import com.sgai.pox.admin.security.annotation.EnableZjmzxfzhlAuthorizationServer;
import com.sgai.pox.admin.security.annotation.EnableZjmzxfzhlResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 庄金明
 * @date 2020年3月23日
 */
@SpringBootApplication
@EnableZjmzxfzhlAuthorizationServer
@EnableZjmzxfzhlResourceServer
public class AdminStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminStarterApplication.class, args);
    }
}
