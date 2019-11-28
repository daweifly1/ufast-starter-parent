/*    */ package yb.ecp.fast.infra.infra.monitor;
/*    */ 
/*    */ import yb.ecp.fast.infra.infra.log.LogHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MemoryMonitor
/*    */ {
/*    */   private static final int MEGA = 1048576;
/*    */   
/*    */   public static void takeMemoryLog()
/*    */   {
/* 16 */     LogHelper.monitor(
/* 17 */       "Available processors (cores): " + Runtime.getRuntime().availableProcessors());
/*    */     
/* 19 */     LogHelper.monitor("Free memory (MB): " + 
/* 20 */       Runtime.getRuntime().freeMemory() / 1048576L);
/*    */     
/*    */ 
/* 23 */     long maxMemory = Runtime.getRuntime().maxMemory();
/*    */     
/* 25 */     LogHelper.monitor("Maximum memory (MB): " + (maxMemory == Long.MAX_VALUE ? "no limit" : 
/* 26 */       Long.valueOf(maxMemory / 1048576L)));
/*    */     
/*    */ 
/* 29 */     LogHelper.monitor("Total memory (MB): " + 
/* 30 */       Runtime.getRuntime().totalMemory() / 1048576L);
/*    */   }
/*    */   
/*    */   public abstract void scheduleMonitor();
/*    */ }
