package com.sgai.pox.engine.common.config;

import com.google.common.collect.Maps;
import com.sgai.pox.engine.common.listener.TaskBeforeListener;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Configuration
public class FlowableEngineConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {
    @Value("${pox.flowable.font-name}")
    private String flowableFontName;

    @Autowired
    private TaskBeforeListener taskBeforeListener;

    @Override
    public void configure(SpringProcessEngineConfiguration engineConfiguration) {
        engineConfiguration.setProcessDiagramGenerator(processDiagramGenerator());
        engineConfiguration.setActivityFontName(flowableFontName);
        engineConfiguration.setLabelFontName(flowableFontName);
        engineConfiguration.setAnnotationFontName(flowableFontName);
        engineConfiguration.setTypedEventListeners(customFlowableListeners());
        engineConfiguration.setDatabaseType("mysql");
        // 开启异步
//        engineConfiguration.setAsyncHistoryEnabled(true);
//        engineConfiguration.setAsyncExecutorAsyncJobAcquisitionEnabled(true);
    }

    @Bean
    public ProcessDiagramGenerator processDiagramGenerator() {
        return new CustomProcessDiagramGenerator();
    }

    private Map<String, List<FlowableEventListener>> customFlowableListeners () {
        Map<String, List<FlowableEventListener>> listenerMap = Maps.newHashMap();
        listenerMap.put(FlowableEngineEventType.TASK_CREATED.name(),
                new ArrayList<>(Collections.singletonList(taskBeforeListener)));
        return listenerMap;
    }
}
