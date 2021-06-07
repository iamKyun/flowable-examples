package com.iamkyun.flowable.example;

import org.flowable.engine.ManagementService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.engine.test.FormDeploymentAnnotation;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ExampleApplication.class)
class ExampleApplicationTests {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ManagementService managementService;

    @Test
    @FormDeploymentAnnotation
    public void test() {
        ProcessInstance processInstance
                = runtimeService.startProcessInstanceByKey("holidayRequest");

        Task firstTask = taskService.createTaskQuery()
                                    .processInstanceId(processInstance.getId())
                                    .includeProcessVariables()
                                    .singleResult();

        taskService.complete(firstTask.getId());

        assertNotNull(firstTask);
        assertEquals("firstTask", firstTask.getTaskDefinitionKey());
        String businessKey = (String)runtimeService.getVariable("processInstanceId", "businessKey");



    }

    @Test
    @FormDeploymentAnnotation
    public void test2() {
        managementService.executeCommand(commandContext -> {
            RuntimeService runtimeService = CommandContextUtil
                    .getProcessEngineConfiguration(commandContext)
                    .getRuntimeService();
            TaskService taskService = CommandContextUtil
                    .getProcessEngineConfiguration(commandContext)
                    .getTaskService();

            ProcessInstance processInstance
                    = runtimeService.startProcessInstanceByKey("holidayRequest");

            Task firstTask = taskService.createTaskQuery()
                                        .processInstanceId(processInstance.getId())
                                        .singleResult();

            assertNotNull(firstTask);
            assertEquals("firstTask", firstTask.getTaskDefinitionKey());

            return null;
        });
    }
}
