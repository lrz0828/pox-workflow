package com.sgai.pox.test.config;

import org.junit.After;
import org.junit.Before;
import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.FormService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:flowable-context.xml")
public class BaseConfiguation{

	protected ProcessEngine processEngine;
	protected TaskService taskService;
	protected RuntimeService runtimeService;
	protected RepositoryService repositoryService;
	protected HistoryService historyService;
	protected DynamicBpmnService dynamicBpmnService;
	protected FormService formService;
	protected IdentityService identityService;
	protected ManagementService managementService;
	protected ProcessEngineConfiguration processEngineConfiguration;

	@Before
	public void testProcessEngine() {
		processEngine = ProcessEngines.getDefaultProcessEngine();
		System.out.println("流程引擎类：" + processEngine);

		taskService = processEngine.getTaskService();
		runtimeService = processEngine.getRuntimeService();
		repositoryService = processEngine.getRepositoryService();
		historyService = processEngine.getHistoryService();
		dynamicBpmnService = processEngine.getDynamicBpmnService();
		formService = processEngine.getFormService();
		identityService = processEngine.getIdentityService();
		managementService = processEngine.getManagementService();
		processEngineConfiguration = processEngine.getProcessEngineConfiguration();

		String name = processEngine.getName();

		System.out.println("流程引擎的名称： " + name);
		System.out.println(processEngineConfiguration);

	}

	/**
	 * 关闭流程引擎
	 */
	@After
	public void close() {
		processEngine.close();
	}

}
