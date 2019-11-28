package com.fast.starter;

import com.alibaba.druid.pool.DruidDataSource;
import com.fast.starter.druid.DruidDataSourceWrapper;
import com.fast.starter.druid.prop.DruidStatProperties;
import com.fast.starter.druid.stat.DruidFilterConfiguration;
import com.fast.starter.druid.stat.DruidSpringAopConfiguration;
import com.fast.starter.druid.stat.DruidStatViewServletConfiguration;
import com.fast.starter.druid.stat.DruidWebStatFilterConfiguration;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnClass({DruidDataSource.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({DruidStatProperties.class, DataSourceProperties.class})
@Import({DruidSpringAopConfiguration.class, DruidStatViewServletConfiguration.class, DruidWebStatFilterConfiguration.class, DruidFilterConfiguration.class})
public class FastAutoConfigure
{
  private static final Logger LOGGER = LoggerFactory.getLogger(FastAutoConfigure.class);
  
  @Bean(initMethod="init")
  @ConditionalOnMissingBean
  public DataSource dataSource()
  {
    LOGGER.info("Init DruidDataSource");
    return new DruidDataSourceWrapper();
  }
}
