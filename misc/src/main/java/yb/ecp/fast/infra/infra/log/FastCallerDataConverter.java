/*    */ package yb.ecp.fast.infra.infra.log;
/*    */ 
/*    */ import ch.qos.logback.classic.pattern.CallerDataConverter;
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import ch.qos.logback.core.CoreConstants;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FastCallerDataConverter
/*    */   extends CallerDataConverter
/*    */ {
/*    */   public String convert(ILoggingEvent le)
/*    */   {
/* 14 */     String result = super.convert(le);
/* 15 */     if (result.endsWith(CoreConstants.LINE_SEPARATOR))
/* 16 */       return result.substring(0, result.length() - CoreConstants.LINE_SEPARATOR_LEN);
/* 17 */     return result;
/*    */   }
/*    */ }

