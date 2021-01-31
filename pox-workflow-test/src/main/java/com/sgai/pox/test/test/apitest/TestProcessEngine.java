package com.sgai.pox.test.test.apitest;

import com.sgai.pox.test.config.BaseConfiguation;
import org.apache.commons.io.FileUtils;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.DeploymentQuery;
import org.flowable.engine.repository.NativeDeploymentQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Auther: lirunze
 * @Date: 2021/1/26 14:03
 * @Description: processEngine, repositoryService
 */
public class TestProcessEngine extends BaseConfiguation {

    // 创建引擎
    @Test
    public void buildEngine1() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/engine?useUnicode=true&amp;characterEncoding=UTF-8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("1qaz2wsx#");
        processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);
    }

    @Test
    public void buildEngine2() {
        InputStream inputStream = TestProcessEngine.class.getClassLoader().getResourceAsStream("flowable-context.xml");
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(inputStream);
        processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);

    }


    @Test
    public void deploymengBuilder() {
        // 生成构造器
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        // 创建流程
        deploymentBuilder.category("subprocesstest").name("subprocesstest")
                .addClasspathResource("process/子流程.bpmn20.xml");
        Deployment deploy = deploymentBuilder.deploy();

        System.out.println("流程ID: " + deploy.getId());
    }

    @Test
    public void createProcessDefinitionQuery() {
        // 查询流程定义
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery
                .latestVersion()// 最后一个版本
                .list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println(processDefinition.getName());
            System.out.println(processDefinition.getCategory());
        }
    }

    @Test
    public void deleteDeployment() {
        String deploymentId = "2501";
        // 删除
//        repositoryService.deleteDeployment(deploymentId);
        // 删除 包含流程实例
        repositoryService.deleteDeployment(deploymentId, true);
    }

    @Test
    public void viewImage() throws IOException {
        // 图片
        String deploymentId = "5001";
        List<String> deploymentResourceNames = repositoryService.getDeploymentResourceNames(deploymentId);

        String imageName = "";
        for (String deploymentResourceName : deploymentResourceNames) {
            if (deploymentResourceName.indexOf(".png") > 0) {
                imageName = deploymentResourceName;
            }
        }
        System.out.println(imageName);

        File file = new File("C:/Users/lirun/Desktop/" + imageName);
        InputStream resourceAsStream = repositoryService.getResourceAsStream(deploymentId, imageName);
        FileUtils.copyInputStreamToFile(resourceAsStream, file);
    }

    @Test
    public void createDeploymentQuery() {
        // 查询部署对象
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        List<Deployment> list = deploymentQuery.list();
        for (Deployment deployment : list) {
            System.out.println(deployment.getName());
        }
    }

    @Test
    public void createNativeDeploymentQuery() {
        // 查询部署对象(自定义sql)
        NativeDeploymentQuery deploymentQuery = repositoryService.createNativeDeploymentQuery();
        List<Deployment> list = deploymentQuery
                .sql("select RES.* from ACT_RE_DEPLOYMENT RES order by RES.ID_ asc").list();
        for (Deployment deployment : list) {
            System.out.println(deployment.getName());
        }
    }
}
