/*    */ package yb.ecp.fast.infra.infra.log;
/*    */ 
/*    */ import ch.qos.logback.classic.Level;
/*    */ import ch.qos.logback.classic.pattern.ClassicConverter;
/*    */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FastLevelConverter
/*    */   extends ClassicConverter
/*    */ {
/* 15 */   private static final Map<String, String> levelConvertMap = new HashMap();
/*    */   
/*    */   static {
/* 18 */     levelConvertMap.put("ERROR", "FATAL");
/* 19 */     levelConvertMap.put("WARN", "ERROR");
/* 20 */     levelConvertMap.put("INFO", "MONITOR");
/*    */   }
/*    */   
/*    */ 
/*    */   public String convert(ILoggingEvent iLoggingEvent)
/*    */   {
/* 26 */     String levelString = iLoggingEvent.getLevel().toString();
/* 27 */     String convertLevel = (String)levelConvertMap.get(levelString);
/* 28 */     if (null != convertLevel) {
/* 29 */       return convertLevel;
/*    */     }
/* 31 */     return levelString;
/*    */   }
/*    */ }
