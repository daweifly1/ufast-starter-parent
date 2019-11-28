/*    */ package yb.ecp.fast.infra.infra.eureka;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.springframework.cloud.netflix.eureka.CloudEurekaInstanceConfig;
/*    */ import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
/*    */ import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
/*    */ import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaServiceRegistry;
/*    */ import yb.ecp.fast.infra.infra.log.LogHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EurekaDeregister
/*    */ {
/*    */   private EurekaRegistration registration;
/*    */   private EurekaServiceRegistry serviceRegistry;
/*    */   private EurekaClientConfigBean eurekaClientConfigBean;
/*    */   
/*    */   public EurekaDeregister(EurekaRegistration registration, EurekaServiceRegistry serviceRegistry, EurekaClientConfigBean eurekaClientConfigBean)
/*    */   {
/* 22 */     this.registration = registration;
/* 23 */     this.serviceRegistry = serviceRegistry;
/* 24 */     this.eurekaClientConfigBean = eurekaClientConfigBean;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void showDeregisterInfo()
/*    */     throws Exception
/*    */   {
/* 32 */     if ((this.registration != null) && (this.serviceRegistry != null) && (this.eurekaClientConfigBean != null)) {
/* 33 */       CloudEurekaInstanceConfig instanceConfig = this.registration.getInstanceConfig();
/* 34 */       String eurekaDefaultZone = (String)this.eurekaClientConfigBean.getServiceUrl().get("defaultZone");
/* 35 */       String registerInfo = eurekaDefaultZone + "apps/" + instanceConfig.getAppname() + "/" + instanceConfig.getInstanceId();
/* 36 */       LogHelper.monitor("you can deregister service by: \"curl -X DELETE " + registerInfo + "\"");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void deregister()
/*    */     throws Exception
/*    */   {
/* 45 */     if ((this.registration != null) && (this.serviceRegistry != null)) {
/* 46 */       this.serviceRegistry.deregister(this.registration);
/* 47 */       LogHelper.monitor("deregister service info on Eureka: " + this.registration.getServiceId());
/*    */     }
/*    */   }
/*    */ }
