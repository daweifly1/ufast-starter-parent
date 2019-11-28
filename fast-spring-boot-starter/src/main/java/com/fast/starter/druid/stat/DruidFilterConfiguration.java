package com.fast.starter.druid.stat;

import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.filter.encoding.EncodingConvertFilter;
import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class DruidFilterConfiguration
{
  private static final String FILTER_STAT_PREFIX = "spring.datasource.druid.filter.stat";
  private static final String FILTER_CONFIG_PREFIX = "spring.datasource.druid.filter.config";
  private static final String FILTER_ENCODING_PREFIX = "spring.datasource.druid.filter.encoding";
  private static final String FILTER_SLF4J_PREFIX = "spring.datasource.druid.filter.slf4j";
  private static final String FILTER_LOG4J_PREFIX = "spring.datasource.druid.filter.log4j";
  private static final String FILTER_LOG4J2_PREFIX = "spring.datasource.druid.filter.log4j2";
  private static final String FILTER_COMMONS_LOG_PREFIX = "spring.datasource.druid.filter.commons-log";
  private static final String FILTER_WALL_PREFIX = "spring.datasource.druid.filter.wall";
  private static final String FILTER_WALL_CONFIG_PREFIX = "spring.datasource.druid.filter.wall.config";
  
  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.stat")
  @ConditionalOnProperty(prefix="spring.datasource.druid.filter.stat", name={"enabled"}, matchIfMissing=true)
  @ConditionalOnMissingBean
  public StatFilter statFilter()
  {
    return new StatFilter();
  }
  
  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.config")
  @ConditionalOnProperty(prefix="spring.datasource.druid.filter.config", name={"enabled"})
  @ConditionalOnMissingBean
  public ConfigFilter configFilter()
  {
    return new ConfigFilter();
  }
  
  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.encoding")
  @ConditionalOnProperty(prefix="spring.datasource.druid.filter.encoding", name={"enabled"})
  @ConditionalOnMissingBean
  public EncodingConvertFilter encodingConvertFilter()
  {
    return new EncodingConvertFilter();
  }
  
  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.slf4j")
  @ConditionalOnProperty(prefix="spring.datasource.druid.filter.slf4j", name={"enabled"})
  @ConditionalOnMissingBean
  public Slf4jLogFilter slf4jLogFilter()
  {
    return new Slf4jLogFilter();
  }
  
  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.log4j")
  @ConditionalOnProperty(prefix="spring.datasource.druid.filter.log4j", name={"enabled"})
  @ConditionalOnMissingBean
  public Log4jFilter log4jFilter()
  {
    return new Log4jFilter();
  }
  
  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.log4j2")
  @ConditionalOnProperty(prefix="spring.datasource.druid.filter.log4j2", name={"enabled"})
  @ConditionalOnMissingBean
  public Log4j2Filter log4j2Filter()
  {
    return new Log4j2Filter();
  }
  
  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.commons-log")
  @ConditionalOnProperty(prefix="spring.datasource.druid.filter.commons-log", name={"enabled"})
  @ConditionalOnMissingBean
  public CommonsLogFilter commonsLogFilter()
  {
    return new CommonsLogFilter();
  }
  
  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.wall.config")
  @ConditionalOnProperty(prefix="spring.datasource.druid.filter.wall", name={"enabled"})
  @ConditionalOnMissingBean
  public WallConfig wallConfig()
  {
    return new WallConfig();
  }
  
  @Bean
  @ConfigurationProperties("spring.datasource.druid.filter.wall")
  @ConditionalOnProperty(prefix="spring.datasource.druid.filter.wall", name={"enabled"})
  @ConditionalOnMissingBean
  public WallFilter wallFilter(WallConfig wallConfig)
  {
    WallFilter filter = new WallFilter();
    filter.setConfig(wallConfig);
    return filter;
  }
}
