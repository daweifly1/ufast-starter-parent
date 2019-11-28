package com.fast.starter.druid.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.datasource.druid")
public class DruidStatProperties
{
  private String[] aopPatterns;
  private StatViewServlet statViewServlet = new StatViewServlet();
  private WebStatFilter webStatFilter = new WebStatFilter();
  
  public String[] getAopPatterns()
  {
    return this.aopPatterns;
  }
  
  public void setAopPatterns(String[] aopPatterns)
  {
    this.aopPatterns = aopPatterns;
  }
  
  public StatViewServlet getStatViewServlet()
  {
    return this.statViewServlet;
  }
  
  public void setStatViewServlet(StatViewServlet statViewServlet)
  {
    this.statViewServlet = statViewServlet;
  }
  
  public WebStatFilter getWebStatFilter()
  {
    return this.webStatFilter;
  }
  
  public void setWebStatFilter(WebStatFilter webStatFilter)
  {
    this.webStatFilter = webStatFilter;
  }
  
  public static class StatViewServlet
  {
    private boolean enabled = true;
    private String urlPattern;
    private String allow;
    private String deny;
    private String loginUsername = "admin";
    private String loginPassword = "123456";
    private String resetEnable;
    
    public boolean isEnabled()
    {
      return this.enabled;
    }
    
    public void setEnabled(boolean enabled)
    {
      this.enabled = enabled;
    }
    
    public String getUrlPattern()
    {
      return this.urlPattern;
    }
    
    public void setUrlPattern(String urlPattern)
    {
      this.urlPattern = urlPattern;
    }
    
    public String getAllow()
    {
      return this.allow;
    }
    
    public void setAllow(String allow)
    {
      this.allow = allow;
    }
    
    public String getDeny()
    {
      return this.deny;
    }
    
    public void setDeny(String deny)
    {
      this.deny = deny;
    }
    
    public String getLoginUsername()
    {
      return this.loginUsername;
    }
    
    public void setLoginUsername(String loginUsername)
    {
      this.loginUsername = loginUsername;
    }
    
    public String getLoginPassword()
    {
      return this.loginPassword;
    }
    
    public void setLoginPassword(String loginPassword)
    {
      this.loginPassword = loginPassword;
    }
    
    public String getResetEnable()
    {
      return this.resetEnable;
    }
    
    public void setResetEnable(String resetEnable)
    {
      this.resetEnable = resetEnable;
    }
  }
  
  public static class WebStatFilter
  {
    private boolean enabled = true;
    private String urlPattern;
    private String exclusions;
    private String sessionStatMaxCount;
    private String sessionStatEnable;
    private String principalSessionName;
    private String principalCookieName;
    private String profileEnable;
    
    public boolean isEnabled()
    {
      return this.enabled;
    }
    
    public void setEnabled(boolean enabled)
    {
      this.enabled = enabled;
    }
    
    public String getUrlPattern()
    {
      return this.urlPattern;
    }
    
    public void setUrlPattern(String urlPattern)
    {
      this.urlPattern = urlPattern;
    }
    
    public String getExclusions()
    {
      return this.exclusions;
    }
    
    public void setExclusions(String exclusions)
    {
      this.exclusions = exclusions;
    }
    
    public String getSessionStatMaxCount()
    {
      return this.sessionStatMaxCount;
    }
    
    public void setSessionStatMaxCount(String sessionStatMaxCount)
    {
      this.sessionStatMaxCount = sessionStatMaxCount;
    }
    
    public String getSessionStatEnable()
    {
      return this.sessionStatEnable;
    }
    
    public void setSessionStatEnable(String sessionStatEnable)
    {
      this.sessionStatEnable = sessionStatEnable;
    }
    
    public String getPrincipalSessionName()
    {
      return this.principalSessionName;
    }
    
    public void setPrincipalSessionName(String principalSessionName)
    {
      this.principalSessionName = principalSessionName;
    }
    
    public String getPrincipalCookieName()
    {
      return this.principalCookieName;
    }
    
    public void setPrincipalCookieName(String principalCookieName)
    {
      this.principalCookieName = principalCookieName;
    }
    
    public String getProfileEnable()
    {
      return this.profileEnable;
    }
    
    public void setProfileEnable(String profileEnable)
    {
      this.profileEnable = profileEnable;
    }
  }
}
