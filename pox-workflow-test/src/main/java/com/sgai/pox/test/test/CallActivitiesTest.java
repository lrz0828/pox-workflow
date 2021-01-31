package com.sgai.pox.test.test;

import java.util.HashMap;
import java.util.Map;

import com.sgai.pox.test.config.BaseConfiguation;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.junit.Test;


/**
 *调用活动（子流程）--测试
 *
 */
public class CallActivitiesTest extends BaseConfiguation {

	/**
	 * 部署
	 */
	@Test
	public void deploy() {
		DeploymentBuilder deploymentBuilder = repositoryService
												.createDeployment()
													.category("SubProcessesandCallActivitiesOfMainProcess")
													.name("SubProcessesandCallActivitiesOfMainProcess")
													.addClasspathResource("process/调用活动（子流程）之主流程.bpmn20.xml")
													.addClasspathResource("process/调用活动（子流程）之子流程.bpmn20.xml");
		Deployment deploy = deploymentBuilder.deploy();

		System.out.println("流程ID: " + deploy.getId());
	}
	/**
	 * 启动主流程实例 ：通过调用活动，子流程实例也会启动
	 * 
	 */
	@Test
	public void startMainProcess() {
		String processDefinitionKey = "SubProcessesandCallActivitiesOfMainProcess";
    	

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("main_var1", "主流程变量1");
        vars.put("main_var2", "主流程变量2");
        vars.put("main_var3", "主流程变量3");
        vars.put("main_var4", "主流程变量4");
		runtimeService.startProcessInstanceByKey(processDefinitionKey,vars);
	}
	
	/**
	 * 完成任务
	 */
	@Test
	public void complete() {
		String taskId = "5005";
		
		Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("approve", true);
		taskService.complete(taskId,vars);
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
