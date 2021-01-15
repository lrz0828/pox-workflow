package com.sgai.pox;

import com.sgai.pox.admin.core.util.EnvironmentUtil;
import com.sgai.pox.engine.core.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AdminApplication {
    private static Logger logger = LoggerFactory.getLogger(AdminApplication.class);

    public static void main(String[] args) {
        EnvironmentUtil.setSystemEnv(args);
        // 1 admin 2 starter 其他情况默认为 engine被单独引用
        System.setProperty("start.TYPE", "1");
        System.setProperty("server.TYPE", "service-base");
        SpringApplication application = new SpringApplication(AdminApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        ApplicationContext applicationContext = application.run(args);
        SpringBeanUtil.setApplicationContext(applicationContext);
        logger.info("service-base start success");
    }
}
