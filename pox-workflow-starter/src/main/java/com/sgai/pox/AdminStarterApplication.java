package com.sgai.pox;

import com.sgai.pox.admin.security.annotation.EnablePoxAuthorizationServer;
import com.sgai.pox.admin.security.annotation.EnablePoxResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pox
 * @date 2020年3月23日
 */
@SpringBootApplication
@EnablePoxAuthorizationServer
@EnablePoxResourceServer
public class AdminStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminStarterApplication.class, args);
    }
}
