package com.fast.starter.druid.stat;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.fast.starter.druid.prop.DruidStatProperties;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@ConditionalOnProperty({"spring.datasource.druid.aop-patterns"})
public class DruidSpringAopConfiguration
{
  @Bean
  public Advice advice()
  {
    return new DruidStatInterceptor();
  }
  
  @Bean
  public Advisor advisor(DruidStatProperties properties)
  {
    return new RegexpMethodPointcutAdvisor(properties.getAopPatterns(), advice());
  }
  
  @Bean
  public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator()
  {
    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    advisorAutoProxyCreator.setProxyTargetClass(true);
    return advisorAutoProxyCreator;
  }
}
