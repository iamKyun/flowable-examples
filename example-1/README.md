### 自定义身份管理

参考文档：https://flowable.com/open-source/docs/bpmn/ch18-Advanced/#custom-identity-management-by-overriding-standard-sessionfactory

自动配置文件：flowable-spring-boot-autoconfigure-6.5.0/META-INF/spring.factories

自动配置类：org.flowable.spring.boot.ProcessEngineAutoConfiguration,org.flowable.spring.boot.idm.IdmEngineAutoConfiguration

生效的流程引擎配置器：org.flowable.engine.configurator.ProcessEngineConfigurator

修改spring boot 的自动配置

仿照LDAP实现



flowable-spring-boot-autoconfigure-6.5.0/META-INF/spring.factories

- org.flowable.spring.boot.ProcessEngineAutoConfiguration
  - 
- org.flowable.spring.boot.idm.IdmEngineAutoConfiguration
  - org.flowable.idm.spring.SpringIdmEngineConfiguration
    - org.flowable.idm.engine.IdmEngineConfiguration # idmIdentityService



用Spring Bean的方式新建一个属于SpringIdmEngineConfiguration的引擎配置器，在配置器里替换调SpringIdmEngineConfiguration的IdmIdentityService

```java
@Bean
public EngineConfigurationConfigurer<SpringIdmEngineConfiguration> idmEngineConfigurationConfigurer() {
	return idmEngineConfiguration -> idmEngineConfiguration.setIdmIdentityService(
	new CustomIdmIdentityServiceImpl());
}
```

