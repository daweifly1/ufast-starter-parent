/*    */ package yb.ecp.fast.infra.util;
/*    */ 
/*    */ import java.security.SecureRandom;
/*    */ import yb.ecp.fast.infra.security.CryptoUtil;
/*    */ 
/*    */ 
/*    */ public class RandomStringUtil
/*    */ {
/*    */   public static String RandomString(int length)
/*    */   {
/*    */     try
/*    */     {
/* 13 */       SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
/* 14 */       byte[] bytes = new byte[length / 2];
/* 15 */       random.nextBytes(bytes);
/* 16 */       return CryptoUtil.byteArrayToHexString(bytes);
/*    */     }
/*    */     catch (Exception e) {}
/* 19 */     return "";
/*    */   }
/*    */ }

