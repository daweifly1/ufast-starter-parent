/*    */ package yb.ecp.fast.infra.infra.log;
/*    */ 
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogHelper
/*    */ {
/* 13 */   private static final Logger log = LoggerFactory.getLogger("ufast");
/*    */   
/*    */ 
/*    */ 
/*    */   private static final String split = ":";
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void error(String message, int errorCode)
/*    */   {
/* 24 */     if (log.isWarnEnabled()) {
/* 25 */       log.warn(errorCode + ":" + message);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void fatal(String message, Throwable e)
/*    */   {
/* 35 */     log.error(message, e);
/*    */   }
/*    */   
/*    */   public static void debug(String message) {
/* 39 */     if (log.isDebugEnabled())
/* 40 */       log.debug(message);
/*    */   }
/*    */   
/*    */   public static void monitor(String message) {
/* 44 */     if (log.isInfoEnabled()) {
/* 45 */       log.info(message);
/*    */     }
/*    */   }
/*    */ }
