/*    */ package yb.ecp.fast.infra.util;
/*    */ 
/*    */ 
/*    */ public class StringUtil
/*    */ {
/*    */   public static boolean isNullOrEmpty(String str)
/*    */   {
/*  8 */     if (str == null) {
/*  9 */       return true;
/*    */     }
/* 11 */     if (str.isEmpty()) {
/* 12 */       return true;
/*    */     }
/* 14 */     return false;
/*    */   }
/*    */   
/* 17 */   public static boolean isNullOrSpace(String str) { if (str == null) {
/* 18 */       return true;
/*    */     }
/* 20 */     return isNullOrEmpty(str.trim());
/*    */   }
/*    */ }
