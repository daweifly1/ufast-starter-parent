/*    */ package yb.ecp.fast.infra.security;
/*    */ 
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.spec.IvParameterSpec;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ import sun.misc.BASE64Decoder;
/*    */ import sun.misc.BASE64Encoder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AESUtil
/*    */ {
/* 14 */   private static String sKey = "abcdef0123456789";
/*    */   
/* 16 */   private static String ivParameter = "0123456789abcdef";
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static String encrypt(String sSrc)
/*    */   {
/* 25 */     String result = new String();
/*    */     try
/*    */     {
/* 28 */       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 29 */       byte[] raw = sKey.getBytes();
/* 30 */       SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
/* 31 */       IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
/* 32 */       cipher.init(1, skeySpec, iv);
/* 33 */       byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
/* 34 */       result = new BASE64Encoder().encode(encrypted);
/*    */     } catch (Exception e) {
/* 36 */       e.printStackTrace();
/*    */     }
/*    */     
/* 39 */     return result;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static String decrypt(String sSrc)
/*    */   {
/* 49 */     String originalString = new String();
/*    */     try {
/* 51 */       byte[] raw = sKey.getBytes("ASCII");
/* 52 */       SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
/* 53 */       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 54 */       IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
/* 55 */       cipher.init(2, skeySpec, iv);
/* 56 */       byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
/* 57 */       byte[] original = cipher.doFinal(encrypted1);
/* 58 */       originalString = new String(original, "utf-8");
/*    */     }
/*    */     catch (Exception ex) {
/* 61 */       ex.printStackTrace();
/*    */     }
/* 63 */     return originalString;
/*    */   }
/*    */ }

