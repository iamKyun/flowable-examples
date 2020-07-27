package com.iamkyun.flowable.example;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.form.engine.test.FormDeploymentAnnotation;
import org.flowable.spring.impl.test.FlowableSpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ExampleApplication.class)
class ExampleApplicationTests {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    @FormDeploymentAnnotation
    public void simpleFormInstanceTest() {
        List<ProcessDefinition> processDefinitions =
                repositoryService.createProcessDefinitionQuery().processDefinitionKey("holidayRequest").list();
        assertFalse(processDefinitions.isEmpty());
    }

}
