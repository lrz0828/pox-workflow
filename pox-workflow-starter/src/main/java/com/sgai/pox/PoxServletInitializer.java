package com.sgai.pox;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author pox
 * @date 2020年3月23日
 */
public class PoxServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(EngineStarterApplication.class);
        return application.sources(AdminStarterApplication.class);
    }
}
