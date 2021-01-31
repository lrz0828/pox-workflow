package com.sgai.pox.test.test;

import java.util.HashMap;
import java.util.Map;

import com.sgai.pox.test.config.BaseConfiguation;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.junit.Test;


/**
 *事件子流程也可以添加到嵌入式子流程内--测试
 *
 */
public class EventSubprocessInSubProcessTest extends BaseConfiguation {

	/**
	 * 部署
	 */
	@Test
	public void deploy() {
		DeploymentBuilder deploymentBuilder = repositoryService
												.createDeployment()
													.category("eventsubprocessinsubprocess")
													.name("eventsubprocessinsubprocess")
													.addClasspathResource("process/事件子流程也可以添加到嵌入式子流程内.bpmn20.xml");
		Deployment deploy = deploymentBuilder.deploy();

		System.out.println("流程ID: " + deploy.getId());
	}
	/**
	 * 启动流程实例 
	 * 
	 */
	@Test
	public void start() {
		String processDefinitionKey = "eventsubprocessinsubprocess";
    	

        Map<String, Object> vars = new HashMap<String, Object>();
		runtimeService.startProcessInstanceByKey(processDefinitionKey,vars);
	}
	
	/**
	 * 完成任务
	 */
	@Test
	public void complete() {
		String taskId = "240011";

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("count", -1);
		taskService.complete(taskId,vars);
	}
}
