package com.iamkyun.flowable.example;

import com.iamkyun.flowable.example.idm.engine.impl.CustomIdmIdentityServiceImpl;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    public EngineConfigurationConfigurer<SpringIdmEngineConfiguration> idmEngineConfigurationConfigurer() {
        return idmEngineConfiguration -> idmEngineConfiguration.setIdmIdentityService(
                new CustomIdmIdentityServiceImpl());
    }

}
