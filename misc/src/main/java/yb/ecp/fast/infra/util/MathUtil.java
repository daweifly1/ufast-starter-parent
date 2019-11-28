/*    */ package yb.ecp.fast.infra.util;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.RoundingMode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MathUtil
/*    */ {
/*    */   public static BigDecimal MathRount(BigDecimal value)
/*    */   {
/* 15 */     return new BigDecimal(Math.round(value.doubleValue()));
/*    */   }
/*    */   
/*    */   public static String MathRount(String value)
/*    */   {
/*    */     try {
/* 21 */       return Long.toString(Math.round(Double.parseDouble(value)));
/*    */     }
/*    */     catch (Exception exp) {}
/*    */     
/* 25 */     return "0";
/*    */   }
/*    */   
/*    */ 
/*    */   public static double MathRount(double value)
/*    */   {
/* 31 */     return Math.round(value);
/*    */   }
/*    */   
/*    */   public static double MathRount(double value, int length)
/*    */   {
/* 36 */     return MathRount(new BigDecimal(value), length).doubleValue();
/*    */   }
/*    */   
/*    */   public static BigDecimal MathRount(BigDecimal value, int length)
/*    */   {
/* 41 */     return value.setScale(length, RoundingMode.HALF_EVEN);
/*    */   }
/*    */ }
