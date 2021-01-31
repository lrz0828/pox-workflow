package com.sgai.pox.test.listener;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineLifecycleListener;

/**
 * @Auther: lirunze
 * @Date: 2021/1/27 13:42
 * @Description: 监听
 */
public class PoxProcessEngineLifecycleListener implements ProcessEngineLifecycleListener {

    /*
     * @Author lirunze
     * @Description 监听引擎启动
     * @Date 2021/1/27 13:46
     **/
    @Override
    public void onProcessEngineBuilt(ProcessEngine processEngine) {
        System.out.println("onProcessEngineBuilt:" + processEngine);
    }

    /*
     * @Author lirunze
     * @Description 监听引擎关闭
     * @Date 2021/1/27 13:46
     **/
    @Override
    public void onProcessEngineClosed(ProcessEngine processEngine) {
        System.out.println("onProcessEngineClosed:" + processEngine);
    }
}
