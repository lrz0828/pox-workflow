package com.sgai.pox.test.test;

import java.util.HashMap;
import java.util.Map;

import com.sgai.pox.test.config.BaseConfiguation;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.junit.Test;


/**
 *子流程--测试
 *
 */
public class SubProcessTest extends BaseConfiguation {

	/**
	 * 部署
	 */
	@Test
	public void deploy() {
		DeploymentBuilder deploymentBuilder = repositoryService
												.createDeployment()
													.category("subprocesstest")
													.name("subprocesstest")
													.addClasspathResource("process/子流程.bpmn20.xml");
		Deployment deploy = deploymentBuilder.deploy();

		System.out.println("流程ID: " + deploy.getId());
	}
	/**
	 * 启动流程实例 
	 * 
	 */
	@Test
	public void start() {
		String processDefinitionKey = "subprocesstest";
    	

        Map<String, Object> vars = new HashMap<String, Object>();
		runtimeService.startProcessInstanceByKey(processDefinitionKey,vars);
	}
	
	/**
	 * 完成任务
	 */
	@Test
	public void complete() {
		String taskId = "177505";
		taskService.complete(taskId);
	}
	/**
	 * 睡眠5分钟
	 * @throws InterruptedException 
	 */
	@Test
	public void sleep() throws InterruptedException {
		Long millis = 300000L;
		Thread.sleep(millis);
	}
}
