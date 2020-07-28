package com.iamkyun.flowable.example;

import com.iamkyun.flowable.example.idm.engine.impl.CustomIdmIdentityServiceImpl;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.spring.boot.AbstractEngineAutoConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.flowable.spring.boot.FlowableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

}


@Configuration
class CustomSpringIdmEngineConfiguration extends AbstractEngineAutoConfiguration {
    public CustomSpringIdmEngineConfiguration(FlowableProperties flowableProperties) {
        super(flowableProperties);
    }

    /**
     * 不是最好的方法
     */
    @Bean
    public SpringIdmEngineConfiguration idmEngineConfiguration(DataSource dataSource,
                                                               PlatformTransactionManager platformTransactionManager) {
        SpringIdmEngineConfiguration configuration = new SpringIdmEngineConfiguration();

        configuration.setTransactionManager(platformTransactionManager);
        configureEngine(configuration, dataSource);

        // 此处修改IdmIdentityService
        configuration.setIdmIdentityService(new CustomIdmIdentityServiceImpl());
        return configuration;
    }

    /**
     * 好一点的方法
     */
    @Bean
    public EngineConfigurationConfigurer<SpringIdmEngineConfiguration> idmEngineConfigurationConfigurer() {
        return engineConfiguration -> engineConfiguration.setIdmIdentityService(new CustomIdmIdentityServiceImpl());
    }
}

