package com.sgai.pox;

import com.sgai.pox.admin.security.annotation.EnablePoxAuthorizationServer;
import com.sgai.pox.admin.security.annotation.EnablePoxResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pox
 * @date 2021年01月04日
 */
@SpringBootApplication
@EnablePoxAuthorizationServer
@EnablePoxResourceServer
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
