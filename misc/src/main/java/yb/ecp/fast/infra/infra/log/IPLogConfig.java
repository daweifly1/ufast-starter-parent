/*    */ package yb.ecp.fast.infra.infra.log;
/*    */ 
/*    */ import ch.qos.logback.classic.pattern.ClassicConverter;
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import java.net.InetAddress;
/*    */ import java.net.UnknownHostException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IPLogConfig
/*    */   extends ClassicConverter
/*    */ {
/*    */   public String convert(ILoggingEvent iLoggingEvent)
/*    */   {
/*    */     try
/*    */     {
/* 18 */       return InetAddress.getLocalHost().getHostAddress();
/*    */     } catch (UnknownHostException e) {
/* 20 */       e.printStackTrace();
/*    */     }
/* 22 */     return null;
/*    */   }
/*    */ }
