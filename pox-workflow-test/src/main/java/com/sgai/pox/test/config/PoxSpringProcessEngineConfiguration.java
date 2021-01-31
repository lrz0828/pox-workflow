package com.sgai.pox.test.config;

import org.flowable.spring.SpringProcessEngineConfiguration;

/**
 * @Auther: lirunze
 * @Date: 2021/1/27 15:18
 * @Description:
 */
public class PoxSpringProcessEngineConfiguration extends SpringProcessEngineConfiguration {

    @Override
    public void init() {
        System.out.println("PoxSpringProcessEngineConfiguration:init()");
        super.init();
    }

    @Override
    protected void initDataSource() {
        super.initDataSource();
    }
}
