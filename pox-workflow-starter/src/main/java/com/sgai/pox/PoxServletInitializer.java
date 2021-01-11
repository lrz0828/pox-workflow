package com.sgai.pox;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author pox
 * @date 2021年01月04日
 */
public class PoxServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(EngineStarterApplication.class);
        return application.sources(StarterApplication.class);
    }
}
